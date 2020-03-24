package com.gura.step17sqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //필드
    private DBHelper helper;
    private EditText inputText;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> stringList; //listView를 출력하기 위해 Adapter에 연결할 문자열 목록
    private List<TodoDto> todoList;//DB에 있는 실제 DATA를 가지고 있는 TodoDto 목록
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
            onCreate() 메소드는 경우에 따라 여러번 호출될 수 있다.
            호출될때 version의 확인해서 이전과 똑같으면 DB테이블을 다시 생성하지 않는다.

            -name
            만들어질 파일의 이름을 작성
            파일하나를 만들어서 SQLlight 가 필요할때 마다 열어서 사용하는 것이다.

            -factory
            어플리케이션을 처음 인스톨 하자마자 어떤 복잡한 데이터를 갖고 있고 싶은 경우

            -version
            어떤 조건에서 version이 변경되게 할 수도 있다.
            예를 들어 버튼을 눌렀을 때, version이 올라가게 설정을 해놓으면
            onCreate() 메소드 호출시 DBHelper 클래스의 onupgrate()메소드가 호출된다.
         */
        helper=new DBHelper(this, "MyDB.sqlite", null, 1); //SQLite app이 MyDB.sqlite 파일에 접근하여 사용한다.(확장자명은 반드시.sqlite가 아니어도됨)
        //UI의 참조값 얻어오기
        inputText=findViewById(R.id.inputText);
        listView=findViewById(R.id.listView);
        //Button에 리스너 등록
        Button saveBtn=findViewById(R.id.saveBtn);
        Button deletBtn=findViewById(R.id.deleteBtn);
        Button updateBtn=findViewById(R.id.updateBtn);
        saveBtn.setOnClickListener(this);
        deletBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        //모델 객체 생성
        stringList=new ArrayList<>();
        //adapter 객체 생성
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, stringList);
        //listView에 adapter 장착하기
        listView.setAdapter(adapter);

        todoList=new ArrayList<>();

        showList();

    }

    //버튼을 눌렀을때 호출되는 메소드
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.updateBtn:
                index=listView.getCheckedItemPosition();
                if(index==-1){ //선택된 셀이 없을 경우
                    Toast.makeText(this, "수정할 셀을 선택하세요", Toast.LENGTH_LONG).show();
                    return;
                }
                SQLiteDatabase db3=helper.getWritableDatabase();
                /*
                    [날짜 format 만들기]
                    strftime("날짜형식", 날짜문자열, 'localtime')
                    년 : %Y
                    월 : %m
                    일 : %d
                    시 : %H
                    분 : %M
                    초 : %S
                 */
                String sql3="SELECT num, content, strftime(\"%Y년 %m월 %d일 %H시 %M분 %S초\",regdate)" + // "" 안에 또 ""가 들어갈때는 \" 로 표기해주어야한다.
                        " FROM todo" +
                        " WHERE num=?";
                //삭제할 cell의 primary key
                int num2=todoList.get(index).getNum();
                String[] args3={Integer.toString(num2)};
                Cursor result2=db3.rawQuery(sql3, args3);
                TodoDto dto=null;
                //반복문 돌면서 Cursor 객체에서 정보 읽어오기
                while (result2.moveToNext()){
                    //0번째 칼럼의 문자열 읽어오기
                    int num=result2.getInt(0);
                    //1번째 칼럼의 문자열 읽어오기
                    String content=result2.getString(1);
                    //2번째 칼럼의 문자열 읽어오기
                    String regdate=result2.getString(2);
                    //TodoDto 객체를 생성해서 번호, 내용, 날짜를 넣어주고
                    dto=new TodoDto(num, content, regdate);
                }
                Intent intent=new Intent(this, DetailActivity.class);
                intent.putExtra("todoDto", dto);
                startActivity(intent);
                break;
            case R.id.deleteBtn:
                index=listView.getCheckedItemPosition();
                if(index==-1){ //선택된 셀이 없을 경우
                    Toast.makeText(this, "삭제할 셀을 선택하세요", Toast.LENGTH_LONG).show();
                    return;
                }
                SQLiteDatabase db2=helper.getWritableDatabase();
                String sql2="DELETE FROM todo" +
                        " WHERE num=?";
                //삭제할 cell의 primary key
                int num=todoList.get(index).getNum();
                Object[] args2={num};
                db2.execSQL(sql2,args2);
                db2.close();
                //선택된 cell 취소하기
                listView.clearChoices();
                //목록 다시 출력하기
                showList();
                break;
            case R.id.saveBtn:
                //1. 입력한 문자열을 읽어와서
                String inputMsg=inputText.getText().toString();
                if(TextUtils.isEmpty(inputMsg)){ //입력된 내용이 없을 경우
                    Toast.makeText(this, "내용을 입력하세요", Toast.LENGTH_LONG).show();
                    return;
                }
                //2. todo 테이블에 저장한다.
                SQLiteDatabase db=helper.getWritableDatabase();
                Object[] args={inputMsg}; //전달할 DATA를 순서대로 담으면 SQL Query문에 자동으로 바인딩 된다.
                String sql="INSERT INTO todo (content, regdate)" +
                        "VALUES(?, datetime('now','localtime'))"; //datetime('now','localtime')==SYSDATE
                db.execSQL(sql, args);
                db.close();

                Util.hideKeyboard(this);
                inputText.setText("");
                Util.releaseFocus(inputText);

                showList();
                break;

        }
    }

    //ListView에 할일 목록을 출력하는 메소드
    public void showList(){
        //데이터가 쌓이지 않도록 모델 초기화
        stringList.clear();
        todoList.clear();
        //실행할 SELECT 문
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="SELECT num, content, regdate" +
                " FROM todo" +
                " ORDER BY num DESC";
        //SELECT 문을 수행하고 결과를 Cursor type으로 받아오기
        /*
            selectionArgs
            SELECT 문에 WHERE 절이 들어가야된다면 selectionArgs에
            Object[]로 전달하면 된다.
         */
        Cursor result=db.rawQuery(sql, null); //Cursor == ResultSet(JDBC)
        //반복문 돌면서 Cursor 객체에서 정보 읽어오기
        while (result.moveToNext()){
            //0번째 칼럼의 문자열 읽어오기
            int num=result.getInt(0);
            //1번째 칼럼의 문자열 읽어오기
            String content=result.getString(1);
            //2번째 칼럼의 문자열 읽어오기
            String regdate=result.getString(2);
            //읽어온 문자열을 모델에 추가하기
            stringList.add(num+" | "+content+" | "+ regdate);
            //TodoDto 객체를 생성해서 번호, 내용, 날짜를 넣어주고
            TodoDto dto=new TodoDto(num, content, regdate);
            //todoList에 추가하기
            todoList.add(dto);
        }
        //모델의 data가 바뀌었다고 adapter에 알려서 ListView 업데이트하기
        adapter.notifyDataSetChanged();
    }
}

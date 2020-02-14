package com.gura.step03listview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
// AdapterView.OnItemClickListener 의 의미는 AdapterView 클래스 안에 OnItemClickListener 인터페이스가 존재한다는 의미이다.
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //listView 의 참조값 얻어오기
        ListView listView=findViewById(R.id.listView);

        //어답터에 연결할 모델(data)
        names=new ArrayList<>();
        //모델에 sample data 저장
        for(int i=0; i<100; i++){
            names.add("김구라"+i);
        }

        /*
            [ListView에 연결한 어답터 객체 생성하기]
            모델을 바로 list에 넣을 수 없다.
            따라서 연결할 수 있도록 어답터 객체가 필요하다.
         */
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        //ListView에 어답터 연결하기
        listView.setAdapter(adapter);
        //ListView에 아이템 클릭 리스너 연결하기
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        //인자로 전달되는 i에는 클릭한 셀의 index값이 들어있다.
        new AlertDialog
                .Builder(this)
                .setMessage(names.get(i))
                .setNeutralButton("확인", null)
                .create()
                .show();
        /*
            위의 코드를 풀어서 사용하면 아래와 같다

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage(names.get(i)); //자기자신의 참조값을 계속 리턴 해줌
            builder.setNeutralButton("확인", null);
            builder.create();
            builder.show();

         */
    }

    //'다음예제' 버튼 눌렀을때 호출되는 메소드
    public void moveNext(View v){
        // Main2Activity로 이동할 의도 객체 생성
        Intent intent=new Intent(this, Main2Activity.class);
        //안드로이드 시스템에서 제공하는 API를 사용하기 위해 startActivity() 메소드를 호출하면서 의도 객체를 전달하면 activity 이동을 할 수 있다.
        startActivity(intent);
    }

}

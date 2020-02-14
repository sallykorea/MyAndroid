package com.gura.step03listview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    List<String> names;
    int selectedIndex;
    ArrayAdapter<String> adapter;

    DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int i) { // '네', '아니오' 버튼을 클릭시 정수 값이 i에 인자로 들어온다.
            switch (i){
                case DialogInterface.BUTTON_POSITIVE:
                    //1. 선택한 아이템 인덱스를 모델에서 삭제한다.
                    names.remove(selectedIndex);
                    //2. Adapter를 사용해서 lisView를 업데이트 한다.
                    adapter.notifyDataSetChanged();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:


                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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
       adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        //ListView에 어답터 연결하기
        listView.setAdapter(adapter);
        //ListView에 아이템 클릭 리스너 연결하기
        listView.setOnItemLongClickListener(this);
    }

    //셀을 오래 누르고 있으면
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {

        //선택한 index를 필드에 저장한다.
        selectedIndex=i;

        new AlertDialog.Builder(this)
                .setTitle("알림")
                .setMessage(names.get(i)+"해당 셀을 삭제하시겠습니까?")
                .setPositiveButton("네", listener)
                .setNegativeButton("아니오", listener)
                .create()
                .show();

        return false;
    }
}

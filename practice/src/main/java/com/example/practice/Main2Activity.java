package com.example.practice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

// AdapterView.OnItemClickListener 의 의미는 AdapterView 클래스 안에 OnItemClickListener 인터페이스가 존재한다는 의미이다.
public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    List<String> list;
    int seletedIndex;
    ArrayAdapter<String> adapter;

    DialogInterface.OnClickListener onClickListener=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int i) {

            switch (i){
                case DialogInterface.BUTTON_POSITIVE:
                    list.remove(seletedIndex);
                    adapter.notifyDataSetChanged();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:

                    break;
            }
        }
    };

    AdapterView.OnItemLongClickListener longClickListener=new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
            seletedIndex=i;
            new AlertDialog.Builder(Main2Activity.this)
                    .setTitle("알림")
                    .setMessage(list.get(i)+" 번째를 삭제하시겠습니까?")
                    .setPositiveButton("네", onClickListener)
                    .setNegativeButton("아니오", null)
                    .create()
                    .show();

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //
        ListView listView=findViewById(R.id.listView);

        //
        list=new ArrayList<>();
        for(int i=0; i<100; i++){
            list.add("sally"+i);
        }

        //
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(longClickListener);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        new AlertDialog
                .Builder(this)
                .setMessage(list.get(i)+"번을 선택하였습니다.")
                .setNeutralButton("확인", null)
                .create()
                .show();


    }


}

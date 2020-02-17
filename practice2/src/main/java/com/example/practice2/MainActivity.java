package com.example.practice2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //필드
    ListView listView;
    List<InfoDto> informations;
    InfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listView);
        informations=new ArrayList<>();
        for(int i=0; i<30; i++){
            informations.add(new InfoDto(R.drawable.profile, i+1+"사람", "서울시" +i+"번지", "010-1111-2222"));
        }

        adapter=new InfoAdapter(this, R.layout.layout_cell, informations); //context는 activity를 지칭한다고 생각하면된다.

        listView.setAdapter(adapter);
        /*
            listView.setOnItemClickListener(this);

            activity에 setOnItemClickListener를 등록하려고 할 때 button이 있는 경우,
            listener가 등록이 안돼는 이슈가 있다.
            이 때는 listView에 listener를 달아주지 말고 button에 listener를 달아주어야한다.
         */


    }

}

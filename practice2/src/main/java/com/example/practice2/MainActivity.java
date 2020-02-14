package com.example.practice2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //필드
    ListView listView;
    List<InfoDto> informations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listView);
        informations=new ArrayList<>();
        for(int i=0; i<30; i++){
            informations.add(new InfoDto(i, i+"사람", "010-1111-2222"));
        }


    }
}

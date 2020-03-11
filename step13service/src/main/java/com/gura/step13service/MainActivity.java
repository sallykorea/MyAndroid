package com.gura.step13service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //서비스 시작 버튼을 눌렀을 때
    public void start(View v){
        //서비스를 시작 시킬 Intent 객체 생성
        Intent intent=new Intent(this, MyService.class); //어떤 서비스를 시작 시킬지 명시하기
        //Intent 객체를 이용해서 서비스를 시작 시킨다.(activity 부모 메소드)
        startService(intent); // MyService의 onStartCommand() 메소드가 호출된다.
    }

    //서비스 종료 버튼을 눌렀을 때
    public void end(View v){
        //서비스를 종료 시킬 Intent 객체 생성
        Intent intent=new Intent(this, MyService.class);//어떤 서비스를 종료 시킬지 명시하기
        //Intent 객체를 이용해서 서비스를 종료 시킨다.(activity 부모 메소드)
        stopService(intent); // MyService의 onDestroy() 메소드가 호출된다.
    }
}

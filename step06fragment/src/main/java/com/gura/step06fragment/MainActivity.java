package com.gura.step06fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyFragment.MyfragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
            [fragment 객체의 참조값이 activity에서 필요한 경우]
            만일 Activity_main.xml에 정의된 fragment 객체의 참조값이 activity에서 필요하다면?
         */
        //1. fragment의 매니져 객체의 참조값을 얻어와서
        FragmentManager fm=getSupportFragmentManager();
        //2. fragment의 참조 값을 갖고 온다.
        MyFragment myFragment=(MyFragment)fm.findFragmentById(R.id.myFragment);

        Button button=findViewById(R.id.button2);
    }

    //버튼을 눌렀을 때 호출될 메소드
    public void move(View v) {
        Intent intent=new Intent(this, SubActivity.class);
        startActivity(intent);
    }

    //fragment가 호출할 메소드
    @Override
    public void showMessage(int count) {
        Toast.makeText(this, "현재 카운드 : "+count, Toast.LENGTH_SHORT).show();
    }

}

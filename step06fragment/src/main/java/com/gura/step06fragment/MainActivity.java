package com.gura.step06fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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

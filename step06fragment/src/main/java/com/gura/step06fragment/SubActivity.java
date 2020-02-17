package com.gura.step06fragment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SubActivity extends AppCompatActivity implements MyFragment.MyfragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
    }

    //fragment가 호출할 메소드
    @Override
    public void showMessage(int count) {
        new AlertDialog.Builder(this)
                .setMessage("현재 카운트 : "+count)
                .setNeutralButton("확인", null)
                .create()
                .show();
    }
}

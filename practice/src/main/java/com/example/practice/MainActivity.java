package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // 3.
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText editText=findViewById(R.id.editText);
            String msg=editText.getText().toString();

            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 메소드를 만들어서 버튼에 직접 이벤트 연결해주기
        Button sendBtn1=findViewById(R.id.sendBtn1);

        // 2. View.OnClickListener 인터페이스를 구현한 리스너를 button에 연결해주기
        Button sendBtn2=findViewById(R.id.sendBtn2);
        sendBtn2.setOnClickListener(this);

        // 3. View.OnClickListener 인터페이스 type을 필드에 가지고 있기
        Button sendBtn3=findViewById(R.id.sendBtn3);
        sendBtn3.setOnClickListener(listener);

        //4.다음예제로 이동하기 버튼을 눌렀을때 호출 되는 메소드
        Button sendBtn4=findViewById(R.id.sendBtn4);
        sendBtn4.setOnClickListener(this);
    }

    // 1.
    public void sendMsg(View v){
        EditText editText=findViewById(R.id.editText);
        String msg=editText.getText().toString();

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    // 2.
    @Override
    public void onClick(View v) {
        int resId=v.getId();
        if(resId==R.id.sendBtn2){
            EditText editText=findViewById(R.id.editText);
            String msg=editText.getText().toString();

            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }else if(resId==R.id.sendBtn4){
            Intent intent=new Intent(this, Main2Activity.class);
            startActivity(intent);
        }

    }
}

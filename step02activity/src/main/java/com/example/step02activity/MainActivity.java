package com.example.step02activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    // View.OnClickListener 인터페이스 type을 필드에 가지고 있기
    View.OnClickListener listener=new View.OnClickListener() { //익명 클래스
        @Override
        public void onClick(View v) {
            //1. EditText 에 입력한 문자열을 읽어온다.
            EditText inputMsg=findViewById(R.id.inputMsg); // @+id/inputMsg 로 자원화해서 받은 정수 값이 리턴된다.
            String msg=inputMsg.getText().toString();
            //2. Toast 메세지에 문자열을 띄우기
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //전송2 button의 참조값 얻어오기(implements View.OnClickListener)
        Button sendBtn=findViewById(R.id.sendBtn);
        //버튼에 클릭 리스너 등록하기
        sendBtn.setOnClickListener(this);

        //전송3 button의 참조값 얻어오기
        Button sendBtn3=findViewById(R.id.button3);
        //버튼에 클릭 리스너 등록하기
        sendBtn3.setOnClickListener(listener);
    }

    //버튼을 눌렀을때 메소드가 호출되게 하려면 View 객체를 받을 준비를 한다.
    public void sendMsg(View v){
        //1. EditText 에 입력한 문자열을 읽어온다.
        EditText inputMsg=findViewById(R.id.inputMsg); // @+id/inputMsg 로 자원화해서 받은 정수 값이 리턴된다.
        String msg=inputMsg.getText().toString();
        //2. Toast 메세지에 문자열을 띄우기
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        //1. EditText 에 입력한 문자열을 읽어온다.
        EditText inputMsg=findViewById(R.id.inputMsg); // @+id/inputMsg 로 자원화해서 받은 정수 값이 리턴된다.
        String msg=inputMsg.getText().toString();
        //2. Toast 메세지에 문자열을 띄우기
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    //다음예제로 이동하기 버튼을 눌렀을때 호출 되는 메소드
    public void move(View v){
        // 액티비티를 이동하기 위한 Intent(의도) 객체를 생성한다.
        Intent intent=new Intent(this, Example2Activity.class);
        // startActivity() 메소드를 호출하면서 Intent 객체를 전달한다.
        startActivity(intent);
        //activity는 이동할 때마다 쌓이면서 history를 남긴다. 뒤로가기를 하는 경우, 쌓였던 activity가 사라지고 이전에 있던 activtiy가 나온다.
        //그러다가 더이상 남아 있는 activity history가 없으면 앱이 종료된다.
    }
}

package com.gura.step10broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //TextView 의 참조값을 담을 필드
    private TextView textView;
    public static final String HUNGRY="com.gura.step10broadcastreceiver.HUNGRY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView의 참조값을 필드에 저장
        textView=findViewById(R.id.textView);

        //방송 수신자 객체 생성해서
        HungryReceiver hr=new HungryReceiver();
        //동작 가능하도록하기
        registerReceiver(hr, new IntentFilter(HUNGRY));
    }

    //방송 수신자 객체가 호출할 메소드
    public void updateUI(String msg){
        textView.setText(msg);
    }

    public void btnClicked(View v){
        //인텐트 객체 생성하고
        Intent intent=new Intent();
        //엑션명 지정
        intent.setAction(HUNGRY);
        //해당 인텐트 객체를 이용해서 방송하기
        sendBroadcast(intent);
    }
}

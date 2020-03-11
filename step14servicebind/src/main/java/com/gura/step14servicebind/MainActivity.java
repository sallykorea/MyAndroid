package com.gura.step14servicebind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //필요한 맴버필드 정의하기
    MessengerService mService;
    //서비스에 연결되었는지 여부
    boolean mServiceConnected=false;
    EditText console;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        console = (EditText)findViewById(R.id.editText);
        //서비스를 시작 시킨다.
        Intent intent=new Intent(this, MessengerService.class);
        startService(intent);
    }
    //서비스 종료 버튼을 눌렀을때
    public void end(View v){
        if(mServiceConnected){//서비스에 바인딩 된 상태라면
            //바인딩을 해제해준다. activity가 비활성화 될 때 다른 activity가 연결될 수 있도록 종료되는 activity의 연결(바인딩)을 끊어준다.
            unbindService(mConn);
            mServiceConnected=false;
        }
        Intent intent=new Intent(this, MessengerService.class);
        stopService(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //서비스 바인딩하기(service와 activity를 연결한다)
        Intent intent=new Intent(this, MessengerService.class);
        bindService(intent, mConn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        if(mServiceConnected){//서비스에 바인딩 된 상태라면
            //바인딩을 해제해준다.
            unbindService(mConn);
            mServiceConnected=false;
        }
        super.onStop();
    }

    //서비스로 부터 문자열을 전달받을 메소드
    public void setMsg(final String msg){
        //UI 스레드에서 동작할수 있도록 한다.
        //ui 스레드 안에서 run()메소드가 호출될 수 있도록 살짝 편법을 써야한다. 그렇지 않으면 app에서 오류가 남
        runOnUiThread(new Runnable() { //Local inner class(new Runnable.class)에서 밖에 있는 변수는 final일 경우에만 참조 가능하다.
            @Override
            public void run() {
                //edittext에 출력하기
                console.append(msg+"\n");
                // local inner class는 비동기 처리를 위해 객체를 만들어 사용할 때 쓰인다.
                // 따라서 변수를 전달 받을 일이 있을 경우 local inner class는 지역변수를 미리 capture하는데
                // capture한 변수가 변경되면 안되니 상수화 된 데이터만 사용하기 위함이다.
            }
        });

    }


    //서비스 연결객체
    //서비스 객체의 참조값은 운영체제가 생성하게된다.(activity나 다른 component에서 직접 생성할 수 없음)
    ServiceConnection mConn=new ServiceConnection() {
        //서비스와 연결되었을때 호출되는 메소드
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) { //IBinder 에는 MessengerService.class의 LocalBinder 객체의 참조값이 들어온다.
            //서비스의 onBind() 메소드에서 리턴해주는 IBinder 객체가 들어온다.
            MessengerService.LocalBinder localBinder = (MessengerService.LocalBinder)service;
            //MessengerService 의 참조값을 맴버필드에 저장한다.
            mService=localBinder.getService();
            //서비스에 액티비티의 참조값을 전달한다.
            localBinder.setActivity(MainActivity.this);
            //서비스와 연결되었다고 표시한다.
            mServiceConnected=true;

        }

        //서비스와 연결 해제 되었을때 호출되는 메소드
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceConnected=false;
            mService=null;
        }
    };
}

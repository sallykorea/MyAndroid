package com.gura.step13service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/*
    [UI 없이 백그라운드에서 동작할 수 있는 Service]
    1. Service 추상클래스를 상속 받는다.
    2. onBind() 메소드 오버라이딩
    3. 추가로 필요한 메소드를 오버라이딩 해서 작업한다.
    4. Service가 동작할 수 있도록 AndroidManifest.xml에 등록해준다.

 */
public class MyService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    //초기화할 작업을 하는 메소드

    @Override
    public void onCreate() {
        super.onCreate();
    }

    //서비스에서 수행해야할 main 작업을 하는 메소드
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //핸들러에 메세지 보내기
        handler.sendEmptyMessage(0);
        return super.onStartCommand(intent, flags, startId);
    }

    //서비스가 종료될때 마무리 작업을 하는 메소드
    @Override
    public void onDestroy() {
        super.onDestroy();
        //핸들러 동작 하지 않도록
        handler.removeMessages(0);
    }

    //카운트값을 저장할 필드
    int count=0;

    //핸들러
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            count++;
            Toast.makeText(getApplicationContext(), //부모클래스로 부터 받은 메소드이다.(Service)
                    count+" 번 호출",
                    Toast.LENGTH_SHORT).show();
            handler.sendEmptyMessageDelayed(0, 5000);
        }
    };

}

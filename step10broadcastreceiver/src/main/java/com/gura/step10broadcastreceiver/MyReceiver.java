package com.gura.step10broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/*
    [BroadcastReceiver를 만드는 방법]

    - BroadcastReceiver 추상 클래스를 상속 받아서 만든다.
    - onReceive() 메소드를 오버라이딩 해서 방송이 수신되었을때 원하는 작업을 한다.
    - AndroidManifest.xml에 등록을 해야한다.
    -
 */
public class MyReceiver extends BroadcastReceiver {
    //activity의 참조값을 담을 필드
    private MainActivity activity;
    //activity의 참조값을 필드에 저장하는 setter 메소드
    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    //특정 방송이 수신되면 호출되는 메소드
    @Override
    public void onReceive(Context context, Intent intent) {

        boolean isOn=intent.getBooleanExtra("state", false);
        if(isOn){
            Toast.makeText(context, "비행기 모드 on", Toast.LENGTH_SHORT).show();
            activity.updateUI("AirPlanMode on"); //UI를 업데이트하고 싶다면 activity(UI를 컨트롤하는 컴포넌트이다.)에 있는 내용을 업데이트 하면된다.
        }else {
            Toast.makeText(context, "비행기 모드 off", Toast.LENGTH_SHORT).show();
            activity.updateUI("AirPlanMode off");
        }

    }
}

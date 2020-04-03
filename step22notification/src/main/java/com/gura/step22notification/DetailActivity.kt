package com.gura.step22notification

import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

    }

    override fun onStart() {
        super.onStart()

        //Intent 객체에 "msg" 라는 키값으로 담긴 문자열 읽어오기
        var msg=intent.getStringExtra("msg") //getIntent() 대신에 kotlin에서는 intent 라고 작성해주면 된다.
        System.out.println(msg)
        //TextView 에 출력하기
        textView.setText(msg) //findById() 대신에 kotlin에서는 textView 라고 작성해주면 된다.
        //Intent 객체에 "id" 라는 키값으로 전달된 Int 값이 있는지 읽어와 본다.
        var id=intent.getIntExtra("id", -1)
        if(id!=-1){ //알림을 수동을 취소 해야하는 경우
            //알림 매니저 객체의 참조값을 얻어와서
            val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            //알림 id 값을 전달해서 취소 시킨다.
            //manager.cancel(id)
            manager.cancelAll() //: 모든 알림을 취소 시킨다.
        }
    }
}

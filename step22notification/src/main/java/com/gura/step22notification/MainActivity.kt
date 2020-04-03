package com.gura.step22notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

//Kotlin 도 단일 extends(상속)을 지원한다. implments는 여러개 가능함.
class MainActivity : AppCompatActivity(), View.OnClickListener {
    //현재 알림의 아이디
    var currentId=0
    //채널의 아이디를 겹치지 않게 유일하게 구성하기
    var CHANNEL_ID="com.gura.step22notification.ALERT_CHANNEL"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //버튼에 리스너 등록하기
        notiBtn.setOnClickListener(this)
    }


    //View? - nullableView type | View type 과는 다르다.
    //모든 type은 nullable type과 그냥 type으로 나누어진다.
    override fun onClick(v: View?) { //눌러진 버튼의 참조값이 부모 type인 View type으로 들어온다.
        // as 연산자를 사용해 원래 type인 Button으로 casting
        //var btn=v as Button
        when(v?.id){
            R.id.notiBtn -> {
                //입력한 문자열을 읽어와서
                val msg=inputMsg.text.toString()
                //알림에 띄운다.
                makeAutoCancelNoti(msg)
            }
        } //when end

    } //onClick() end

    //인자로 전달되는 문자열을 알림에 띄우는 함수
    fun makeAutoCancelNoti(msg:String){
        //이 앱의 알림 채널 만들기
        createNotificationChannel()
        //알림을 클릭했을때 실행할 Activity 정보를 가지고 있는 Intent 객체
        /*
            [ .apply{} ]
            - 블럭안에서 객체의 기능을 다 사용하고 참조값을 담을 수 있다.
            - 다른 객체에도 .apply{} 를 붙여주면 객체에 있는 method 또는 property 사용하고 그 결과를 변수에 담을 수 있다.
            Intent intent=new Intent()
            intent.setFlag(xxx)
            intent.putExtra(xxx, xxx)
         */
        /*
            [ with ]
            - 어떤 객체의 참조값이 이미 나와 있는 상태에서 사용한다는 점에서 apply 와 조금 다르다.
         */
        val intent = Intent(this, DetailActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("msg", msg)
        }

        //Intent 객체를 인텐트 전달자 객체에 담는다.
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        //NotificationCompat.Builder 객체 생성
        val builder = NotificationCompat.Builder(this, "gura")
                .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .setContentTitle("오빠 나야!")
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true) //알림을 선택해서 읽으면 자동으로 알림이 없어지도록한다.

        currentId++

        //with(객체 참조값)
        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(currentId, builder.build()) //notify(알림의 id 값, ) id값을 변경 시키면 알림이 쌓인다.
        }
    }
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "kim"
            val descriptionText = "test!"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {// channel id값을 등록해야지 알림이 뜬다.
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}

package com.gura.step22notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //Intent 객체에 "msg" 라는 키값으로 담긴 문자열 읽어오기
        var msg=intent.getStringExtra("msg") //getIntent() 대신에 kotlin에서는 intent 라고 작성해주면 된다.
        //TextView 에 출력하기
        textView.setText(msg) //findById() 대신에 kotlin에서는 textView 라고 작성해주면 된다.
    }
}

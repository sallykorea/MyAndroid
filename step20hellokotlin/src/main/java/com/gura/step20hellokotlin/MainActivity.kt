package com.gura.step20hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//[JAVA] class MainActivity extends AppCompatActivity {}
class MainActivity : AppCompatActivity() {
    /*
        [JAVA]
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }
     */
    override fun onCreate(savedInstanceState: Bundle?) { // ?는 변수에 NULL값을 허용할지말지 결정한다.(? 있으면 NULL 값 허용, ? 없으면 NULL값 불허)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

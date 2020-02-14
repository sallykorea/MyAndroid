package com.gura.step01layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
            res/layout/activity_main.xml 문서를 전개(해석)해서 화면 구성하기
            xml 문서에 있는 UI 객체(View객체)를 각각 생성하고 설정 정보에 따라 설정을 수행한다.
         */
        setContentView(R.layout.linear08);

        ConstraintLayout a=null;
        TextView b=null;
    }

    public void introduceBtn(View v){
        setContentView(R.layout.introducpage);
    }
}

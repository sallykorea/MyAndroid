package com.gura.step06fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment  extends Fragment implements View.OnTouchListener {

    //터치 횟수를 관리할 필드
    private int touchCount;
    TextView textView;

    /*
       [Fragment 란?]
       - 여러가지 activity 에서 공통으로 사용할 UI와 동작이 있을 때 사용하는 것이다.
         mini activity 느낌
       - 화면의 특정 부분을 Fragment에 맡길 수 있다.
       - 코드의 재활용성이 좋아진다.
       - 단, fragment 단독으로는 존재할 수 없다. activity 안에서 존재해야한다.
       - 예시) 웹페이지에서 이미지 슬라이더

       [Fragment 만드는 법]
       1. Fragment 클래스를 상속 받는다.
       2. onCreatView() 메소드를 오버라이드 한다.
    */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //인자로 전달되는 레이아웃 전개자 객체를 이용해서 View 객체를 만들어서
        View view=inflater.inflate(R.layout.fragment_my, container);
        //TextView 의 참조값 얻어오기
        textView=view.findViewById(R.id.textView);
        //textView에 터치 리스너 등록하기
        textView.setOnTouchListener(this);
        //리턴해주어야한다.
        return view;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // 1. touchCount를 1증가시킨다.
        touchCount++;
        // 2. textview에 출력한다.
        textView.setText(Integer.toString(touchCount));
        return false;
    }
}

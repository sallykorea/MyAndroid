package com.gura.step07customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/*
    setContentView(View view);
    - View 객체 하나로 Activity를 꽉 채운다.
    - 예) 게임화면

    Custom View 만드는 방법
    1. View 클래스를 상속 받는다.
    2. Context 객체를 생성자의 인자로 전달받아서 부모 생성자에 전달하도록 한다.
    3. onDraw() 메소드를 오버라이딩해서 View 화면을 구성한다.
*/
public class MyView extends View {// 1. 상속
    //터치 이벤트가 일어난 x, y 좌표를 저장할 필드
    private int x, y;

    // 2. 생성자
    public MyView(Context context) {
        super(context);
    }

    // layout xml 문서에서 해당 View 를 사용하게 하려면 필요한 생성자
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    // 3.
    @Override
    protected void onDraw(Canvas canvas) { // canvas==context==activity
        canvas.drawColor(Color.GREEN);
        Paint textPaint=new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(100);
        //text 는 좌하단이 원점(0,0)이다.
        canvas.drawText("x:"+x+" y:"+y, 10, 110, textPaint);

    }

    //터치 이벤트 처리
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //이벤트가 일어난 곳의 좌표
        x=(int)event.getX();
        y=(int)event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        //View 갱신하기 (결과적으로 onDraw() 메소드가 다시호출된다.)
        //view에 그려진 내용을 무효화 하고 다시 그린다.
        invalidate();
        return true;
    }
}

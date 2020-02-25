package com.gura.step07customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TouchView extends View {

    //이 View 의 폭과 높이
    private int width, height;
    //글자 혹은 도형을 출력할때 사용할 Paint 객체
    private Paint bluePaint, greenPaint;
    //이벤트의 종류를 저장할 필드
    private String eventType="";
    //이벤트가 일어난 곳의 좌표를 저장할 필드
    private int eventX, eventY;

    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /*
        화면이 처음 구성될때 최초 한번 호출되고 그 이후에는 View의 사이즈가 바뀌었을때 호출된다.
        layout이 전개되어 화면 구성이 될때 method가 호출되면서 화면의 너비, 높이가 인자로 들어온다.
        값을 다른 곳에서 사용하고 싶으면 필드를 정의 한다.


        1. onSizeChanged()가 가장 먼저 호출되어 화면의 사이즈를 측정한다.
        2. 그 후 init() method 를 호출하여 화면 사이즈에 맞도록 초기화 작업을 하고
        3. onDraw() 메소드가 호출된다.

     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //크기를 필드에 저장하고
        width=w;
        height=h;

        //초기화 메소드를 호출한다.
        init();
    }

    //초기화하는 메소드
    public void init(){
        //파란색 Paint 객체 설정
        bluePaint=new Paint();
        bluePaint.setColor(Color.BLUE);
        bluePaint.setStyle(Paint.Style.FILL);
        bluePaint.setTextSize(50);

        //녹색 Paint 객체 설정
        greenPaint=new Paint();
        greenPaint.setColor(Color.GREEN);
        greenPaint.setStyle(Paint.Style.FILL);
        greenPaint.setStrokeWidth(5);

        //핸들러가 동작하도록 빈 메세지 보내기
        handler.sendEmptyMessageDelayed(0, 10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //이벤트의 종류 출력하기
        canvas.drawText("이벤트 종류:"+eventType, 0, 100, bluePaint);
        //이벤트가 일어난 곳의 좌표 출력하기
        canvas.drawText("x:"+eventX+" y:"+eventY, 0, 200, bluePaint);
        //View 의 폭과 높이
        canvas.drawText("width:"+width+" height:"+height, 0, 300, bluePaint);
        //이벤트가 일어난 곳에 원 그리기
        canvas.drawCircle(eventX, eventY, 50, greenPaint);
        //이벤트가 일어난 곳에 직선 그리기
        canvas.drawLine(0, eventY, 2000, eventY, greenPaint);
        canvas.drawLine(eventX, 0, eventX, 2000, greenPaint);

    }

    //touch 이벤트가 일어날때 마다 화면이 업데이트 됨
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        eventX=(int)event.getX();
        eventY=(int)event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                eventType="ACTION_DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                eventType="ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                eventType="ACTION_UP";
                break;
        }

        return true; //false를 return하면 ACTION_DOWN 이벤트만 처리된다.
    }


    //onDraw()메소드가 1초에 100번 호출될 수 있도록 Handler 클래스 만들어주기
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            invalidate();//화면 갱신하기
            // 10/1000 초 이후에 핸들러에 빈 메세지 보내기
            handler.sendEmptyMessageDelayed(0, 10);
        }
    };
}

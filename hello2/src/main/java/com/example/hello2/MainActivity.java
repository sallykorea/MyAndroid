package com.example.hello2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //res/layout/activity_main.xml 문서를 전개해서 화면 구성하기
        //res에 등록된 모든 것들은 16진수 정수로 등록되어 관리한다. 따라서 원하는 것을 불러오려면 해당 참조값을 불러와야한다.
        // 참조값을 불러오는 방법은 아래와 같다.
        //setContentView(R.layout.activity_main) : R 클래스 의 Layout 클래스에 activity_main.xml 문서의 참조값을 갖고와서 layout을 구성한다.
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.test01);
    }

    //버튼을 눌렀을때 메소드가 호출되게 하려면 View 객체를 받을 준비를 한다.
    public void  btnClicked(View v){
        //로그 출력하기
        Log.d("one", "버튼을 눌렀네요?");
        //토스트 띄우기
        Toast.makeText(this,"버튼 눌렀음?", Toast.LENGTH_LONG).show();
        //알림창 띄우기
        new AlertDialog.Builder(this)
                .setTitle("알림")
                .setMessage("버튼을 눌렀네?")
                .setNeutralButton("확인", null)
                .create()
                .show();
    }

    public void getName(View v){
        TextView nameBox=(TextView)findViewById(R.id.editText3);
        String getName=nameBox.getText().toString();
        Toast.makeText(this, " nameBox 이름이 입력되었습니다.", Toast.LENGTH_SHORT).show();
    }
}

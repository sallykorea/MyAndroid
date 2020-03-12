package com.gura.step15httprequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener, Util.RequestListener {

    private EditText inputUrl, console;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //id가 requestBtn인 Button객체의 참조값을 얻어와서 requestBtn이라는 지역변수에 담기
        Button requestBtn=findViewById(R.id.requestBtn);
        //버튼에 리스너 등록하기
        requestBtn.setOnClickListener(this);

        inputUrl=findViewById(R.id.inputUrl);
        console=findViewById(R.id.console);
    }

    @Override
    public void onClick(View v) {
        String urlAddr=inputUrl.getText().toString();
        //Util 클래스의 메소드를 이용해서 GET 방식 요청을 한다.
        //.sendGetRequest(요청id, 요청url, 요청 파라미터, 요청 리스너)
        Util.sendGetRequest(0, urlAddr, null, this);
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        //응답 코드값
        int responseCode=(int)result.get("code");
        //응답된 문자열
        String data=(String)result.get("data");
        //여기는 UI 스레드 이기 때문에 UI를 업데이트 할 수 있다.
        console.setText(data);//EditText에 출력하기
    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {
        //예외 메세지 읽어오기
        String data=(String)result.get("data");
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}

package com.gura.step16jsonparse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener, Util.RequestListener {

    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        result=findViewById(R.id.listView);

        Button getBtn=findViewById(R.id.getBtn);
        getBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String urlAddr="http://192.168.0.35:8865/spring05/android/getDetail.do";
        Util.sendGetRequest(0, urlAddr, null, this);
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        String data=(String)result.get("data");
        try {
            JSONObject obj=new JSONObject(data);
            int num=obj.getInt("num");
            String name=obj.getString("name");
            Boolean isMan=obj.getBoolean("isMan");
            String info="번호 : "+num+", 이름 : "+name+", 성별 : "+isMan;
            this.result.setText(info);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {

    }
}

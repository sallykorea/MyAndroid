package com.gura.step16jsonparse;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity implements Util.RequestListener{

    private TextView numTextView, nameTextView, addrTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        numTextView=findViewById(R.id.numTextView);
        nameTextView=findViewById(R.id.nameTextView);
        addrTextView=findViewById(R.id.addrTextView);

        //Intent 객체에 "num" 이라는 키값으로 담긴 회원의 번호 얻어내기
        int num=getIntent().getIntExtra("num", 0);

        Map<String, String> map=new HashMap<>();
        map.put("num", Integer.toString(num));

        String urlAddr="http://192.168.0.35:8865/spring05/android/member/detail.do";
        Util.sendGetRequest(0, urlAddr, map, this);

    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        String data=(String)result.get("data");
        try {
            JSONObject obj=new JSONObject(data);
            int num=obj.getInt("num");
            String name=obj.getString("name");
            String addr=obj.getString("addr");

            numTextView.setText(Integer.toString(num)); // setText() 메소드에 int type을 넣으면 layout resource 값을 전달하는게 된다. 즉, 다른 메소드가 불러와진다.
            nameTextView.setText(name);
            addrTextView.setText(addr);

        }catch (JSONException je){

        }
    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {

    }
}

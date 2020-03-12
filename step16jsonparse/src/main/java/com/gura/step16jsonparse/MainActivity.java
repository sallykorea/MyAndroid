package com.gura.step16jsonparse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Util.RequestListener {

    private ArrayAdapter<String> adapter;
    private List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //모델
        names=new ArrayList<>();
        //어답터
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        //ListView
        ListView listView=findViewById(R.id.listView);
        listView.setAdapter(adapter);

        //스프링 웹 서버에 요청하기
        String urlAddr="http://192.168.0.35:8865/spring05/android/getnames.do";
        Util.sendGetRequest(0, urlAddr, null, this);
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        //서버에서 응답받은 데이터가 들어온다 []
        String data=(String)result.get("data");
        //[]형식은 JSONArray에 대응 된다.
        try {
            JSONArray arr=new JSONArray(data);
            //반복문 돌면서
            for(int i=0; i<arr.length(); i++){
                String tmp=arr.getString(i);
                names.add(tmp);
            }
            //모델이 업데이트 되었다고 apater에게 알려주기
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {
        Toast.makeText(this, "실패!", Toast.LENGTH_SHORT).show();
    }
}

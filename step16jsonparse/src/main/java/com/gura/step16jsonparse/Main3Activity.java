package com.gura.step16jsonparse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener, Util.RequestListener {

    private ListView listView;
    private ArrayAdapter adapter;
    private List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        listView=findViewById(R.id.listView);
        names=new ArrayList<String>();

        adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);

        Button getListBtn=findViewById(R.id.getList);
        getListBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String urlAddr="http://192.168.0.35:8865/spring05/android/getList.do";
        Util.sendGetRequest(0, urlAddr, null, this);
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        String data=(String)result.get("data");
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
        try {
            JSONArray arr=new JSONArray(data);
            for (int i=0; i<arr.length(); i++){
                JSONObject obj=arr.getJSONObject(i);
                int num=obj.getInt("NUM");
                String name=obj.getString("NAME");
                String addr=obj.getString("ADDR");

                String info="번호 : "+num+", 이름 : "+name+", 성별 : "+addr;
                names.add(info);
            }
            adapter.notifyDataSetChanged();
        }catch (JSONException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {
        String msg=(String)result.get("data");
        Toast.makeText(this, "msg:"+msg, Toast.LENGTH_SHORT).show();
    }
}

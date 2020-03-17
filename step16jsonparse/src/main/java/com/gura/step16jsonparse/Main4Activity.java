package com.gura.step16jsonparse;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, Util.RequestListener {

    private ListView listView;
    private MemberAdapter adapter;
    private List<MemberDto> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        listView=findViewById(R.id.listView);

        list=new ArrayList<>();

        adapter=new MemberAdapter(this, R.layout.listview_cell, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        Button getListBtn=findViewById(R.id.getList);
        getListBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        String urlAddr="http://192.168.0.35:8865/spring05/android/member/list.do";
        Util.sendGetRequest(0, urlAddr, null, this); //요청하면 json 형식으로 data를 응답한다.
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        String data=(String)result.get("data");
        //Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
        try {
            JSONArray arr=new JSONArray(data);
            for (int i=0; i<arr.length(); i++){
                JSONObject obj=arr.getJSONObject(i);
                int num=obj.getInt("num");
                String name=obj.getString("name");
                //String addr=obj.getString("addr");

                MemberDto dto=new MemberDto(num, name);
                list.add(dto);
            }
            adapter.notifyDataSetChanged();
        }catch (JSONException e){
            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {
        String msg=(String)result.get("data");
        //Toast.makeText(this, "msg:"+msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) { // view 클릭한 셀view의 참조값 / i, id 클릭한 셀의 id값이 들어온다.
        //1. 클릭한 셀의 회원 번호를 읽어와서
        int num=list.get(i).getNum();

        Intent intent=new Intent(this, DetailActivity.class);
        intent.putExtra("num", num);
        startActivity(intent);
    }
}

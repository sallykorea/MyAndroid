package com.gura.step09camera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListActivity extends AppCompatActivity implements Util.RequestListener, View.OnClickListener {

    public static final String IMAGELIST_URL ="http://192.168.0.26:8865/spring05/android/image/list.do";
    public static final String IMAGELIST_URL_TEACHER ="http://192.168.0.15:8888/spring05/android/image/list.do";
    private ListView listView;
    private ImageAdapter adapter;
    private List<ImageDto> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView=findViewById(R.id.listView);
        list=new ArrayList<>();
        adapter=new ImageAdapter(this, R.layout.listview_cell, list);
        listView.setAdapter(adapter);

        Button takePicBtn=findViewById(R.id.takePicBtn);
        Button refreshBtn=findViewById(R.id.refreshBtn);

        takePicBtn.setOnClickListener(this);
        refreshBtn.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //activity생명 주기에 따라 onStop(), onPause() 메소드가 호출되었다가 다시 활성화 될때는 onStart(), onResume()메소드가 호출된다.
        // 따라서 해당 메소드를 오버라이드 해서 ImageList 요청을하면 새로고침 버튼을 누르지 않아도 가장 최근에 찍었던 사진도 가지고 올 수 있다.
        Util.sendGetRequest(0, IMAGELIST_URL, null, this);
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        //모델 클리어
        list.clear();
        String data=(String)result.get("data");
        try{
            JSONArray arr=new JSONArray(data);
            for(int i=0; i<arr.length(); i++){
                JSONObject obj=arr.getJSONObject(i);
                int num=obj.getInt("num");
                String writer=obj.getString("writer");
                String imagePath=obj.getString("imagePath");
                String regdate=obj.getString("regdate");
                ImageDto dto=new ImageDto();
                dto.setNum(num);
                dto.setImageUrl("http://192.168.0.26:8865/spring05"+imagePath);
                dto.setWriter(writer);
                dto.setRegdate(regdate);
                list.add(dto);
            }
            adapter.notifyDataSetChanged();
        }catch (JSONException je){

        }

    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {
        Toast.makeText(this, "실패!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.takePicBtn:
                //Activity 이동하기
                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case  R.id.refreshBtn:
                //다시 목록 받아오기
                Util.sendGetRequest(0, IMAGELIST_URL, null, this);
                break;
        }
    }
}

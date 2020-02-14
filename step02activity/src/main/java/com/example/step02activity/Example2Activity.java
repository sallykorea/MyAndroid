package com.example.step02activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Example2Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example2);
        //버튼의 참조값을 얻어와서 리스너 등록하기
        Button updateBtn=findViewById(R.id.updateBtn);
        Button deleteBtn=findViewById(R.id.deleteBtn);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) { //버튼을 클릭하면 해당 버튼의 참조값이 인자로 전달된다.
        // 눌러진 버튼의 id값을 읽어온다.
        int resId=v.getId();
        if (resId==R.id.updateBtn){//수정 버튼을 눌렀을 때
            Toast.makeText(this, "수정", Toast.LENGTH_SHORT).show();
        }else if (resId==R.id.deleteBtn){//삭제 버튼을 눌렀을 때
            Toast.makeText(this, "삭제", Toast.LENGTH_LONG).show();
        }
    }
}

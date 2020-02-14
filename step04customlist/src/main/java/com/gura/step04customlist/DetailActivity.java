package com.gura.step04customlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //DetailActivity 가 활성화 되는데 사용되었던 Intent 객체의 참조값 얻어오기
        Intent intent=getIntent();
        // "dto"라는 키값으로 전달된 Serializable type 객체의 참조값 얻어오기
        Serializable serial=intent.getSerializableExtra("dto");
        //원래 type으로 casting!
        CountryDto dto=(CountryDto)serial;

        /*
            위의 코드를 한줄로 적으면 아래와 같다.
            CountryDto dto=(CountryDto)getIntent().getSerializableExtra("dto");
         */

        // 전개된 레이아웃에서 UI 참조값 얻어오기
        ImageView imageView=findViewById(R.id.imageView);
        TextView textView=findViewById(R.id.textView);
        Button confirmBtn=findViewById(R.id.confirmBtn);

        // 이미지와 내용 출력하기
        imageView.setImageResource(dto.getResId());
        textView.setText(dto.getContent());

        //버튼에 리스너 등록하기
        confirmBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        //엑티비티 종료 시키기
        finish();
    }
}

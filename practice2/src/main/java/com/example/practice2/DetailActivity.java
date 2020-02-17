package com.example.practice2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        InfoDto dto=(InfoDto)getIntent().getSerializableExtra("dto");

        ImageView imageView=findViewById(R.id.imageView);
        TextView nameTextView=findViewById(R.id.nameTextView);
        TextView addrTextView=findViewById(R.id.addrTextView);
        TextView digitTextView=findViewById(R.id.digitTextView);
        Button confirmBtn=findViewById(R.id.confirmBtn);

        imageView.setImageResource(dto.getResId());
        nameTextView.setText(dto.getName());
        addrTextView.setText(dto.getAddr());
        digitTextView.setText(dto.getDigit());

        confirmBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}

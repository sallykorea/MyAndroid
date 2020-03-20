package com.gura.step17sqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent=getIntent();
        TodoDto dto=(TodoDto)intent.getSerializableExtra("todoDto");

        TextView numTextView=findViewById(R.id.numTextView);
        TextView contentTextView=findViewById(R.id.contentTextView);
        TextView regdateTextView=findViewById(R.id.regdateTextView);

        numTextView.setText(Integer.toString(dto.getNum()));
        contentTextView.setText(dto.getContent());
        regdateTextView.setText(dto.getRegdate());

        Button updateBtn=findViewById(R.id.updateBtn);
    }
}

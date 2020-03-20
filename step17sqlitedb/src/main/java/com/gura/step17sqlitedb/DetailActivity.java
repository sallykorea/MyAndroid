package com.gura.step17sqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private DBHelper helper;
    private EditText contentEditText;
    private TextView numTextView, regdateTextView;
    private TodoDto dto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        helper=new DBHelper(this, "MyDB.sqlite", null, 1);

        Intent intent=getIntent();
        dto=(TodoDto)intent.getSerializableExtra("todoDto");

        numTextView=findViewById(R.id.numTextView);
        contentEditText=findViewById(R.id.contentEditText);
        regdateTextView=findViewById(R.id.regdateTextView);

        numTextView.setText(Integer.toString(dto.getNum()));
        contentEditText.setText(dto.getContent());
        regdateTextView.setText(dto.getRegdate());

        Button updateBtn=findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.updateBtn:

                String sql="UPDATE todo" +
                        " SET content=?" +
                        " WHERE num=?";
                String content=contentEditText.getText().toString();
                Object[] args={content, dto.getNum()};
                SQLiteDatabase db=helper.getWritableDatabase();
                db.execSQL(sql, args);
                db.close();

                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

}

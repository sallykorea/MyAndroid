package com.gura.step12sharedpref;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    private EditText editText;
    Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //EditText 참조값 얻어와서필드에 저장하기
        editText=findViewById(R.id.editText);
        //Button 참조값 얻어와서 리스너 등록하기
        Button saveBtn=findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);


        sw=findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(this);

        SharedPreferences pref=getSharedPreferences("info", MODE_PRIVATE);
        // .getString( key 값, default 값)
        String myName=pref.getString("myName", "empty");
        if(myName.equals("empty")){
            return;
        }
        //저장된 이름을 editText 에 출력하기
        editText.setText(myName);
        //스위치 체크 여부 읽어오기
        boolean isChecked=pref.getBoolean("isChecked", false);
        //스위치의 체크 상태 반영하기
        sw.setChecked(isChecked);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //선택된 메뉴 아이템의 아이디 값을 읽어온다.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(this, SettingsActivity.class);
            startActivity(intent);

        }else if (id ==R.id.finish){
            //액티비티 종료 시키기
            finish();
        }else if (id==R.id.start){

        }

        return true;
    }

    @Override
    public void onClick(View v) {
        //입력한 문자열 읽어오기
        String inputName=editText.getText().toString();
        Boolean isChecked=sw.isChecked();
        /*
            [SharedPreferences 를 활용해서 저장하기]
            - app 자체에 정보를 저장하고 싶은 경우 사용한다. 단, filesystem에 저장하고 불러오기 때문에 약간 동작이 느리다.
            - info.xml 이라는 문서를 만들고 myName이라는 key값으로 value를 저장한다.
            - .commit()을 해야 실제로 저장이된다.
         */
        SharedPreferences pref=getSharedPreferences("info", MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString("myName", inputName);
        editor.putBoolean("isChecked", isChecked);
        editor.commit();
        new AlertDialog.Builder(this)
                        .setMessage("저장했습니다.")
                        .setNeutralButton("확인", null)
                        .show();

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            sw.setText("ON");
        }else {
            sw.setText("OFF");
        }
    }

    //Setting 엑티비티에서 저장했던 값을 읽어내는 메소드
    public void readSettings(View v){
        //디폴트로 사용하는 SharedPreferences 객체의 참조값 얻어오기
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(this);
        //signature 라는 키값으로 저장된 문자열 읽어오기
        String signature=pref.getString("signature","");
        //reply라는 키값으로 저장된 boolean 값 읽어오기
        String reply=pref.getString("reply", "");
        Boolean sync=pref.getBoolean("sync", false);

        String info="signature:"+signature+" | reply:"+reply;
        Toast.makeText(this,info,Toast.LENGTH_SHORT).show();
    }

}

package com.gura.step12sharedpref;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //res/layout/settings_activity.xml 문서를 전개해서 화면 구성하기
        setContentView(R.layout.settings_activity);
        /*
            id가 settings인 레이아웃(FrameLayout)에
            SettingsFragment로 화면 구성하게 하기
            (settings_activity에 fagment 의 layout을 보여달라는 의미)
         */
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        //up button 동작 가능하도록 한다.(Manifest.xml에서 activity의 계층구조도 설정해주어야한다.)
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }


    //FrameLayout 제출 프레그먼트
    public static class SettingsFragment extends PreferenceFragmentCompat {
        //fragment가 처음 활성화 될때 호출되는 메소드
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            //res/layout/root_preferences.xml 문서를 전개해서 fragment UI 구성하기
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences가 바뀌었는지 감시할 리스너 등록하기
        pref.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences가 바뀌었는지 감시할 리스너 등록하기
        pref.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences pref, String key) {
        //key : 바뀐 설정의 key 값이 전달된다.
        if (key.equals("signature")){
            String signature=pref.getString(key, "");
            Toast.makeText(this, signature, Toast.LENGTH_SHORT).show();
        }

    }

}
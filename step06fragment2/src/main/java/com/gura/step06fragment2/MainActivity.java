package com.gura.step06fragment2;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gura.step06fragment2.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //레이아웃 구성하기
        setContentView(R.layout.activity_main);

        //Pager adapter 객체 생성하기(fragment를 공급해준다.)
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        //ViewPager 객체의 참조값 얻어오기(ViewPager에 fragment를 하나씩 공급해준다.)
        ViewPager viewPager = findViewById(R.id.view_pager);
        //ViewPager 에 PagerAdapter 객체 연결하기
        viewPager.setAdapter(sectionsPagerAdapter);  //sectionsPagerAdapter에 overide 되어 있는 메소드를 필요할때 호출한다.

        //Tab 레이아웃 객체의 참조값 얻어오기
        TabLayout tabs = findViewById(R.id.tabs);
        //Tab 과 ViewPager를 함께 연계해서 쓰도록 설정
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
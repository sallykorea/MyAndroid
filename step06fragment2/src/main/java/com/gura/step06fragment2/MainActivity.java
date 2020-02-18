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
import android.widget.ListView;

import com.gura.step06fragment2.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<CountryDto> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //레이아웃 구성하기
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listview);
        countries=new ArrayList<>();
        countries.add(new CountryDto(R.drawable.austria, "오스트리아", "오스트리아"));
        countries.add(new CountryDto(R.drawable.belgium, "벨기에", "벨기에"));
        countries.add(new CountryDto(R.drawable.brazil, "브라질", "브라질"));
        countries.add(new CountryDto(R.drawable.france, "프랑스", "프랑스"));
        countries.add(new CountryDto(R.drawable.germany, "독일", "독일"));
        countries.add(new CountryDto(R.drawable.greece, "그리스", "그리스"));
        countries.add(new CountryDto(R.drawable.italy, "이탈리아", "이탈리아"));
        countries.add(new CountryDto(R.drawable.japan, "일본", "일본"));
        countries.add(new CountryDto(R.drawable.poland, "폴란드", "폴란드"));
        countries.add(new CountryDto(R.drawable.korea, "한국", "한국"));

        //Pager adapter 객체 생성하기(fragment를 공급해준다.)
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(countries, getSupportFragmentManager());

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
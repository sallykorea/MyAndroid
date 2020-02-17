package com.gura.step04customlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //필드
    ListView listView;
    List<CountryDto> countries;
    CountryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listView);
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
        countries.add(new CountryDto(R.drawable.spain, "스페인", "스페인"));
        countries.add(new CountryDto(R.drawable.usa, "미국", "미국"));

        adapter=new CountryAdapter(this, R.layout.listview_cell, countries);

        listView.setAdapter(adapter); //adapter가 cell view를 공급해 준다.
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        Intent intent=new Intent(this, DetailActivity.class);
        CountryDto dto=countries.get(i);
        intent.putExtra("dto", dto);
        startActivity(intent);
    }
}

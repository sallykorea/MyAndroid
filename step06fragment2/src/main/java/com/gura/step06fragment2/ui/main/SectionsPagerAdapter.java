package com.gura.step06fragment2.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.constraintlayout.widget.Placeholder;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gura.step06fragment2.CountryDto;
import com.gura.step06fragment2.R;

import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
   private List<CountryDto> countries;

    public SectionsPagerAdapter(List<CountryDto> countries, FragmentManager fm) {
        super(fm);
        this.countries = countries;
    }

    //인자로 전달하는 인덱스에 해당하는 fragment 객체의 참조값을 리턴해주는 메소드
    @Override
    public Fragment getItem(int position) {
        //position 인덱스에 해당하는 이미지 리소스 아이디
        CountryDto dto=countries.get(position);
        //position 인덱스에 해당하는 fragment의 참조값 얻어내서
        PlaceholderFragment fr=PlaceholderFragment.newInstance(dto);
        //리턴해주기
        return fr; // 한줄로 작성 : PlaceholderFragment.newInstance(imgs[position]);
    }

    //인자로 전달되는 인덱스에 해당하는 문자열(페이지 제목, tab 제목)을 리턴해주는 메소드
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
       String title=countries.get(position).getName();

        return title;
    }

    //전체 페이지의 갯수를 리턴해준다.
    @Override
    public int getCount() {
        // Show 5 total pages.
        return countries.size();
    }
}
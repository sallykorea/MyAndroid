package com.gura.step06fragment2.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.gura.step06fragment2.R;

/**
 * A placeholder fragment containing a simple view.
 */
/*
    fragment는 페이지 갯수만큼 다 만든다.
    뒤로가기 했을 때 만들어 두었던 fragment를 호출된다.
    onCreate() 메소드는 activity가 죽기 전까지는 다시 호출되지 않는다.
    onCreateView() 메소드는 페이지가 바뀔때 마다 호출된다.
 */
public class PlaceholderFragment extends Fragment {
    //Fragment 객체를 생성해서 리턴해주는 static 메소드
    public static PlaceholderFragment newInstance(int resId) {
        PlaceholderFragment fr=new PlaceholderFragment();

        Bundle bundle=new Bundle();
        bundle.putInt("resId", resId);
        //Fragment 에 전달할 bundle 객체
        fr.setArguments(bundle);
        return fr;
    }

    //이미지 리소스 아이디를 담을 필드
    private int resId;

    //1. fragment가 최초 사용될때 호출되는 메소드
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setArguments()로 frgment에 전달할 bundle객체를 넣어주면  getArguments()메소드로 읽어내야한다.
        resId=getArguments().getInt("resId");

    }
    //2. fragment가 활성화 될때마다 호출되는 메소드
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // res/layout/fragment_main.xml 문서를 전개해서 view객체를 만든다.
        View view=inflater.inflate(R.layout.fragment_main, container, false);
        // 이미지뷰의 참조값 얻어와서
        ImageView imageView=view.findViewById(R.id.imageView);
        // 이미지 출력하기
        imageView.setImageResource(resId);
        //view 객체를 리턴해주기
        return view;
    }
}
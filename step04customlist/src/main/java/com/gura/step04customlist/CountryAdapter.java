package com.gura.step04customlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CountryAdapter extends BaseAdapter {
    //필드
    private Context context;
    private int layoutRes;
    private List<CountryDto> list;
    private LayoutInflater inflater; //레이아웃 전개자 객체의 참조값이 담길 field

    //생성자
    public CountryAdapter(Context context, int layoutRes, List<CountryDto> list){
        this.context=context;
        this.layoutRes=layoutRes;
        this.list=list;
        /*
            [레이아웃 전개자 객체]
            xml 로 정의한 레이아웃 정보를 실제로 전개해서
            View 객체로 만들어 주는 객체 (View를 만들어야 화면에 보이게 할 수 있다.)
         */
        inflater=LayoutInflater.from(context);
    }

    /*
        [list View에 연결할 Adapter Class 정의 하기]
        - BaseAdapter 추상 클래스를 상속 받아서 만든다
     */
    //전체 모델의 갯수를 리턴해준다.
    @Override
    public int getCount() {
        return list.size();
    }

    // i index에 해당하는 아이템(data)를 리턴해 준다.
    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    // i index에 해당하는 아이템(data)의 id가 있으면 리턴해 준다.
    @Override
    public long getItemId(int i) {
        return i;
    }

    // i index에 해당하는 cell View를 리턴해 준다.
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //처음에는 인자로 선언된 지역변수 view에 null이 들어있다.
        if(view==null){
            //레이아웃 전개자 객체를 이용해서 View 객체를 만든다.
            view=inflater.inflate(layoutRes, viewGroup, false);
        }
        //View 에서 원하는 UI의 참조값을 얻어 낸다.
        ImageView imageView=view.findViewById(R.id.imageView);
        TextView textView=view.findViewById(R.id.textView);
        // i 번째 인덱스에 해당하는 데이터를 얻어온다.
        CountryDto dto=list.get(i);
        //View 에 데이터를 출력한다.
        imageView.setImageResource(dto.getResId());
        textView.setText(dto.getName());

        return view;
    }
    /*
        새로운 cell을 만드는 것이 아니라 썻던 cell을 재활용 하는 것이다.

     */
}

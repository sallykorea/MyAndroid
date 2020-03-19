package com.gura.step09camera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    //필드
    private Context context;
    private int layoutRes;
    private List<ImageDto> list;
    private LayoutInflater inflater; //레이아웃 전개자 객체의 참조값이 담길 field

    //생성자(마음대로 만들 수 있음)
    public ImageAdapter(Context context, int layoutRes, List<ImageDto> list){
        this.context=context;
        this.layoutRes=layoutRes;
        this.list=list;
        /*
            [레이아웃 전개자 객체]
            xml 로 정의한 레이아웃 정보를 실제로 전개해서
            View 객체로 만들어 주는 객체 (View를 만들어야 화면에 보이게 할 수 있다.)
            (화면 설계(.xml)를 바탕으로 실제 화면에 출력할 수 있는 view 객체를 생성하는 역할을 한다.)
         */
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //처음에는 인자로 선언된 지역변수 view에 null이 들어있다.
        if(view==null){
            //레이아웃 전개자 객체를 이용해서 View 객체를 만든다.
            view=inflater.inflate(layoutRes, viewGroup, false);
        }

        // i 번째 인덱스에 해당하는 데이터를 얻어온다.
        ImageDto dto=list.get(i);

        //View 에서 원하는 UI의 참조값을 얻어 낸다.
        TextView textWriter=view.findViewById(R.id.textWriter);
        TextView textRegdate=view.findViewById(R.id.textRegdate);
        ImageView imageView=view.findViewById(R.id.imageView);

        //View 에 데이터를 출력한다.
        Glide.with(context)
                .load(dto.getImageUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);

        textWriter.setText("작성자:"+dto.getWriter());
        textRegdate.setText("등록일:"+dto.getRegdate());

        return view;
    }
}

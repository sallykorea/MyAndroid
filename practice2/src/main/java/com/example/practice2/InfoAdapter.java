package com.example.practice2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class InfoAdapter extends BaseAdapter {

    private Context context;
    private int layoutRes;
    private List<InfoDto> list;
    private LayoutInflater inflater;
    private static final String TAG = "MainActivity";

    public InfoAdapter(Context context, int layoutRes, List<InfoDto> list) {
        this.context = context;
        this.layoutRes = layoutRes;
        this.list = list;

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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view=inflater.inflate(layoutRes, viewGroup, false);
        }
        ImageView imageView=view.findViewById(R.id.imageView);
        TextView nameTextView=view.findViewById(R.id.nameTextView);
        TextView addrTextView=view.findViewById(R.id.addrTextView);
        TextView digitTextView=view.findViewById(R.id.digitTextView);
        Button button=view.findViewById(R.id.modifyBtn);

        /*
            [button에만 listener 달아주기]
            View.OnClickListener를 implment한 익명 클래스를 생성하면서
            button에 OnClickListener 달아주기

         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailActivity.class);
                InfoDto dto=list.get(i); // Local innerClass에서는 final 상수값만 참조 가능하다
                intent.putExtra("dto", dto);
                context.startActivity(intent);
            }
        });

        InfoDto dto=list.get(i);

        imageView.setImageResource(dto.getResId());
        nameTextView.setText(dto.getName());
        addrTextView.setText(dto.getAddr());
        digitTextView.setText(dto.getDigit());

        return view;
    }
}

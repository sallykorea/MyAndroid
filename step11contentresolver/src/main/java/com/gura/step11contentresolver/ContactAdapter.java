package com.gura.step11contentresolver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends BaseAdapter {

    private Context context;
    private int layoutRes;
    private List<ContactDto> list;
    private LayoutInflater inflater;

    public ContactAdapter(Context context, int layoutRes, List<ContactDto> list){
        this.context=context;
        this.layoutRes=layoutRes;
        this.list=list;

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
        return list.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            //레이아웃 전개자 객체를 이용해서 View 객체를 만든다.
            view=inflater.inflate(layoutRes, viewGroup, false);
        }
        //View 에서 원하는 UI의 참조값을 얻어 낸다.
        //TextView idTextView=view.findViewById(R.id.idTextView);
        TextView idTextView=(TextView)view.findViewById(R.id.idTextView);
        TextView nameTextView=(TextView)view.findViewById(R.id.nameTextView);
        TextView phoneTextView=(TextView)view.findViewById(R.id.phoneTextView);
        // i 번째 인덱스에 해당하는 데이터를 얻어온다.
        ContactDto dto=list.get(i);
        //View 에 데이터를 출력한다.
        //idTextView.setText(toString(dto.getId()));
        idTextView.setText(Long.toString(dto.getId()));
        nameTextView.setText(dto.getName());
        phoneTextView.setText(dto.getPhoneNumber());

        return view;
    }
}

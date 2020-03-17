package com.gura.step16jsonparse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;


public class MemberAdapter extends BaseAdapter {
    //필드
    private Context context;
    private int layoutRes;
    private List<MemberDto> list;
    private LayoutInflater inflater;

    //생성자
    public MemberAdapter(Context context, int layoutRes, List<MemberDto> list){
        this.context=context;
        this.layoutRes=layoutRes;
        this.list=list;
        inflater= LayoutInflater.from(context);
    }

    //전체 모델의 갯수를 리턴해준다.
    @Override
    public int getCount() {
        return list.size();
    }

    // i index에 해당하는 아이템(data)를 리턴해 준다.
    @Override
    public Object getItem(int i) {
        return list.get(i); //리턴해주기
    }

    // i index에 해당하는 아이템(data)의 id가 있으면 리턴해 준다.(주로 이벤트 처리할때 사용되는 메소드 이다.)
    // ex) DB에 있는 data 에 primary key가 적용된 경우
    @Override
    public long getItemId(int i) {
        //번호가 PK(Primary Key) 이므로 회원의 번호를 리턴해준다.
        return list.get(i).getNum();
    }

    // i index에 해당하는 cell View를 리턴해 준다.
    @Override
    public View getView(int i, View  view, ViewGroup viewGroup) {
        //처음에는 인자로 선언된 지역변수 view에 null이 들어있다.
        if(view==null){
            //레이아웃 전개자 객체를 이용해서 View 객체를 만든다.
            view=inflater.inflate(layoutRes, viewGroup, false);
        }
        //View 에서 원하는 UI의 참조값을 얻어 낸다
        TextView name=view.findViewById(R.id.nameTextView);

        String result=list.get(i).getName();
        name.setText(result);

        return view;
    }
}

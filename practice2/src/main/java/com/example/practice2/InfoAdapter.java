package com.example.practice2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class InfoAdapter extends BaseAdapter {

    private Context context;
    private int layoutRes;
    private List<InfoDto> list;
    private LayoutInflater inflater;

    public InfoAdapter(Context context, int layoutRes, List<InfoDto> list) {
        this.context = context;
        this.layoutRes = layoutRes;
        this.list = list;

        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}

package com.marj4n.sqllite_learn.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.marj4n.sqllite_learn.R;
import com.marj4n.sqllite_learn.model.Data;

import java.util.List;

public class Adapter extends BaseAdapter {


    private LayoutInflater inflater;
    private final List<Data> items;

    public Adapter( List<Data> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(inflater == null){
            inflater = LayoutInflater.from(viewGroup.getContext());
        }

        if(view == null){
            view = inflater.inflate(R.layout.list_row,null);
        }
        TextView tId = view.findViewById(R.id.id);
        TextView tName = view.findViewById(R.id.name);
        TextView tAddress = view.findViewById(R.id.address);

        Data data = items.get(i);
        tId.setText(data.getId());
        tName.setText(data.getName());
        tAddress.setText(data.getAddress());

        return view;
    }
}
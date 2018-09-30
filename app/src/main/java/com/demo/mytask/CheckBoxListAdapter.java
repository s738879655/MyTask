package com.demo.mytask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sudha on 18-Aug-18.
 */

public class CheckBoxListAdapter extends BaseAdapter {

    private final List<checkBoxListDetail> dish_lists;
    private final Context context;

    CheckBoxListAdapter(Context context, List<checkBoxListDetail> bill_lists) {
        this.dish_lists = bill_lists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dish_lists.size();
    }

    @Override
    public Object getItem(int position) {
        return dish_lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {


        if(view==null)
            view= LayoutInflater.from(context).inflate(R.layout.checkboxlist,parent,false);

         CheckBox checkBox=view.findViewById(R.id.checkBox1);




        final checkBoxListDetail bill_detail = (checkBoxListDetail) getItem(position);

    final String nameOfButton= bill_detail.getBtnName();

    checkBox.setText(nameOfButton);

        return view;
    }

}

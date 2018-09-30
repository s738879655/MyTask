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

public class ToppingsListAdapter extends BaseAdapter {

    private final List<ToppingListDetails> dish_lists;
    private final Context context;

    ToppingsListAdapter(Context context, List<ToppingListDetails> bill_lists) {
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
            view= LayoutInflater.from(context).inflate(R.layout.topping_list,parent,false);
        CheckBox btnName=view.findViewById(R.id.toppingButtoninLay);
        TextView btnValue=view.findViewById(R.id.toppingValueinLay);




        final ToppingListDetails bill_detail = (ToppingListDetails) getItem(position);

    final String nameOfButton= bill_detail.getToppingname();
    final String valueofButton = bill_detail.getToppingprice();

    btnName.setText(nameOfButton);
    btnValue.setText("₹ "+valueofButton);
        return view;
    }

}

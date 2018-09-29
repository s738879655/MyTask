package com.demo.mytask;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by sudha on 18-Aug-18.
 */

public class buttonListAdapter extends BaseAdapter {

    private final List<buttonListDetail> dish_lists;
    private final Context context;

    buttonListAdapter(Context context, List<buttonListDetail> bill_lists) {
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
            view= LayoutInflater.from(context).inflate(R.layout.btnPriceList,parent,false);
        RadioButton btnName=view.findViewById(R.id.buttonRadio);
        TextView btnValue=view.findViewById(R.id.buttonValue);




      //  final buttonListDetail bill_detail = (buttonListDetail) getItem(position);

//        final String nameOfButton= bill_detail.getBtnName();
  //      final String valueofButton = bill_detail.getBtnValue();

        return view;
    }

}

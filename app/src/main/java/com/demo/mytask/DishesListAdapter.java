package com.demo.mytask;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by sudha on 18-Aug-18.
 */

public class DishesListAdapter extends BaseAdapter {

    private final List<DishDetail> dish_lists;
    private final Context context;

    DishesListAdapter(Context context, List<DishDetail> bill_lists) {
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
            view= LayoutInflater.from(context).inflate(R.layout.lay,parent,false);
        ImageView dishImage=view.findViewById(R.id.dishImage);
        TextView itemName = view.findViewById(R.id.itemName);
        TextView quantityHead = view.findViewById(R.id.quantityHead);
        TextView quantityAmount = view.findViewById(R.id.quantityAmount);
        TextView priceHead = view.findViewById(R.id.priceHead);
        final TextView priceValue = view.findViewById(R.id.priceValue);
        TextView timeHead = view.findViewById(R.id.timeHead);
        TextView timeValue = view.findViewById(R.id.timeValue);
        TextView customMess = view.findViewById(R.id.customMess);

        RelativeLayout linearLayout = view.findViewById(R.id.vlinearlayout);

        final DishDetail bill_detail = (DishDetail) getItem(position);

        final String nameOfItem = bill_detail.getItemName();
        final String amountOfQuantity = bill_detail.getQuantityAmount();
        final String price = bill_detail.getPriceValue();
        final String timeVal = bill_detail.getTimeValue();
        final String customStatus = bill_detail.getCustomStatus();
        final String imageUri=bill_detail.getImageUri();





        if(customStatus.equalsIgnoreCase("true")){
            customMess.setVisibility(View.VISIBLE);

        }



        itemName.setText(nameOfItem);
        quantityAmount.setText(amountOfQuantity);
        priceValue.setText(price);
        timeValue.setText(timeVal);




        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(context,Extend_Dish.class);
           /*     in.putExtra();
                in.putExtra("billno",billno);
                in.putExtra("type",type);
                in.putExtra("name",name);
                in.putExtra("status",status);
                in.putExtra("total",total);
                in.putExtra("day",day);
                in.putExtra("month",month);
                in.putExtra("date",date);
                in.putExtra("rate",bill_detail.getRate());
                in.putExtra("tax",bill_detail.getTax());
                in.putExtra("jobstatus",bill_detail.getJobstatus());
                in.putExtra("address",bill_detail.getAddress());
                in.putExtra("ofwhat",bill_detail.getOfwhat());
                in.putExtra("whereto",bill_detail.getWhereto());
                in.putExtra("jobstatus",bill_detail.getJobstatus());
                in.putExtra("stime",bill_detail.getStarttime());
                in.putExtra("etime",bill_detail.getEndtime());
                in.putExtra("discount",bill_detail.getDiscount());
                context.startActivity(in);
                  */
           Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}

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
        final String checkboxStatus=bill_detail.getCheckboxStatus();
        final String buttonStatus=bill_detail.getCommentStatus();
        final String commentStatus=bill_detail.getButtonStatus();






        if(customStatus.equalsIgnoreCase("true"))
        {

            customMess.setVisibility(View.VISIBLE);

        }
        if((!(amountOfQuantity.trim()).equalsIgnoreCase("0"))&&amountOfQuantity!=null)
        {
            quantityAmount.setVisibility(View.VISIBLE);
            quantityHead.setVisibility(View.VISIBLE);
        }


        if((!(timeVal.trim()).equalsIgnoreCase("0"))&&timeVal!=null)
        {
           timeHead.setVisibility(View.VISIBLE);
           timeValue.setVisibility(View.VISIBLE);
        }



        itemName.setText(nameOfItem);
        quantityAmount.setText(amountOfQuantity);
        priceValue.setText(" ₹ "+price);
        timeValue.setText(timeVal);




        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                       Intent in = new Intent(context,Extend_Dish.class);
        in.putExtra("itemName",nameOfItem);
        in.putExtra("quantityAmount",amountOfQuantity);
        in.putExtra("priceValue",price);
        in.putExtra("timeValue",timeVal);
        in.putExtra("customStatus",customStatus);
        in.putExtra("checkStatus",checkboxStatus);
        in.putExtra("buttonStatus",buttonStatus);
        in.putExtra("commentStatus",commentStatus);
        in.putExtra("imageUri",imageUri);

        context.startActivity(in);

        Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
    }
});

        return view;
    }

}

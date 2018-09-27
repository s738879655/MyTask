package com.demo.mytask;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddItem extends AppCompatActivity implements NumberPicker.OnValueChangeListener{

    private TextView timechoose,addmorebtn,addmoreingredient;
    private ImageView imagechoos;
    CheckBox buttoncb,commentcb,checkcb;
    FrameLayout fl4,fl5;
    RelativeLayout ll,rl2;
    ListView lv,lv2;
    int  newHeight=0;
    ArrayList<String> btnlist,ingredientlist;
    MyItemRecyclerViewAdapter myAdapter;MyItemRecyclerViewAdapter2 myAdapter2;
    int h = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);


        timechoose = findViewById(R.id.itemtime);
        imagechoos = findViewById(R.id.timechoos);
        addmorebtn = findViewById(R.id.addmorebtns);
        addmoreingredient = findViewById(R.id.addmoreingredient);

        buttoncb = findViewById(R.id.buttoncb);
        ll = findViewById(R.id.lllist);
        commentcb = findViewById(R.id.commentcb);
        checkcb = findViewById(R.id.checkboxcb);

        lv = findViewById(R.id.buttonlist);
        lv2 = findViewById(R.id.ingridientlist);
        rl2 = findViewById(R.id.rl2);
        btnlist = new ArrayList<String>();
        ingredientlist = new ArrayList<String>();
        btnlist.clear();
        ingredientlist.clear();


        myAdapter = new MyItemRecyclerViewAdapter(this,btnlist);
        myAdapter2 = new MyItemRecyclerViewAdapter2(this,ingredientlist);


        lv2.setAdapter(myAdapter2);
        lv.setAdapter(myAdapter);

        fl4 = findViewById(R.id.fl4);
        fl5 = findViewById(R.id.fl5);
        buttoncb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    fl4.setVisibility(View.VISIBLE);
                    btnlist.clear();
                    btnlist.add("2");
                    myAdapter.notifyDataSetChanged();
                }
                if(!isChecked){
                    btnlist.clear();
                    myAdapter.notifyDataSetChanged();
                    setListViewHeightBasedOnChildren(lv);
                    fl4.setVisibility(View.GONE);
                }
            }
        });

        checkcb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ingredientlist.clear();
                    setListViewHeightBasedOnChildren2(lv2);
                    rl2.setVisibility(View.VISIBLE);
                }else if(!isChecked){
                    rl2.setVisibility(View.GONE);
                }
            }
        });

        imagechoos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });timechoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });

        addmorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddItem.this,"Clicked",Toast.LENGTH_SHORT).show();
                addrow();
            }
        });
        addmoreingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredientlist.add("2");
                myAdapter2.notifyDataSetChanged();
                setListViewHeightBasedOnChildren2(lv2);
            }
        });


    }
    private void setListViewHeightBasedOnChildren(ListView listView) {
        Log.e("Listview Size ", "" + listView.getCount());
        MyItemRecyclerViewAdapter listAdapter = (MyItemRecyclerViewAdapter) listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    private void setListViewHeightBasedOnChildren2(ListView listView) {
        Log.e("Listview Size ", "" + listView.getCount());
        MyItemRecyclerViewAdapter2 listAdapter = (MyItemRecyclerViewAdapter2) listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    void addrow(){
        btnlist.add("2");
        myAdapter.notifyDataSetChanged();
        setListViewHeightBasedOnChildren(lv);
//        h = ll.getHeight();
//        newHeight = fl4.getHeight();
//        Log.d("DataSetChanged",newHeight+"Changed "+h);
//        ll.getLayoutParams().height = newHeight;


    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        Log.i("value is",""+newVal);

    }

    public void show()
    {

        final Dialog d = new Dialog(AddItem.this);
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.timepick);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker nph = (NumberPicker) d.findViewById(R.id.numberpicker1);
        final NumberPicker npm = (NumberPicker) d.findViewById(R.id.numberpicker2);
        final NumberPicker nps = (NumberPicker) d.findViewById(R.id.numberpicker3);
        nph.setMaxValue(12); // max value 100
        nph.setMinValue(0);   // min value 0
        npm.setMaxValue(59);
        npm.setMinValue(0);
        nps.setMaxValue(59);
        nps.setMinValue(0);
        nph.setWrapSelectorWheel(false);
        nph.setOnValueChangedListener(this);
        npm.setWrapSelectorWheel(false);
        npm.setOnValueChangedListener(this);
        nps.setWrapSelectorWheel(false);
        nps.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                int hr = nph.getValue();
                int min = npm.getValue();
                int sec = nps.getValue();
                String s="";
                if(hr!=0)
                    s=hr+"hr ";
                if(min != 0)
                    s=s+min+"min ";
                if(sec != 0)
                    s=s+sec+"sec";

                timechoose.setText(s); //set the value to textview
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss(); // dismiss the dialog
            }
        });
        d.show();


    }
}

//class MyAdapter extends ArrayAdapter {
//
//    ArrayList<String> arrayList;
//    Context context;
//
//    public MyAdapter(@NonNull Context context, int resource,ArrayList<String> arrayList) {
//        super(context, resource);
//        this.context = context;
//        this.arrayList = arrayList;
//    }
//
//    public View gegtView(int posotion, View v, ViewGroup p){
//        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View row = layoutInflater.inflate(R.layout.button_list,p,false);
//
//        TextView tv1 = row.findViewById(R.id.buttonname);
//        TextView tv2 = row.findViewById(R.id.buttonname2);
//
//        return row;
//    }
//}

class MyItemRecyclerViewAdapter extends BaseAdapter {

    private final List<String> button;
    private final Context context;


    public MyItemRecyclerViewAdapter(Context contex, ArrayList<String> items) {
        button = items;
        context = contex;
    }

    @Override
    public int getCount() {
        return button.size();
    }

    @Override
    public Object getItem(int position) {
        return button.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Log.d("dfdad","Enetred in getView");

        if(view==null)
            view=LayoutInflater.from(context).inflate(R.layout.button_list,parent,false);

        TextView tv1 = view.findViewById(R.id.buttonname);
        TextView tv2 = view.findViewById(R.id.buttonname2);
        button.get(position);
        tv2.setText("");

        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}class MyItemRecyclerViewAdapter2 extends BaseAdapter {

    private final List<String> ingridientlist;
    private final Context context;


    public MyItemRecyclerViewAdapter2(Context contex, ArrayList<String> items) {
        ingridientlist = items;
        context = contex;
    }

    @Override
    public int getCount() {
        return ingridientlist.size();
    }

    @Override
    public Object getItem(int position) {
        return ingridientlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Log.d("dfdad","Enetred in getView");

        if(view==null)
            view=LayoutInflater.from(context).inflate(R.layout.ingridient_list,parent,false);

        TextView tv1 = view.findViewById(R.id.ingridientname);
        ingridientlist.get(position);

        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}

package com.demo.mytask;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Extend_Dish extends AppCompatActivity {

    private TextView itemName1,quantityAmount1,priceValue1,timeValue1,quantityHead1,priceHead1,timeHead1,btnListHead,btnListNote,checkBoxListHead,checkBoxListNote,commentHead;
    private Button canbtn,addtocart,placeorder;
    LinearLayout listView1,listView2,listView3;
    TextView quanadded,tv_total,tv_subtotal,discount1,disclaimerTextview,toppingListHead,toppingListNote;
    EditText tv_message;
    GridView gridView;
    ImageView dishimage,vegimage;
    GridView gridView1,gridView3;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
    DatabaseReference databaseReference2;
    private ArrayList<buttonListDetail> btn_details= new ArrayList<>();
    private ArrayList<checkBoxListDetail> check_Box_details= new ArrayList<>();
    private ArrayList<ToppingListDetails> topping_Box_details= new ArrayList<>();
    private buttonListAdapter adapter;
    private CheckBoxListAdapter adapter1;
    private ToppingsListAdapter adapter2;
    RadioGroup radioGrp;
    String price;int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend_dish);

        addtocart = findViewById(R.id.addMoreItems);
        placeorder = findViewById(R.id.btn_place_order);
        quanadded = findViewById(R.id.quantityAddedValue);

        final String nameOfItem = getIntent().getStringExtra("itemName");
        final String amountOfQuantity = getIntent().getStringExtra("quantityAmount");
        final String[] price = {getIntent().getStringExtra("priceValue")};
        final String timeVal = getIntent().getStringExtra("timeValue");
        final String customStatus = getIntent().getStringExtra("customStatus");
        final String imageUri = getIntent().getStringExtra("imageUri");
        final String checkboxStatus = getIntent().getStringExtra("checkStatus");
        final String buttonStatus = getIntent().getStringExtra("buttonStatus");
        final String commentStatus = getIntent().getStringExtra("commentStatus");
        final String itemType= getIntent().getStringExtra("itemType");
        final String discount = getIntent().getStringExtra("discount");
        final String disclaimer = getIntent().getStringExtra("disclaimer");
        final String toppingStatus = getIntent().getStringExtra("toppingStatus");

        Toast.makeText(this, buttonStatus, Toast.LENGTH_SHORT).show();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gridView = findViewById(R.id.btnList);
        gridView1 = findViewById(R.id.checkList);
        gridView3 = findViewById(R.id.topList);
        radioGrp = (RadioGroup) findViewById(R.id.radioGroup);
        disclaimerTextview=findViewById(R.id.showDisclaimer);
        getSupportActionBar().setTitle(nameOfItem);
if(buttonStatus.equalsIgnoreCase("true"))
{
    if(nameOfItem!=null&&!nameOfItem.equalsIgnoreCase(""))
        databaseReference = FirebaseDatabase.getInstance().getReference(nameOfItem).child("btnlist");
    databaseReference.keepSynced(true);

    adapter=new buttonListAdapter(Extend_Dish.this,retrieveButton(databaseReference));

    gridView.setAdapter(adapter);

}
        if(toppingStatus.equalsIgnoreCase("true"))
        {
            if(nameOfItem!=null&&!nameOfItem.equalsIgnoreCase(""))
                databaseReference2 = FirebaseDatabase.getInstance().getReference(nameOfItem).child("tpnglist");
            databaseReference2.keepSynced(true);

            adapter2=new ToppingsListAdapter(Extend_Dish.this,retrieveToppings(databaseReference2));

            gridView3.setAdapter(adapter2);

        }
if(checkboxStatus.equalsIgnoreCase("true"))
{
    if(nameOfItem!=null&&!nameOfItem.equalsIgnoreCase(""))
    databaseReference1 = FirebaseDatabase.getInstance().getReference(nameOfItem).child("inglist");
    databaseReference1.keepSynced(true);
    adapter1=new CheckBoxListAdapter(Extend_Dish.this,retrieveCheckBox(databaseReference1));

    gridView1.setAdapter(adapter1);

}


if(disclaimer!=null)
    disclaimerTextview.setText(disclaimer);


dishimage=findViewById(R.id.dishImage1);
vegimage=findViewById(R.id.vegImage1);
        itemName1 = findViewById(R.id.itemName1);
        quantityAmount1 = findViewById(R.id.quantityAmount1);
        quantityHead1 = findViewById(R.id.quantityHead1);
        priceHead1 = findViewById(R.id.priceHead1);
        priceValue1 = findViewById(R.id.priceValue1);
        timeValue1 = findViewById(R.id.timeValue1);
        timeHead1 = findViewById(R.id.timeHead1);
        btnListHead = findViewById(R.id.btnListHead);
        btnListNote = findViewById(R.id.btnListNote);
        checkBoxListHead = findViewById(R.id.checkBoxListHead);
        checkBoxListNote = findViewById(R.id.checkBoxListNote);
        commentHead = findViewById(R.id.commentHead);
        tv_message = findViewById(R.id.tv_message);
        listView1 = findViewById(R.id.listView1);
        listView2 = findViewById(R.id.listView2);
        listView3 = findViewById(R.id.listView3);
        tv_total=findViewById(R.id.tv_total);
        tv_subtotal=findViewById(R.id.tv_subtotal);
        discount1=findViewById(R.id.discount1);
        toppingListHead=findViewById(R.id.toppingListHead);
        toppingListNote=findViewById(R.id.toppingListNote);



        tv_subtotal.setText(" ₹ "+ price[0]);
        tv_total.setText(" ₹ "+ price[0]);

        itemName1.setText(nameOfItem);
        quantityAmount1.setText(amountOfQuantity);
        priceValue1.setText(" ₹ "+ price[0]);
        if(!timeVal.equalsIgnoreCase(""))
        timeValue1.setText(timeVal);


        if(!discount.equalsIgnoreCase("0"))
        {
            discount1.setText(discount+" %");
        }
        else
            discount1.setText("");



        if(checkboxStatus.equalsIgnoreCase("true")) {

checkBoxListHead.setText("Customize Your Item :");
checkBoxListNote.setText("NOTE : Select Ingredient that you don't want to add in your item");
        }

        if(buttonStatus.equalsIgnoreCase("true")) {
            databaseReference = FirebaseDatabase.getInstance().getReference(nameOfItem).child("headingname");

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text= dataSnapshot.getValue(String.class);
                    btnListHead.setText(text);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            databaseReference = FirebaseDatabase.getInstance().getReference(nameOfItem).child("headingnote");

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text= dataSnapshot.getValue(String.class);
                    btnListNote.setText(text);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




        }


        if(itemType.equalsIgnoreCase("veg"))
        {
            vegimage.setBackgroundResource(R.mipmap.veg);
        }
        else if (itemType.equalsIgnoreCase("egg"))
            vegimage.setBackgroundResource(R.mipmap.download);
        else
            vegimage.setBackgroundResource(R.mipmap.nonveg);


            if (!commentStatus.equalsIgnoreCase("true")) {
            commentHead.setVisibility(View.GONE);
            tv_message.setVisibility(View.GONE);
        }

        if (!checkboxStatus.equalsIgnoreCase("true")) {
            checkBoxListHead.setVisibility(View.GONE);
            checkBoxListNote.setVisibility(View.GONE);
            listView2.setVisibility(View.GONE);
        }
        if (!buttonStatus.equalsIgnoreCase("true")) {
            btnListHead.setVisibility(View.GONE);
            btnListNote.setVisibility(View.GONE);
            listView1.setVisibility(View.GONE);
        }
        if((!(timeVal.trim()).equalsIgnoreCase("0"))&&timeVal!=null)
        {
            timeHead1.setVisibility(View.VISIBLE);
            timeValue1.setVisibility(View.VISIBLE);
        }
        if((!(amountOfQuantity).equalsIgnoreCase("0")))
        {
            quantityAmount1.setVisibility(View.VISIBLE);
            quantityHead1.setVisibility(View.VISIBLE);
        }

        if(imageUri!=null)
            Picasso.with(this).load(imageUri).into(dishimage);

        if(!toppingStatus.equalsIgnoreCase("true"))
        {
            toppingListHead.setVisibility(View.GONE);
            toppingListNote.setVisibility(View.GONE);
            listView3.setVisibility(View.GONE);
        }

        radioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                price[0] = (checkedId+"").substring(0,(checkedId+"").length()-1);
            }
        });
        i=0;
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i == 0){
                    i++;
                    quanadded.setText(i+"");
                }
                if(customStatus.equals("true")){
                    final AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
                    alertbox.setTitle("Confirm Submission");

                    alertbox.setPositiveButton("Repeat Last",
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {
                                    i++;
                                    quanadded.setText(i+"");

                                }
                            })
                            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    alertbox.show();
                }else{
                    i++;
                    quanadded.setText(i+"");
                }
            }
        });

        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
                alertbox.setTitle("Order Placed");
                alertbox.setMessage("Your order placed successfully");
                alertbox.setCancelable(false);
                alertbox.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                startActivity(new Intent(Extend_Dish.this,Extend_Dish.class));
                                finish();
                            }
                        });
                alertbox.show();
            }
        });


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }

    private void fetchDataButton(DataSnapshot dataSnapshot)
    {
        int i=0;

        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            Log.d("ds","Hell");
            try
            {
                buttonListDetail spacecraft = ds.getValue(buttonListDetail.class);
                Log.d("sds","Hellooo");
                assert spacecraft != null;
                //   if(spacecraft.getBillno().contains(searchstring))


                //get string array from sourc

                //create radio buttons

                    RadioButton radioButton = new RadioButton(this);
                    radioButton.setText(spacecraft.getBtnName());
                    if(i==0) {
                        radioButton.setChecked(true);
                    }
                    radioGrp.addView(radioButton);
                    radioButton.setId(Integer.parseInt(spacecraft.getBtnValue()+i));
                    i++;
                btn_details.add(spacecraft);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                Log.d("ds_exception",e+"");
            }
        }
    }

    private List<buttonListDetail> retrieveButton(DatabaseReference db) {

        Log.d("sdsd","Entered in the listener");

        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("sdsd","Calling Fetch data");
                //bill_details.clear();
                fetchDataButton(dataSnapshot);
                setListViewHeightBasedOnChildren2(gridView);
                adapter.notifyDataSetChanged();
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


                fetchDataButton(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return  btn_details;
    }

    private void fetchDataCheckBox(DataSnapshot dataSnapshot)
    {

        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            Log.d("ds","Hell");
            try {
                checkBoxListDetail spacecraft1 = ds.getValue(checkBoxListDetail.class);
                Log.d("sds","Hellooo");
                assert spacecraft1 != null;
                //   if(spacecraft.getBillno().contains(searchstring))
                check_Box_details.add(spacecraft1);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                Log.d("ds_exception",e+"");
            }
        }
    }

    private List<checkBoxListDetail> retrieveCheckBox(DatabaseReference db) {

        Log.d("sdsd","Entered in the listener");

        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("sdsd","Calling Fetch data");
                //bill_details.clear();
                fetchDataCheckBox(dataSnapshot);
                setListViewHeightBasedOnChildren(gridView1);
                adapter1.notifyDataSetChanged();
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


                fetchDataCheckBox(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return  check_Box_details;
    }

    private void setListViewHeightBasedOnChildren(GridView listView) {
        Log.e("Listview Size ", "" + listView.getCount());
       CheckBoxListAdapter listAdapter= (CheckBoxListAdapter) listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i+=2) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }


        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getVerticalSpacing()) *( (listAdapter.getCount() - 1));

        listView.setLayoutParams(params);
        listView.requestLayout();

    }
    private void setListViewHeightBasedOnChildren2(GridView listView) {
        Log.e("Listview Size ", "" + listView.getCount());
        buttonListAdapter listAdapter= (buttonListAdapter) listView.getAdapter();
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
                + (listView.getVerticalSpacing() +6)* ((listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    private void setListViewHeightBasedOnChildren3(GridView listView) {
        Log.e("Listview Size ", "" + listView.getCount());
       ToppingsListAdapter listAdapter= (ToppingsListAdapter) listView.getAdapter();
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
                + (listView.getVerticalSpacing())* ((listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();

    }
    private void fetchDataToppings(DataSnapshot dataSnapshot)
    {

        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            Log.d("ds","Hell");
            try
            {
                ToppingListDetails spacecraft2 = ds.getValue(ToppingListDetails.class);
                Log.d("sds","Hellooo");
                assert spacecraft2 != null;
                //   if(spacecraft.getBillno().contains(searchstring))
                 topping_Box_details.add(spacecraft2);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                Log.d("ds_exception",e+"");
            }
        }
    }

    private List<ToppingListDetails> retrieveToppings(DatabaseReference db) {

        Log.d("sdsd","Entered in the listener");

        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("sdsd","Calling Fetch data");
                //bill_details.clear();
                fetchDataToppings(dataSnapshot);
                setListViewHeightBasedOnChildren3(gridView3);
                adapter2.notifyDataSetChanged();
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


                fetchDataToppings(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return  topping_Box_details;
    }
}

package com.demo.mytask;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Extend_Dish extends AppCompatActivity {

    private TextView tvtype,tvtime,tvstatus,tvrate,tvjobstatus,tvsgst,tvcgst,tvaddress,tvdiscount,tvjobfor,tvjobto,tvstime,tvetime,tvttime,tvtotal,tvpaystatus;
    private Button canbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend_dish);




      final  String nameOfItem =getIntent().getStringExtra("itemName");
      final  String amountOfQuantity = getIntent().getStringExtra("quantityAmount");
       final String price = getIntent().getStringExtra("priceValue");
       final String timeVal = getIntent().getStringExtra("timeValue");
      final  String customStatus = getIntent().getStringExtra("customStatus");
       final String imageUri=getIntent().getStringExtra("imageUri");
       final String checkboxStatus=getIntent().getStringExtra("checkStatus");
       final String buttonStatus=getIntent().getStringExtra("buttonStatus");
       final String commentStatus=getIntent().getStringExtra("commentStatus");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(nameOfItem);

    /*    tvtype = findViewById(R.id.type);
        tvtime = findViewById(R.id.time);
        tvstatus = findViewById(R.id.status);
        tvjobstatus = findViewById(R.id.jobstatus);
        tvsgst = findViewById(R.id.sgst);
        tvcgst = findViewById(R.id.cgst);
        tvaddress = findViewById(R.id.address);
        tvdiscount = findViewById(R.id.discount);
        tvjobfor = findViewById(R.id.servicefor);
        tvjobto = findViewById(R.id.whereto);
        tvstime = findViewById(R.id.starttime);
        tvetime = findViewById(R.id.endtime);
        tvttime = findViewById(R.id.totaltime);
        tvtotal = findViewById(R.id.grandtot);
        tvpaystatus = findViewById(R.id.paystat);
        tvrate = findViewById(R.id.rate);
        canbtn = findViewById(R.id.canbtn);


        tvjobstatus.setText(jobstatus);
        tvaddress.setText(address);
        tvjobto.setText(jobto);
        tvjobfor.setText(jobfor);
        tvdiscount.setText(discount);

       */

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }
}

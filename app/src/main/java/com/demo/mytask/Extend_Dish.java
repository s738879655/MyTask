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


        final String time = getIntent().getStringExtra("time");
        final String billno = getIntent().getStringExtra("billno");
        final String type = getIntent().getStringExtra("type");
        final String name = getIntent().getStringExtra("name");
        final String status = getIntent().getStringExtra("status");
        final String total = getIntent().getStringExtra("total");
        final String jobstatus = getIntent().getStringExtra("jobstatus");
        final String tax = getIntent().getStringExtra("tax");
        final String address = getIntent().getStringExtra("address");
        final String jobfor = getIntent().getStringExtra("ofwhat");
        final String jobto = getIntent().getStringExtra("whereto");
        final String rate = getIntent().getStringExtra("rate");
        final String stime = getIntent().getStringExtra("stime");
        final String etime = getIntent().getStringExtra("etime");
        final String discount = getIntent().getStringExtra("discount");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("BN "+billno);

        tvtype = findViewById(R.id.type);
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

        double sgst=0;

        if(!tax.equals("null"))
            sgst = Integer.parseInt(tax)/2;

        tvtime.setText(time);
        tvtype.setText(type);
        tvtotal.setText("Rs."+total);
        tvstatus.setText(status);
        tvsgst.setText(sgst+"");
        tvcgst.setText(sgst+"");
        tvrate.setText(rate);
        tvstime.setText(stime);
        tvetime.setText(etime);
        tvpaystatus.setText(status);
        tvjobstatus.setText(jobstatus);
        tvaddress.setText(address);
        tvjobto.setText(jobto);
        tvjobfor.setText(jobfor);
        tvdiscount.setText(discount);

        if(status.equalsIgnoreCase("Completed")||status.equalsIgnoreCase("Cancelled")){
            canbtn.setVisibility(View.GONE);
        }else if(status.equalsIgnoreCase("Pending")||status.equalsIgnoreCase("In Progress")){

            canbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
                    alertbox.setMessage("Are you sure");
                    alertbox.setTitle("Cancel");

                    alertbox.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0,
                                                    int arg1) {
                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Bills").child("bill"+billno);
                                    databaseReference.child("status").setValue("Cancelled");
                                    canbtn.setVisibility(View.GONE);
                                    tvpaystatus.setText("Cancelled");

                                }
                            })
                            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    alertbox.show();
                }
            });
        }

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

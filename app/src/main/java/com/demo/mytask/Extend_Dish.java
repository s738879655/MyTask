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

    private TextView itemName1,quantityAmount1,priceValue1,timeValue1,quantityHead1,priceHead1,timeHead1,btnListHead,btnListNote,checkBoxListHead,checkBoxListNote,commentHead;
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

       itemName1 = findViewById(R.id.itemName1);
       quantityAmount1 = findViewById(R.id.quantityAmount1);
       quantityHead1= findViewById(R.id.quantityHead1);
       priceHead1= findViewById(R.id.priceHead1);
       priceValue1= findViewById(R.id.priceValue1);
        timeValue1= findViewById(R.id.timeValue1);
        timeHead1= findViewById(R.id.timeHead1);




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

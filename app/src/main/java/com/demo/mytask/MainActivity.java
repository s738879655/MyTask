package com.demo.mytask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      // DishDetail bill_detail = new DishDetail("Biryani Fafda","Half Plate","67 $","1 h","true","gs://notification-fe2b9.appspot.com/dishImage/Biryani/501141009-612x612.jpg","true","true","true");
       //    FirebaseDatabase database = FirebaseDatabase.getInstance();
     //     DatabaseReference databaseReference = database.getReference().child("Item").child("Kolkata");
    //    databaseReference.push().setValue(bill_detail);

        Intent intent=new Intent(this,ListOoFDishes.class);
        startActivity(intent);
    }
}

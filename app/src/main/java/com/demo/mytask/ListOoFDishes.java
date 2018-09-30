package com.demo.mytask;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListOoFDishes extends AppCompatActivity {

    GridView gridView;
    DatabaseReference databaseReference;
    private ArrayList<DishDetail> bill_details= new ArrayList<>();
    private DishesListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_oo_fdishes);
        gridView = findViewById(R.id.gridvf);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Order List");

        databaseReference = FirebaseDatabase.getInstance().getReference("item");
        databaseReference.keepSynced(true);

        adapter=new DishesListAdapter(ListOoFDishes.this,retrieve(databaseReference));
        gridView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }

    private void fetchData(DataSnapshot dataSnapshot)
    {

        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            Log.d("ds","Hell");
            try {
                DishDetail spacecraft = ds.getValue(DishDetail.class);
                Log.d("sds","Hellooo");
                assert spacecraft != null;
             //   if(spacecraft.getBillno().contains(searchstring))
                    bill_details.add(spacecraft);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                Log.d("ds_exception",e+"");
            }
        }
    }

    private List<DishDetail> retrieve(DatabaseReference db) {

        Log.d("sdsd","Entered in the listener");

        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("sdsd","Calling Fetch data");
                //bill_details.clear();
                fetchData(dataSnapshot);
                adapter.notifyDataSetChanged();
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


                fetchData(dataSnapshot);
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

        return  bill_details;
    }
}

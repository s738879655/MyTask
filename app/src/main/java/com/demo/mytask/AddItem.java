package com.demo.mytask;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddItem extends AppCompatActivity implements NumberPicker.OnValueChangeListener{

    private Button submit,reset;
    private TextView itemname,itemprice,itemquantity,timechoose,itemcontent,itemdisclaimer,itemdiscount,addmorebtn,addmoreingredient,btnhn,btnhp,addmoretpng;
    private ImageView ingridientimage;
    private Spinner spinner1,spinner2,spinner3,spinner4;
    CheckBox buttoncb,commentcb,checkcb,toppingcb;
    FrameLayout fl2,fl3,fl4,fl5,fl6;
    RelativeLayout ll,rl2;
    ListView lv,lv2,lv3;
    int  newHeight=0;
    ArrayList<String> btnlist,ingredientlist,sectionlist,categorylist,typelist,quantypelist,toppinglist;
    MyItemRecyclerViewAdapter myAdapter;MyItemRecyclerViewAdapter2 myAdapter2;MyItemRecyclerViewAdapter3 myAdapter3;
    ArrayAdapter<String> spinnerArrayAdapter,spinnerArrayAdapter2,spinnerArrayAdapter3,spinnerArrayAdapter4;
    int h = 0;
    private final int PICK_IMAGE_REQUESI=70;
    Uri filepath;
    StorageReference riversRef;
    Boolean btncb,cmntcb,ingcb,tpngcb;
    String imgpath="",typee,namee;
    FirebaseDatabase database ;
    DatabaseReference myRef ;
    ImageButton imgbtn;
    String name = "", price = "", time = "", quantity = "", discount = "", content = "", disclaimer = "", section = "", category = "", itemtype = "", quantype = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        //Database reference
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("item");


        //For Buttons
        submit = findViewById(R.id.submitbtn);
        reset = findViewById(R.id.resetbtn);

        //For all edittext
        itemname = findViewById(R.id.itemname);
        itemprice = findViewById(R.id.itemprice);
        itemquantity = findViewById(R.id.itemquantity);
        itemcontent = findViewById(R.id.itemcontent);
        itemdisclaimer = findViewById(R.id.itemdisclaimer);
        itemdiscount = findViewById(R.id.itemdiscount);
        btnhn = findViewById(R.id.headingname);
        btnhp = findViewById(R.id.headingprice);

        // For first two spinner
        spinner1 = findViewById(R.id.sectionspinner);
        spinner2 = findViewById(R.id.categoryspinner);

        //for itemtype and quantitytype
        spinner3 = findViewById(R.id.itemtype);
        spinner4 = findViewById(R.id.quantitytype);

        // For time choose , image button(for Ingredient and date) , Add more buttons and add more ingredients
        timechoose = findViewById(R.id.itemtime);
        ingridientimage = findViewById(R.id.itemimage);
        imgbtn = findViewById(R.id.imgbtn);imgbtn.setImageResource(R.drawable.add_image);
        addmorebtn = findViewById(R.id.addmorebtns);
        addmoreingredient = findViewById(R.id.addmoreingredient);
        addmoretpng = findViewById(R.id.addmoretpng);

        // for type of checkbox;
        buttoncb = findViewById(R.id.buttoncb);
        commentcb = findViewById(R.id.commentcb);
        checkcb = findViewById(R.id.checkboxcb);
        toppingcb = findViewById(R.id.toppingcb);

        // for list views and their layouts
        ll = findViewById(R.id.lllist);
        rl2 = findViewById(R.id.rl2);
        lv = findViewById(R.id.buttonlist);
        lv2 = findViewById(R.id.ingridientlist);
        lv3 = findViewById(R.id.toppinglist);

        // arraylist for button, ingredient, category and section
        btnlist = new ArrayList<String>();
        ingredientlist = new ArrayList<String>();
        toppinglist = new ArrayList<String>();
        sectionlist = new ArrayList<String>();
        quantypelist = new ArrayList<String>();
        typelist = new ArrayList<String>();
        sectionlist.add("Choose One");
        sectionlist.add("Today's Special");
        sectionlist.add("Menu Item");
        categorylist = new ArrayList<String>();
        categorylist.add("Choose Type");
        categorylist.add("South Indian");
        categorylist.add("North Indian");
        categorylist.add("Chapatis");
        categorylist.add("Rice");
        quantypelist.add("None");
        quantypelist.add("Half");
        quantypelist.add("Piece");
        typelist.add("Veg");
        typelist.add("Egg");
        typelist.add("Non-Veg");

        /******************************************************************/
        //arrayadapter for spinners
        spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,sectionlist);
        spinnerArrayAdapter2 = new ArrayAdapter<String>(
                this,R.layout.spinner_item,categorylist);
        spinnerArrayAdapter3 = new ArrayAdapter<String>(
                this,R.layout.spinner_item,typelist);
        spinnerArrayAdapter4 = new ArrayAdapter<String>(
                this,R.layout.spinner_item,quantypelist);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerArrayAdapter3.setDropDownViewResource(R.layout.spinner_item);
        spinnerArrayAdapter4.setDropDownViewResource(R.layout.spinner_item);

        spinner1.setAdapter(spinnerArrayAdapter);
        spinner3.setAdapter(spinnerArrayAdapter3);
        spinner4.setAdapter(spinnerArrayAdapter4);

        btnlist.clear();
        ingredientlist.clear();


        myAdapter = new MyItemRecyclerViewAdapter(this,btnlist);
        myAdapter2 = new MyItemRecyclerViewAdapter2(this,ingredientlist);
        myAdapter3 = new MyItemRecyclerViewAdapter3(this,toppinglist);


        lv.setAdapter(myAdapter);
        lv2.setAdapter(myAdapter2);
        lv3.setAdapter(myAdapter3);

        fl2 = findViewById(R.id.fl2);
        fl3 = findViewById(R.id.fl3);
        fl4 = findViewById(R.id.fl4);
        fl5 = findViewById(R.id.fl5);
        fl6 = findViewById(R.id.fl6);

        spinner2.setEnabled(false);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 1){
                    fl2.setVisibility(View.VISIBLE);
                    fl3.setVisibility(View.VISIBLE);
                    spinner2.setEnabled(true);
                    spinner2.setAdapter(null);
                    buttoncb.setChecked(false);
                    commentcb.setChecked(false);
                    checkcb.setChecked(false);
                    toppingcb.setChecked(false);
                }
                else if(position == 2){
                    spinner2.setEnabled(true);
                    spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_item);
                    spinner2.setAdapter(spinnerArrayAdapter2);
                    buttoncb.setChecked(false);
                    commentcb.setChecked(false);
                    checkcb.setChecked(false);
                    toppingcb.setChecked(false);
                }
                else{
                    fl2.setVisibility(View.GONE);
                    fl3.setVisibility(View.GONE);
                    fl4.setVisibility(View.GONE);
                    fl6.setVisibility(View.GONE);
                    rl2.setVisibility(View.GONE);
                    spinner2.setAdapter(null);
                    spinner2.setEnabled(false);
                    buttoncb.setChecked(false);
                    commentcb.setChecked(false);
                    checkcb.setChecked(false);
                    toppingcb.setChecked(false);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    fl2.setVisibility(View.GONE);
                    fl3.setVisibility(View.GONE);
                    fl4.setVisibility(View.GONE);
                    fl6.setVisibility(View.GONE);
                    rl2.setVisibility(View.GONE);
                    buttoncb.setChecked(false);
                    commentcb.setChecked(false);
                    checkcb.setChecked(false);
                    toppingcb.setChecked(false);

                }else{
                    fl2.setVisibility(View.VISIBLE);
                    fl3.setVisibility(View.VISIBLE);
                    buttoncb.setChecked(false);
                    commentcb.setChecked(false);
                    checkcb.setChecked(false);
                    toppingcb.setChecked(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //end for spinners
        /*********************************************************/

        //On select checkboxes
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
                    ingredientlist.add("2");
                    setListViewHeightBasedOnChildren2(lv2);
                    rl2.setVisibility(View.VISIBLE);
                }else if(!isChecked){
                    rl2.setVisibility(View.GONE);
                }
            }
        });
        toppingcb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    toppinglist.clear();
                    toppinglist.add("2");
                    setListViewHeightBasedOnChildren3(lv3);
                    fl6.setVisibility(View.VISIBLE);
                }else if(!isChecked){
                    toppinglist.clear();
                    fl6.setVisibility(View.GONE);
                }
            }
        });

        //end for checkboxes
        /*************************************************/

        //for time selection
        timechoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });


        // for expanding no. of list item in button and ingredients
        addmorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ss1 = btnhn.getText().toString().trim();
                String ss2 = btnhp.getText().toString().trim();
                if(ss1==null || ss1.equals("")){
                    btnhn.setError("Please enter heading");
                    return;
                }
                if(ss2==null || ss2.equals("")){
                    btnhp.setError("Please enter price");
                    return;
                }
                Toast.makeText(AddItem.this,"Clicked",Toast.LENGTH_SHORT).show();
                if(btnlistvalid(lv))
                    addrow();
            }
        });
        addmoreingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ingridientlistvalid(lv2))
                    return;
                ingredientlist.add("2");
                myAdapter2.notifyDataSetChanged();
                setListViewHeightBasedOnChildren2(lv2);
            }
        });
        addmoretpng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!toppinglistvalid(lv3))
                    return;
                toppinglist.add("2");
                setListViewHeightBasedOnChildren3(lv3);
                myAdapter3.notifyDataSetChanged();
            }
        });

        //Ingredient image
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });


        //Final Submit Button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = itemname.getText().toString().trim();
                price = itemprice.getText().toString().trim();
                time = timechoose.getText().toString().trim();
                quantity = itemquantity.getText().toString().trim();
                discount = itemdiscount.getText().toString().trim();
                content = itemcontent.getText().toString().trim();
                disclaimer = itemdisclaimer.getText().toString().trim();
                section = spinner1.getSelectedItem().toString().trim();
                category = "";
                if (section.equals("Menu Item")) {
                    category = spinner2.getSelectedItem().toString().trim();
                    typee = category;
                } else {
                    category = section;
                    typee = category;
                }
                itemtype = spinner3.getSelectedItem().toString().trim();
                quantype = spinner4.getSelectedItem().toString().trim();


                if (filepath == null) {
                    Toast.makeText(AddItem.this, "Please Add Image", Toast.LENGTH_LONG).show();
                    return;
                }
                if (name.equals("") || name == null) {
                    itemname.setError("");
                    return;
                }
                if (price.equals("") || price == null) {
                    itemprice.setError("");
                    return;
                }
                if (time.equals("") || time == null) {
                    time = "0";
                }
                if (quantity.equals("") || quantity == null) {
                    quantity = "0";
                }
                if (discount.equals("") || discount == null) {
                    discount = "0";
                }else if(Integer.parseInt(discount) > 100){
                    itemdiscount.setError("discount cannot exceed 100");
                    return;
                }
                if (content.equals("") || content == null) {
                    content = "0";
                }
                if (disclaimer.equals("") || disclaimer == null) {
                    itemdisclaimer.setError("Please enter disclaimer");
                    return;
                }
                namee = name;
                btncb = buttoncb.isChecked();
                cmntcb = commentcb.isChecked();
                ingcb = checkcb.isChecked();
                tpngcb = toppingcb.isChecked();

                myRef = database.getReference("item").child(category).child(name);

                if (btncb) {
                    String ss1 = btnhn.getText().toString().trim();
                    String ss2 = btnhp.getText().toString().trim();
                    if (ss1 == null || ss1.equals("")) {
                        btnhn.setError("Please enter heading");
                        return;
                    }
                    if (ss2 == null || ss2.equals("")) {
                        btnhp.setError("Please enter price");
                        return;
                    }
                    if (!btnlistvalid(lv))
                        return;

                    DatabaseReference ref1 = database.getReference(name);
                    HeadingDetail hd = new HeadingDetail(ss1, ss2);
                    ref1.setValue(hd);
                    MyItemRecyclerViewAdapter listAdapter = (MyItemRecyclerViewAdapter) lv.getAdapter();
                    for (int i = 0; i < listAdapter.getCount(); i++) {

                        View listItem = lv.getChildAt(i);
                        TextView tv = listItem.findViewById(R.id.buttonname);
                        TextView tv2 = listItem.findViewById(R.id.buttonprice);

                        String s1 = tv.getText().toString().trim();
                        String s2 = tv2.getText().toString().trim();
                        buttonListDetail btn = new buttonListDetail(s1, s2);

                        ref1.child("btnlist").child("listbtn").push().setValue(btn);
                    }
                }
                if (ingcb) {
                    DatabaseReference ref2 = database.getReference(name);
                    if (!ingridientlistvalid(lv2))
                        return;

                    Log.d("putdataing", "inside for");
                    MyItemRecyclerViewAdapter2 listAdapter = (MyItemRecyclerViewAdapter2) lv2.getAdapter();
                    if(listAdapter.getCount() == 0){
                        Toast.makeText(AddItem.this, "Please add Some Ingredient", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    for (int i = 0; i < listAdapter.getCount(); i++) {

                        View listItem = lv2.getChildAt(i);
                        TextView tv = listItem.findViewById(R.id.ingridientname);

                        String s1 = tv.getText().toString().trim();
                        Log.d("putdataing", s1);
                        Log.d("putdatabtn", s1);
                        checkBoxListDetail cbd = new checkBoxListDetail(s1);
                        ref2.child("inglist").child("listing").push().setValue(cbd);
                    }
                }
                if(tpngcb){
                    DatabaseReference ref3 = database.getReference(name);
                    if (!toppinglistvalid(lv3))
                        return;

                    MyItemRecyclerViewAdapter3 listAdapter = (MyItemRecyclerViewAdapter3) lv3.getAdapter();

                    if(listAdapter.getCount() == 0){
                        Toast.makeText(AddItem.this, "Please add Some Topping", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    for (int i = 0; i < listAdapter.getCount(); i++) {

                        View listItem = lv3.getChildAt(i);
                        TextView tv = listItem.findViewById(R.id.buttonname);
                        TextView tv2 = listItem.findViewById(R.id.buttonprice);

                        String s1 = tv.getText().toString().trim();
                        String s2 = tv2.getText().toString().trim();
                        ToppingListDetails btn = new ToppingListDetails(s1, s2);

                        ref3.child("tpnglist").child("listtpng").push().setValue(btn);
                    }
                }

                AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
                alertbox.setMessage("Confirm Submission");
                alertbox.setTitle("Confirm Submission");

                alertbox.setPositiveButton("CONFIRM",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                if (uploadFile(filepath)){
                                    if(imgpath.equals("fail")) {
                                        Toast.makeText(AddItem.this, "Please Try Again", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    DishDetail dishDetail = new DishDetail(name,quantity, price, itemtype, time, ((btncb||ingcb)?true:false) + "", imgpath, ingcb+"", btncb+"", cmntcb+"",tpngcb+"",discount,disclaimer,quantype);
                                    myRef.setValue(dishDetail);

                                }

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

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddItem.this,AddItem.class));
                finish();
            }
        });

    }


    /*********************************************************/
    //For image of item
    private void chooseImage() {
        Intent choose=new Intent();
        choose.setType("image/*");
        choose.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(choose,"Select Book Image"),PICK_IMAGE_REQUESI);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==70&&resultCode==RESULT_OK &&data!=null &&data.getData()!=null )
        {
            filepath=data.getData();
            Bitmap bitmap = null; //getBitmap(filepath.toString());
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ingridientimage.setImageBitmap(bitmap);
            //uploadFile(filepath);
        }
    }
    private boolean uploadFile (final Uri filePath) {
        //if there is a file to upload
        Log.d("dssd","Entered in upload file");
        //Uri filePath=filepath;

        if (filePath != null) {
            //displaying a progress dialog while upload is going on
            //Date dt =
            final ProgressDialog progressDialog = new ProgressDialog(AddItem.this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            riversRef = FirebaseStorage.getInstance().getReference().child("images/"+itemname.getText().toString().trim()+".jpg");
            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //if the upload is successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();
                            riversRef.getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                Uri downloadUrl = uri;
                                                imgpath = downloadUrl.toString();
                                                Log.d("uuuuuuu",imgpath);
                                                DatabaseReference db = FirebaseDatabase.getInstance().getReference("item").child(typee).child(namee).child("imageUri");
                                                db.setValue(imgpath);
                                                startActivity(new Intent(AddItem.this,AddItem.class));
                                                finish();
                                                //Do what you want with the url
                                            }
                                        });
                            //and displaying a success toast
                            //  Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //if the upload is not successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();
                            imgpath = "fail";
                            //and displaying error message
                            //    Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        }
        //if there is not any file
        else {
            //you can display an error toast
        }
        return true;
    }
    //end of item image
    /**********************************************************/


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
    private void setListViewHeightBasedOnChildren3(ListView listView) {
        Log.e("Listview Size ", "" + listView.getCount());
        MyItemRecyclerViewAdapter3 listAdapter = (MyItemRecyclerViewAdapter3) listView.getAdapter();
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

    boolean btnlistvalid(ListView ls){
        MyItemRecyclerViewAdapter listAdapter = (MyItemRecyclerViewAdapter) ls.getAdapter();
        if (listAdapter == null) {
            return false;
        }
        for (int i = 0; i < listAdapter.getCount(); i++) {

            View listItem = ls.getChildAt(i);
            TextView tv = listItem.findViewById(R.id.buttonname);
            TextView tv2 = listItem.findViewById(R.id.buttonprice);

            String s1 = tv.getText().toString().trim();
            String s2 = tv2.getText().toString().trim();

            if(s1==null || s1.equals("")) {
                tv.setError("Please Enter name first");
                return false;
            }if(s2==null || s2.equals("")) {
                tv2.setError("Please Enter price first");
                return false;
            }
        }
        return true;
    }
    void addrow(){
        btnlist.add("2");
        myAdapter.notifyDataSetChanged();
        setListViewHeightBasedOnChildren(lv);
    }

    boolean ingridientlistvalid(ListView ls){
        MyItemRecyclerViewAdapter2 listAdapter = (MyItemRecyclerViewAdapter2) ls.getAdapter();
        if (listAdapter == null) {

            return false;
        }
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = ls.getChildAt(i);
            TextView tv = listItem.findViewById(R.id.ingridientname);

            String s1 = tv.getText().toString().trim();

            if(s1==null || s1.equals("")) {
                tv.setError("Please Enter name first");
                return false;
            }
        }
        return true;
    }
    boolean toppinglistvalid(ListView ls){
        MyItemRecyclerViewAdapter3 listAdapter = (MyItemRecyclerViewAdapter3) ls.getAdapter();
        if (listAdapter == null) {
            return false;
        }
        for (int i = 0; i < listAdapter.getCount(); i++) {

            View listItem = ls.getChildAt(i);
            TextView tv = listItem.findViewById(R.id.buttonname);
            TextView tv2 = listItem.findViewById(R.id.buttonprice);

            String s1 = tv.getText().toString().trim();
            String s2 = tv2.getText().toString().trim();

            if(s1==null || s1.equals("")) {
                tv.setError("Please Enter Topping name first");
                return false;
            }if(s2==null || s2.equals("")) {
                tv2.setError("Please Enter Topping price first");
                return false;
            }
        }
        return true;
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
        TextView tv2 = view.findViewById(R.id.buttonprice);

        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}

class MyItemRecyclerViewAdapter2 extends BaseAdapter {

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

        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}

class MyItemRecyclerViewAdapter3 extends BaseAdapter {

    private final List<String> button;
    private final Context context;


    public MyItemRecyclerViewAdapter3(Context contex, ArrayList<String> items) {
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
        TextView tv2 = view.findViewById(R.id.buttonprice);

        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
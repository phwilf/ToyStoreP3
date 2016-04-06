package com.example.phwilf.applytheme.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.phwilf.applytheme.Adapter.RecyclerAdapter;
import com.example.phwilf.applytheme.Cart;
import com.example.phwilf.applytheme.R;
import com.example.phwilf.applytheme.Toy;
import com.example.phwilf.applytheme.ToyList;
import com.example.phwilf.applytheme.ToyTouchHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button toCart;

    public ArrayList<Toy> userCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toCart = (Button) findViewById(R.id.toCart);

        userCart = new ArrayList<>();

        setUpRecyclerView();


        /* BEGIN: test info that succesfully sends array list to cart screen */
        final ArrayList<Toy> testList = new ArrayList<>();
        final Toy toy = new Toy();
        final Toy toy2 = new Toy();
        final Toy toy3 = new Toy();
        toy.setTitle("ONE");
        toy2.setTitle("TWO");
        toy3.setTitle("THREE");
        toy.setDescription("Cats");
        testList.add(toy);
        testList.add(toy2);
        testList.add(toy3);


        toCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartActivity = new Intent(v.getContext(), CartActivity.class);
                cartActivity.putExtra("ToyData", testList);
                v.getContext().startActivity(cartActivity);
            }
        });
        /* END: test info that succesfully sends array list to cart screen */
    }

    private void setUpRecyclerView() {

        ToyList toyList = new ToyList();
        toyList.readFromFile("/Users/phwilf/Desktop/toy_data.data");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclyerView);
            //id defined in ActivityMain.xml
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, Toy.getData());
        recyclerView.setAdapter(recyclerAdapter); //connecting RecyclerView to Adapter

        // Define Linear Layout Manager for Recycler View
            //will give vertical orientation with linear list structure
        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this);
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManagerVertical);

        //going to help in add / delete operations on list items
        //note: this line is not necessary as it occures by default
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ItemTouchHelper.Callback callback = new ToyTouchHelper(recyclerAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

    }


//    public void onClick(View v) {
//        Log.i("clicks", "You Clicked toCart");
//        Intent i=new Intent(MainActivity.this, CartActivity.class);
//        startActivity(i);
//    }
}

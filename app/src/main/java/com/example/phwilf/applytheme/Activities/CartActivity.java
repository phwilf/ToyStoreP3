package com.example.phwilf.applytheme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.TextView;

import com.example.phwilf.applytheme.Adapter.RecyclerAdapter;
import com.example.phwilf.applytheme.Adapter.cartRecyclerAdapter;
import com.example.phwilf.applytheme.R;
import com.example.phwilf.applytheme.Toy;
import com.example.phwilf.applytheme.ToyTouchHelper;

import java.util.ArrayList;

/**
 * Created by phwilf on 4/4/16.
 */
public class CartActivity extends AppCompatActivity {


    private static final String Tag = CartActivity.class.getSimpleName();
//    private TextView tempToy1;
//    private TextView tempToy2;
//    private TextView tempToy3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_view);
        Log.d(Tag, "Current items in cart: ");

        Intent intent = getIntent();
//        Toy toy = (Toy) intent.getSerializableExtra("ToyData");
        ArrayList<Toy> testList = (ArrayList<Toy>) intent.getSerializableExtra("ToyData");

        setUpRecyclerView(testList);


        //Bundle extra = getIntent().getBundleExtra("CartData");
        //ArrayList<Toy> toyList = (ArrayList<Toy>) extra.getSerializable("testList");


//        tempToy1 = (TextView) findViewById(R.id.txt_cart_toy1Title);
//        tempToy1.setText(testList.get(0).getTitle());
//        tempToy2 = (TextView) findViewById(R.id.txt_cart_toy2Title);
//        tempToy2.setText(testList.get(1).getTitle());
//        tempToy3 = (TextView) findViewById(R.id.txt_cart_toy3Title);
//       tempToy3.setText(testList.get(2).getTitle());

    }

    private void setUpRecyclerView(ArrayList<Toy> toyList){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cartRecyclerView);
        cartRecyclerAdapter recyclerAdapter = new cartRecyclerAdapter(this, toyList);
        recyclerView.setAdapter(recyclerAdapter); //connecting RecyclerView to Adapter

        // Define Linear Layout Manager for Recycler View
        //will give vertical orientation with linear list structure
        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this);
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManagerVertical);

    }
}



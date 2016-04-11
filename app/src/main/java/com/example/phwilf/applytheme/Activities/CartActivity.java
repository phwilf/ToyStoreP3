package com.example.phwilf.applytheme.Activities;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phwilf.applytheme.Adapter.RecyclerAdapter;
import com.example.phwilf.applytheme.Adapter.cartRecyclerAdapter;
import com.example.phwilf.applytheme.Cart;
import com.example.phwilf.applytheme.R;
import com.example.phwilf.applytheme.Toy;
import com.example.phwilf.applytheme.ToyTouchHelper;

import java.util.ArrayList;

/**
 * Created by phwilf on 4/4/16.
 */
public class CartActivity extends AppCompatActivity {

    private static final String Tag = CartActivity.class.getSimpleName();

    public static TextView items, totalPrice;
    public static ImageView img_toMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_view);
        Log.d(Tag, "Current items in cart: ");

        Intent intent = getIntent();
        ArrayList<Toy> toyList = (ArrayList<Toy>) intent.getSerializableExtra("ToyData");

        setUpRecyclerView(toyList);

        items = (TextView) findViewById(R.id.txt_totCartItems);
        totalPrice = (TextView) findViewById(R.id.txt_totCartPrice);
        img_toMap = (ImageView) findViewById(R.id.img_toMap);

        img_toMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your code here
                toMaps_ToysRUs();
            }
        });

        items.setText("Items: " + Cart.getItems());
        totalPrice.setText("Total Price: $" + Cart.getTotalPrice());
    }

    //general private function for calling URLs
    private void toUrl(String url){
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    //specifc function for loading google maps
    public void toMaps_ToysRUs(){
        toUrl("https://www.google.com/maps/place/Toys%E2%80%9CR%E2%80%9DUs/@40.9794274,-74.2563207,17z/data=!4m7!1m4!3m3!1s0x89c302dac069cbdf:0x7a8c72d741957102!2s1+Geoffrey+Way,+Wayne,+NJ+07470!3b1!3m1!1s0x89c302cde7d27bf1:0x2ad7dabe9764018e");
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



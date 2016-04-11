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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import android.os.StrictMode;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button toCart;
    Button clear;

    ToyList toyList;

    public static TextView items, totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toCart = (Button) findViewById(R.id.toCart);
        clear = (Button) findViewById(R.id.clear);

        setUpRecyclerView();

        items = (TextView) findViewById(R.id.txt_totItems);
        totalPrice = (TextView) findViewById(R.id.txt_totPrice);
        Cart.setItems(0); Cart.setTotalPrice(0); Cart.getUserCart().clear();

        toCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartActivity = new Intent(v.getContext(), CartActivity.class);
                cartActivity.putExtra("ToyData", Cart.getUserCart());
                v.getContext().startActivity(cartActivity);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart.setItems(0); Cart.setTotalPrice(0); Cart.getUserCart().clear();

                items.setText("Items: 0");
                totalPrice.setText("Total Price: $0");

                for (Toy current : toyList.getToyList()) {
                    current.resetCount();
                }

                setUpRecyclerView();

            }
        });
        /* END: test info that succesfully sends array list to cart screen */
    }

    private void setUpRecyclerView() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        toyList = new ToyList(readFromURL("http://people.cs.georgetown.edu/~wzhou/toy.data"));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclyerView);
            //id defined in ActivityMain.xml
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, toyList.getToyList());
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

    public static ArrayList<Toy> readFromURL(String link) {
        String Tag = "importantstuff";

        ToyList toyList = null;
        InputStream is = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            is = conn.getInputStream();

            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            byte[] byteArray = baos.toByteArray();

            toyList = new ToyList(byteArray, byteArray.length);

            for (int i = 0; i < toyList.getNumOfToys(); i++){
                Log.d(Tag, toyList.getToy(i).getToyName());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return toyList.getToyList();
    }

}

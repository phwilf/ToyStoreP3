package com.example.phwilf.applytheme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.widget.TextView;

import com.example.phwilf.applytheme.R;

import org.w3c.dom.Text;

/**
 * Created by phwilf on 4/4/16.
 */
public class CartActivity extends AppCompatActivity {


    private static final String Tag = CartActivity.class.getSimpleName();
    private TextView tempText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_view);
        Log.d(Tag, "Current items in cart: ");

        String textFromMain = (String) getIntent().getExtras().get("CartData");
        tempText = (TextView) findViewById(R.id.txt_cartSize);
        tempText.setText(textFromMain);

    }
}

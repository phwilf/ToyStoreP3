package com.example.phwilf.applytheme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.phwilf.applytheme.R;

/**
 * Created by phwilf on 4/5/16.
 */
public class SearchActivity extends AppCompatActivity {

    Button btn_searchToys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_search);
        btn_searchToys = (Button) findViewById(R.id.btn_searchToys);

        btn_searchToys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchToysActivity = new Intent(v.getContext(), MainActivity.class);
                //cartActivity.putExtra("CartData", "Data from Main");
                v.getContext().startActivity(searchToysActivity);
            }
        });
    }



}

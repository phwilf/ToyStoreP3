package com.example.phwilf.applytheme.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phwilf.applytheme.R;
import com.example.phwilf.applytheme.Toy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phwilf on 4/5/16.
 */
public class cartRecyclerAdapter extends RecyclerView.Adapter<cartRecyclerAdapter.MyViewHolder> {

    private static final String Tag = cartRecyclerAdapter.class.getSimpleName();

    private List<Toy> cartData;
    private LayoutInflater mInflater;

    public cartRecyclerAdapter(Context context, List<Toy> data){
        this.cartData = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(Tag, "onCreateViewHolder");
        View view = mInflater.inflate(R.layout.cart_list_item, parent, false); //inflate the layout for each card
        MyViewHolder holder = new MyViewHolder(view); // pass the new view of list_item to the viewHolder
        return holder; //return the instance of the myviewholder class
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //going to be called for each row in the recycler view
        //position will be super important for knowing which objects are being intereacted with
        Log.d(Tag, "onBindViewHolder " + position);

        Toy currentToy = cartData.get(position);
        holder.setData(position, currentToy);

    }

    @Override
    public int getItemCount() {
        return cartData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //card components
        TextView title;
        Toy currentToy;
        int position;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.title = (TextView) itemView.findViewById(R.id.cart_toyTitle);
        }

        public void setData(int position, Toy currentToy){
            this.currentToy = currentToy;
            this.position = position;
            this.title.setText(currentToy.getTitle() + " (" + currentToy.getCartCount() + ")");
        }


        @Override
        public void onClick(View v) {

        }
    }
}

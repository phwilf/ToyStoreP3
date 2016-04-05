package com.example.phwilf.applytheme.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phwilf.applytheme.Cart;
import com.example.phwilf.applytheme.R;
import com.example.phwilf.applytheme.Toy;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

/**
 * Created by phwilf on 4/4/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private static final String Tag = RecyclerAdapter.class.getSimpleName();

    private List<Toy> mData;
    private LayoutInflater mInflater;
    public static Cart cart;

    public RecyclerAdapter(Context context, List<Toy> data){
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
        cart = new Cart();
        //context = context from which RecyclerAdapter is being called
        //inflater - helps actually inflate the layout for each row in the recycler view
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(Tag, "onCreateViewHolder"); //for testing
        View view = mInflater.inflate(R.layout.list_item, parent, false); //inflate the layout for each card
        MyViewHolder holder = new MyViewHolder(view); // pass the new view of list_item to the viewHolder
        return holder; //return the instance of the myviewholder class
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //going to be called for each row in the recycler view
        //position will be super important for knowing which objects are being intereacted with 
        Log.d(Tag, "onBindViewHolder " + position);

        Toy currentObject = mData.get(position);
        holder.setData(currentObject, position);
        holder.setListeners();

    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        TextView title, description;
        ImageView imgThumb, imgAddCart;
        int position;
        Toy current;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.tvTitle);
            description = (TextView) itemView.findViewById(R.id.tvDescription);
            imgThumb = (ImageView) itemView.findViewById(R.id.img_row);
            imgAddCart = (ImageView) itemView.findViewById(R.id.img_row_addCart);

        }

        public void setData(Toy currentObject, int position) {

                this.title.setText(currentObject.getTitle());
                this.description.setText(currentObject.getDescription());
                this.imgThumb.setImageResource(currentObject.getImageID());
                this.position = position;
                this.current = currentObject;

        }

        public void setListeners(){
            imgAddCart.setOnClickListener(MyViewHolder.this);
        }

        @Override
        public void onClick(View v) {
            Log.d(Tag, "On click, before operation at position " + position + " size of data: " + mData.size());
            switch (v.getId()){
                case R.id.img_row_addCart:
                    addItem(position, current);
                    break;
            }
            Log.d(Tag, "On click, after operation at position " + position + " size of data: " + mData.size());

        }
    }


     // used for swiping, won't use this in the catalogue, but will use this in the cart
    public void remove(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size()); //notify data list of changed size

    }

    //adding an item
    public void addItem(int position, Toy toy){
        //can use a similar method to keep track of which row is being moved to pass to cart
        cart.getUserCart().add(toy);
        Log.d(Tag,"Items in cart: " + cart.getUserCart().size());
    }

    //used for dragging, use in the catalogue
    public void swap(int firstPosition, int secondPosition){
        Collections.swap(mData, firstPosition, secondPosition);
        notifyItemMoved(firstPosition, secondPosition);
    }


}

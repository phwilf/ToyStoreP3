package com.example.phwilf.applytheme.Adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phwilf.applytheme.Activities.CartActivity;
import com.example.phwilf.applytheme.Activities.MainActivity;
import com.example.phwilf.applytheme.Cart;
import com.example.phwilf.applytheme.R;
import com.example.phwilf.applytheme.Toy;
import com.example.phwilf.applytheme.ToyList;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by phwilf on 4/4/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private static final String Tag = RecyclerAdapter.class.getSimpleName();

    private List<Toy> mData;
    private LayoutInflater mInflater;

    public RecyclerAdapter(Context context){
        this.mInflater = LayoutInflater.from(context);
        //context = context from which RecyclerAdapter is being called
        //inflater - helps actually inflate the layout for each row in the recycler view
        Log.d("wiseys", "one");
        new FetchToyData().execute();
        Log.d("wiseys", "two");
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


    class FetchToyData extends AsyncTask<Void, Void, ArrayList<Toy>> {

        String Tag = "importantstuff";

        @Override
        protected ArrayList<Toy> doInBackground(Void... params) {
            ToyList toyList = null;
            InputStream is = null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            try {
                URL url = new URL("http://people.cs.georgetown.edu/~wzhou/toy.data");
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
            Log.d(Tag, "finished");

            return toyList.getToyList();
        }

        protected void onPostExecute(ArrayList<Toy> toyList) {
            Log.d("yolo", "plz");
            mData = toyList;
        }
    }



    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        TextView title, price, itemsInCart;
        ImageView imgThumb, imgAddCart, imgRemoveCart;
        int position;
        Toy current;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.tvTitle);
            price = (TextView) itemView.findViewById(R.id.tvPrice);
            imgThumb = (ImageView) itemView.findViewById(R.id.img_row);
            imgAddCart = (ImageView) itemView.findViewById(R.id.img_row_addCart);
            itemsInCart = (TextView) itemView.findViewById(R.id.num_items_in_cart);
            imgRemoveCart = (ImageView) itemView.findViewById(R.id.img_row_removeCart);
        }

        public void setData(Toy currentObject, int position) {

                this.title.setText(currentObject.getToyName());
                this.price.setText("$" + Integer.toString(currentObject.getPrice()));
                this.imgThumb.setImageBitmap(currentObject.getImage());
                this.position = position;
                this.current = currentObject;
                this.itemsInCart.setText("In cart: " + currentObject.getCartCount());

        }

        public void setListeners(){
            imgAddCart.setOnClickListener(MyViewHolder.this);
            imgRemoveCart.setOnClickListener(MyViewHolder.this);
        }

        @Override
        public void onClick(View v) {
            Log.d(Tag, "On click, before operation at position " + position + " size of data: " + mData.size());
            switch (v.getId()){
                case R.id.img_row_addCart:
                    //add item to cart
                    if (!Cart.getUserCart().contains(current)) {
                        addItem(position, current);
                    }

                    //increment cartCount
                    current.incrementCount();
                    Cart.incrementItems();
                    Cart.addPrice(current.getPrice());

                    break;
                case R.id.img_row_removeCart:
                    //remove item from cart
                    if (Cart.getUserCart().contains(current)) {
                        current.decrementCount();
                        Cart.decrementItems();
                        Cart.substractPrice(current.getPrice());

                        //if no more items, remove from cart of items
                        if(current.getCartCount() == 0){
                            Cart.getUserCart().remove(current);
                        }
                    }


                    break;

            }
            //update cart stats
            this.itemsInCart.setText("In cart: " + current.getCartCount());
            MainActivity.items.setText("Items: " + Cart.getItems());
            MainActivity.totalPrice.setText("Total Price: $" + Cart.getTotalPrice());

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
        Toy newToy = toy;
        newToy.setImage(null);

        Cart.getUserCart().add(newToy);
        Log.d(Tag, "Items in cart: " + Cart.getItems());
    }

    //used for dragging, use in the catalogue
    public void swap(int firstPosition, int secondPosition){
        Collections.swap(mData, firstPosition, secondPosition);
        notifyItemMoved(firstPosition, secondPosition);
    }




}

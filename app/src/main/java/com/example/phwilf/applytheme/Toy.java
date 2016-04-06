package com.example.phwilf.applytheme;

import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by phwilf on 4/4/16.
 * "I'm here" - tshaker
 *
 * Data Model that will be used to inflate data in the ListView
 */
public class Toy implements Serializable {
    //for passing through intent
    @Override
    public String toString() {
        return "Toy{" +
                "imageID=" + imageID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cartCount=" + cartCount +
                '}';
    }

    private static final String Tag = Toy.class.getSimpleName();

    private int imageID;
    private String title;
    private String description;
    private int cartCount;

    //Getters and Setters
    public void setImage(int imageID){
        this.imageID = imageID;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void incrementCount(){this.cartCount +=1;}
    public int getCartCount(){return this.cartCount;}

    public void setDescription( String description){
        this.description = description;
    }

    public int getImageID(){
        return this.imageID;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return this.description;
    }

    public static ArrayList<Toy> getData(){
        Log.d(Tag, "getData() in Toy");

        // this is where we will be getting the datat like img, title, description

        ArrayList<Toy> dataList = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            Log.d(Tag, "getData() - making toy: " + i);
            Toy toy = new Toy();
            if(i%2 == 0){
                toy.setImage(R.mipmap.ic_launcher);
            }
            else{

            }

            toy.setTitle("Toy " + i);
            toy.setDescription("Description");
            toy.cartCount = 0;

            dataList.add(toy);
        }

        Log.d(Tag, "Toy 1 from data list: " + dataList.get(1).getTitle());

        return dataList; //contains the objects to be inflated inside recycler view
    }


}

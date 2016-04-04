package com.example.phwilf.applytheme;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by phwilf on 4/4/16.
 *
 * Data Model that will be used to inflate data in the ListView
 */
public class Toy {

    private static final String Tag = Toy.class.getSimpleName();

    private int imageID;
    private String title;
    private String description;

    //Getters and Setters
    public void setImage(int imageID){
        this.imageID = imageID;
    }
    public void setTitle(String title){
        this.title = title;
    }

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

            dataList.add(toy);
        }

        Log.d(Tag, "Toy 1 from data list: " + dataList.get(1).getTitle());

        return dataList; //contains the objects to be inflated inside recycler view
    }


}

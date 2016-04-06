package com.example.phwilf.applytheme;

import java.util.ArrayList;

/**
 * Created by phwilf on 4/4/16.
 */
public class Cart {
    private ArrayList<Toy> userCart;
    private String websiteURL;

    public Cart(){
        this.userCart = new ArrayList<>();
        this.websiteURL = "";
    }
    Cart(String websiteURL){
        this.userCart = new ArrayList<>();
        this.websiteURL = websiteURL;
    }


    public ArrayList<Toy> getUserCart(){
        return this.userCart;
    }

    public String getWebsiteURL(){
        return this.websiteURL;
    }

    public void setUserCart(ArrayList<Toy> cart){
        this.userCart = cart;
    }

    public void setWebsiteURL(String url){
        this.websiteURL = url;
    }

    public int getTotalItems() {
        return this.getUserCart().size();
    }

}

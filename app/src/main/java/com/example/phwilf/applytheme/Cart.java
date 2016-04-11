package com.example.phwilf.applytheme;

import java.util.ArrayList;

/**
 * Created by phwilf on 4/4/16.
 */
public class Cart {
    static private ArrayList<Toy> userCart = new ArrayList<>();
    static private int totalPrice = 0;
    static private int items = 0;


    public static ArrayList<Toy> getUserCart() {
        return userCart;
    }

    public static void setUserCart(ArrayList<Toy> userCart) {
        Cart.userCart = userCart;
    }

    public static int getTotalPrice() {
        return totalPrice;
    }

    public static void setTotalPrice(int totalPrice) {
        Cart.totalPrice = totalPrice;
    }

    public static int getItems() {
        return items;
    }

    public static void setItems(int items) {
        Cart.items = items;
    }

    public static void incrementItems(){
        Cart.items++;
    }

    public static void decrementItems(){
        Cart.items--;
    }

    public static void addPrice(int price){
        Cart.totalPrice += price;
    }

    public static void substractPrice(int price){
        Cart.totalPrice -= price;
    }




}


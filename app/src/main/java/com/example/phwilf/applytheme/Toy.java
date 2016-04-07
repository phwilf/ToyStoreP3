package com.example.phwilf.applytheme;

import android.os.Parcelable;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by phwilf on 4/4/16.
 * "I'm here" - tshaker
 *
 * Data Model that will be used to inflate data in the ListView
 */
public class Toy implements Serializable {
    String toyName = null;
    Bitmap bmp = null;
    int price = 0;

//    //for passing through intent
//    @Override
//    public String toString() {
//        return "Toy{" +
//                "imageID=" + imageID +
//                ", title='" + title + '\'' +
//                ", description='" + description + '\'' +
//                ", cartCount=" + cartCount +
//                '}';
//    }

    public Toy() {
    }

    public Toy(String toyName, int price, Bitmap bmp) {
        this.toyName = toyName;
        this.bmp = bmp;
        this.price = price;
    }

    public Toy(byte[] byteArray) {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(byteArray);

            int nameLength = buffer.getInt();
            byte[] nameBuffer = new byte[nameLength];
            buffer.get(nameBuffer, 0, nameLength);
            this.toyName = new String(nameBuffer);

            this.price = buffer.getInt();

            int imageLength = buffer.getInt();
            byte[] imageBuffer = new byte[imageLength];
            buffer.get(imageBuffer, 0, imageLength);
            this.bmp = BitmapFactory.decodeByteArray(imageBuffer, 0, imageLength);
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    static Toy getToyInfo(byte[] byteArray) {
        Toy toy = new Toy(byteArray);
        return toy;
    }

    public int getSizeInBytes() {
        int size = 0;
        size += Integer.SIZE + toyName.length();
        size += Integer.SIZE;
        size += Integer.SIZE + getImageSize();

        return size;
    }

    public void setToyName(String newName) {
        toyName = newName;
    }

    public void setPrice(int newPrice) {
        price = newPrice;
    }

    public void setImage(Bitmap newBmp) {
        bmp = newBmp;
    }

    public String getToyName() {
        return toyName;
    }

    public int getPrice() {
        return price;
    }

    public Bitmap getImage() {
        return bmp;
    }

    public int getImageSize() {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        try {
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toByteArray().length;
    }

    void putIntToByteArray(int number, ByteArrayOutputStream baos) throws IOException {
        ByteBuffer b = ByteBuffer.allocate(Integer.SIZE);
        b.putInt(number);
        baos.write(b.array());
    }

    public byte[] toByteArray() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            putIntToByteArray(toyName.length(), baos);
            baos.write(toyName.getBytes());
            putIntToByteArray(price, baos);
            putIntToByteArray(getImageSize(), baos);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return baos.toByteArray();
    }

    // Cart stuff
    private int cartCount;

    public void incrementCount(){this.cartCount +=1;}
    public int getCartCount(){return this.cartCount;}

//    public static ArrayList<Toy> getData(){
//        String Tag = Toy.class.getSimpleName();
//        Log.d(Tag, "getData() in Toy");
//
//        // this is where we will be getting the datat like img, title, description
//
//        ArrayList<Toy> dataList = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++){
//            Log.d(Tag, "getData() - making toy: " + i);
//            Toy toy = new Toy();
//            if(i%2 == 0){
//                toy.setImage(R.mipmap.ic_launcher);
//            }
//            else{
//
//            }
//
//            toy.setTitle("Toy " + i);
//            toy.setDescription("Description");
//            toy.cartCount = 0;
//
//            dataList.add(toy);
//        }
//
//        Log.d(Tag, "Toy 1 from data list: " + dataList.get(1).getTitle());
//
//        return dataList; //contains the objects to be inflated inside recycler view
//    }
}

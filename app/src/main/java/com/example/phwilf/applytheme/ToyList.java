package com.example.phwilf.applytheme;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import android.util.Log;
import java.io.File;

public class ToyList {

    private ArrayList<Toy2> toyList = new ArrayList<Toy2>();

    public ToyList() {
    }

    public ToyList(byte[] byteArray, int length) {
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        int cursor = 0;
        while (cursor < length) {
            int toyLength = buffer.getInt();

            byte[] toyBuffer = new byte[toyLength];
            buffer.get(toyBuffer, 0, toyLength);
            Toy2 toy = new Toy2 (toyBuffer);

            toyList.add(toy);
            cursor += Integer.SIZE + toyLength;
        }
    }

    public void addToy(Toy2 toy) {
        toyList.add(toy);
    }

    public Toy2 getToy(int index) {
        return toyList.get(index);
    }

    public ArrayList<Toy2> getToyList() {
        return toyList;
    }

    public int getNumOfToys() {
        return toyList.size();
    }

    public int getSizeInBytes() {
        int size = 0;
        for (int i = 0; i < toyList.size(); i++) {
            size += toyList.get(i).getSizeInBytes();
        }
        return size;
    }

    void putIntToByteArray(int number, ByteArrayOutputStream baos) throws IOException {
        ByteBuffer b = ByteBuffer.allocate(Integer.SIZE);
        b.putInt(number);
        baos.write(b.array());
    }

    public byte[] toByteArray() {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        try {
            for (int i = 0; i < toyList.size(); i++) {
                Toy2 toy = toyList.get(i);
                byte[] toyBuffer = toy.toByteArray();
                putIntToByteArray(toyBuffer.length, baos);
                baos.write(toyBuffer);
            }
        }
        catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return baos.toByteArray();
    }

    public static void readFromFile(String filename) {
        String Tag = ToyList.class.getSimpleName();
        Log.d(Tag, filename);

        try {
            RandomAccessFile file = new RandomAccessFile(filename, "r"); // TODO: find file!!!

            int length = (int) file.length();
            byte[] temp = new byte[length];
            file.read(temp);
            file.close();
            ToyList toyList = new ToyList(temp, length);
            toyList.getNumOfToys();

//            Log.d(Tag, ((String) toyList.getNumOfToys()));
            for (int i = 0; i < toyList.getNumOfToys(); i++){
                Log.d(Tag, toyList.getToy(i).getToyName());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    // TODO: remove this
    public static ArrayList<Toy> getData(){
        String Tag = Toy.class.getSimpleName();
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

package com.example.phwilf.applytheme;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import android.util.Log;
import java.io.File;
import java.net.URL;

public class ToyList {

    private ArrayList<Toy> toyList = new ArrayList<Toy>();

    public ToyList() {
    }

    public ToyList(ArrayList<Toy> inputList) {
        toyList = inputList;
    }

    public ToyList(byte[] byteArray, int length) {
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        int cursor = 0;
        while (cursor < length) {
            int toyLength = buffer.getInt();

            byte[] toyBuffer = new byte[toyLength];
            buffer.get(toyBuffer, 0, toyLength);
            Toy toy = new Toy (toyBuffer);

            toyList.add(toy);
            cursor += Integer.SIZE + toyLength;
        }
    }

    public void addToy(Toy toy) {
        toyList.add(toy);
    }

    public Toy getToy(int index) {
        return toyList.get(index);
    }

    public ArrayList<Toy> getToyList() {
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
                Toy toy = toyList.get(i);
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
}

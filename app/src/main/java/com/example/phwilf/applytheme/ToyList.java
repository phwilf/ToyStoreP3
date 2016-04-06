package com.example.phwilf.applytheme;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Created by phwilf on 4/5/16.
 */
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
        try {
            RandomAccessFile file = new RandomAccessFile(filename, "r");
            int length = (int) file.length();
            byte[] temp = new byte[length];
            file.read(temp);
            file.close();
            ToyList toyList = new ToyList(temp, length);
            toyList.getNumOfToys();

            System.out.println(toyList.getNumOfToys());
            for (int i = 0; i < toyList.getNumOfToys(); i++){
                System.out.println(toyList.getToy(i).getToyName());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}

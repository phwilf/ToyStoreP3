package com.example.phwilf.applytheme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by phwilf on 4/5/16.
 */
public class Toy2 {

    String toyName = null;
    //BufferedImage image = null;
    Bitmap image = null; /* new */
    int price = 0;

    public Toy2() {
    }

    public Toy2(String toyName, int price, Bitmap image) {
        this.toyName = toyName;
        this.image = image;
        this.price = price;
    }

    public Toy2(byte[] byteArray) {
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);

        int nameLength = buffer.getInt();
        byte[] nameBuffer = new byte[nameLength ];
        buffer.get(nameBuffer, 0, nameLength);
        this.toyName = new String(nameBuffer);

        this.price = buffer.getInt();

        int imageLength = buffer.getInt();
        byte[] imageBuffer = new byte[imageLength];
        // buffer.get(imageBuffer, 0, imageLength);

        this.image = BitmapFactory.decodeByteArray(imageBuffer, 0, imageLength); /* new */

        //this.image = ImageIO.read(new ByteArrayInputStream(imageBuffer));
    }

    static Toy2 getToyInfo(byte[] byteArray) {
        Toy2 toy = new Toy2(byteArray);
        return toy;
    }

    public int getSizeInBytes() {
        int size = 0;
        size += Integer.SIZE + toyName.length();
        size += Integer.SIZE;
        size += Integer.SIZE + getImageSize();

        return size;
    }

    public String getToyName() {
        return toyName;
    }

    public int getPrice() {
        return price;
    }

    public Bitmap getImage() {
        return image;
    }

    public int getImageSize() {
        Bitmap bmp;
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bitmapData = baos.toByteArray();
            return bitmapData.length;


    }

    void putIntToByteArray(int number, ByteArrayOutputStream baos) throws IOException {
        ByteBuffer b = ByteBuffer.allocate(Integer.SIZE);
        b.putInt(number);
        baos.write(b.array());
    }

    public byte[] toByteArray() {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bitmapData = baos.toByteArray();

        return baos.toByteArray();
    }
}

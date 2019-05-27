package com.dawn.aidlapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dawn on 2018/8/31.
 */

public class Book implements Parcelable{
    private String bName;
    private float bPrice;

    public Book() {
    }

    public Book(String bName, float bPrice) {
        this.bName = bName;
        this.bPrice = bPrice;
    }

    protected Book(Parcel in) {
        bName = in.readString();
        bPrice = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bName);
        dest.writeFloat(bPrice);
    }

    public void readFromParcel(Parcel reply) {
        bName = reply.readString();
        bPrice = reply.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public float getbPrice() {
        return bPrice;
    }

    public void setbPrice(float bPrice) {
        this.bPrice = bPrice;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bName='" + bName + '\'' +
                ", bPrice=" + bPrice +
                '}';
    }

}

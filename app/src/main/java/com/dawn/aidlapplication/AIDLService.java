package com.dawn.aidlapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dawn on 2018/8/31.
 */

public class AIDLService extends Service{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyService();
    }

    private class MyService extends IMyAidlInterface.Stub {
        List<Book> books = new ArrayList<>();

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getString() throws RemoteException {
            return "hello aidl";
        }

        @Override
        public List<com.dawn.aidlapplication.Book> getBooks() throws RemoteException {
            return books;
        }

        @Override
        public com.dawn.aidlapplication.Book getBook() throws RemoteException {
            return null;
        }

        @Override
        public int getBookCount() throws RemoteException {
            return books.size();
        }

        @Override
        public void setBookPrice(com.dawn.aidlapplication.Book book, int price) throws RemoteException {

        }

        @Override
        public void setBookName(com.dawn.aidlapplication.Book book, String name) throws RemoteException {

        }

        @Override
        public void addBookIn(com.dawn.aidlapplication.Book book) throws RemoteException {
            books.add(book);
        }

        @Override
        public void addBookOut(com.dawn.aidlapplication.Book book) throws RemoteException {
            books.remove(book);
        }

        @Override
        public void addBookInout(com.dawn.aidlapplication.Book book) throws RemoteException {

        }
    }

}

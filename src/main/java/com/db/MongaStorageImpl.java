package com.db;

public class MongaStorageImpl implements Storage {
    @Override
    public void push(byte[] data) {
        System.out.println("Writing data to db >>>>");
    }
}

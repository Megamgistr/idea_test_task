package com.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileStorageImpl implements Storage {
    @Override
    public void push(byte[] data) {
        try {
            Files.write(Paths.get(String.format("history/%s.json", System.currentTimeMillis())), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

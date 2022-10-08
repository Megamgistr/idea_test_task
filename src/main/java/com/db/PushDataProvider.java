package com.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PushDataProvider {
    private static final Storage storage;

    static {
        storage = System.getenv().containsKey("CI") ? new MongaStorageImpl() : new FileStorageImpl();
    }

    public static void main(String... args) {
        try {
            storage.push(Files.readAllBytes(Paths.get("build/results/jmh/results.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

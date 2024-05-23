package com.example.zerohungerhackathon;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFileManager {
    private File csvFile;

    public CsvFileManager(Context context) {
        csvFile = new File(context.getFilesDir(), "data.csv");
    }

    public List<Item> loadItems() {
        List<Item> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 2) {
                    boolean used = Boolean.parseBoolean(parts[2].trim());
                    if (!used) {
                        items.add(new Item(parts[0], parts[1], "false"));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    public void updateItems(List<Item> items) {

//        // Logging to verify the contents of items
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true))) {
            writer.write("id,name,used\n");  // Write the header
            for (Item item : items) {
                Log.d("tag", "msg " + item.getName());
                writer.write(String.format("%s,%s,%b\n", item.getId(), item.getName(), item.isUsed()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

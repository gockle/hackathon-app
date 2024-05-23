package com.example.zerohungerhackathon;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    ListView listView;
    MyAdapter adapter;
    private final Handler handler = new Handler();
    private Runnable runnableCode;
    CsvFileManager csvFileManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        csvFileManager = new CsvFileManager(this);
        List<Item> items = csvFileManager.loadItems();  // Load items
        adapter = new MyAdapter(this, R.layout.list_item, items, csvFileManager);
        listView.setAdapter(adapter);

        runnableCode = new Runnable() {
            @Override
            public void run() {
                List<Item> currentItems = readCSV();
                // Remove the comparison check if you don't want updates to reintroduce removed items
                adapter.clear();
                adapter.addAll(currentItems);
                adapter.notifyDataSetChanged();
            }
        };
        handler.post(runnableCode);
    }

    private List<Item> readCSV() {
        List<Item> itemList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("data.csv")));
            String line;
            reader.readLine(); // Skip the header line
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                if (split.length > 2 && !Boolean.parseBoolean(split[2].trim())) {  // Check if 'used' is false
                    itemList.add(new Item(split[0],split[1], "false"));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnableCode); // Stop the handler when the app is destroyed
    }
}

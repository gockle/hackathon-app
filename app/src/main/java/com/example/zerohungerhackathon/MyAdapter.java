package com.example.zerohungerhackathon;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import java.util.ArrayList;
public class MyAdapter extends ArrayAdapter<Item> {
    private Context mContext;
    private List<Item> items;
    private CsvFileManager csvFileManager;

    public MyAdapter(Context context, int resource, List<Item> items, CsvFileManager csvFileManager) {
        super(context, resource, items);
        this.mContext = context;
        this.items = new ArrayList<>(items);
        this.csvFileManager = csvFileManager;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Item item = getItem(position);
        TextView textView = convertView.findViewById(R.id.textView);
        textView.setText(item.getName());

        convertView.setOnClickListener(view -> {
            item.setUsed("true");
//            Log.d("UpdateCSV", "Item: " + item.getId() + ", " + item.getName() + ", " + item.isUsed());
            Log.d("UpdateCSV", "Item: " + item.getId() + ", " + item.getName() + ", " + item.getUsed());
//            remove(item);
            notifyDataSetChanged();
            // TODO: conditionally show error based on local db that has already been reconciled with the under devices
            if (item.getName().equals("Kevin")){
                new AlertDialog.Builder(this.mContext)
                        .setTitle("Alert")
                        .setMessage(String.format("Student (%s) has already received food today.", item.getName()))
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }

            csvFileManager.updateItems(items);  // Update CSV after item status changes
        });

        return convertView;
    }
//
//    @Override
//    public void remove(Item object) {
//        super.remove(object);
//        items.remove(object); // Ensure items list is also updated
//    }
}


package com.example.zerohungerhackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
    }

    public void handleButtonPress(View view) {
        String studentId = editText.getText().toString();
        Log.d("handleButtonPress", String.format("Tap for studentId: %s", studentId));
        //TODO: Placeholder to demo alert, replace with logic that handles alert on API response
        if (studentId.equals("test")){
            new AlertDialog.Builder(this)
                    .setTitle("Alert")
                    .setMessage(String.format("Student (%s) has already received food today.", studentId))
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
}

package com.example.zerohungerhackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

        MealRequest request = new MealRequest(studentId, "Twoplus");

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<ApiResponse> call = apiService.postMeal(request);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    Log.d("LOG", "MESSAGE " + response.body());
                    ApiResponse apiResponse = (ApiResponse) response.body();
                    if (apiResponse.isClaimed()){
                        new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Success")
                            .setMessage("Meal is already claimed.")
                            .setNegativeButton(android.R.string.ok, null)
                            .show();
                    }
                    else {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Success")
                                .setMessage("Meal is available.")
                                .setNegativeButton(android.R.string.ok, null)
                                .show();
                    }
                } else {
                    // Handle server error
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Error")
                            .setMessage("Failed to register meal.")
                            .setNegativeButton(android.R.string.ok, null)
                            .show();
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Handle network error
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Network Error")
                        .setMessage("Unable to connect to the server.")
                        .setNegativeButton(android.R.string.ok, null)
                        .show();
            }
        });
    }


}

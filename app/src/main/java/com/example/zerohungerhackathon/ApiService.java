package com.example.zerohungerhackathon;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("api/meals")
    Call<ApiResponse> postMeal(@Body MealRequest mealRequest);
}

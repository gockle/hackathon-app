package com.example.zerohungerhackathon;
public class ApiResponse {
    private boolean claimed;

    public ApiResponse(boolean claimed) {
        this.claimed = claimed;
    }

    // Getters and setters
    public boolean isClaimed() {
        return claimed;
    }
    public void setClaimed(boolean claimed) {
        this.claimed = claimed;
    }

}

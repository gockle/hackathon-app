package com.example.zerohungerhackathon;
public class MealRequest {
    private String studentId;
    private String clientId;

    public MealRequest(String studentId, String clientId) {
        this.studentId = studentId;
        this.clientId = clientId;
    }

    // Getters and setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}

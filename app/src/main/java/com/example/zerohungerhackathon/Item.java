package com.example.zerohungerhackathon;

public class Item {
    private String id;
    private String name;
    private String used;

    public Item(String id, String name, String used) {
        this.id = id;
        this.name = name;
        this.used = used;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getUsed() {
        return used;
    }
    public String isUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }
}

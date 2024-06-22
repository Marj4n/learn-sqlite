package com.marj4n.sqllite_learn.model;

public class Data {
    private String id;
    private final String name;
    private String address;

    public Data(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}

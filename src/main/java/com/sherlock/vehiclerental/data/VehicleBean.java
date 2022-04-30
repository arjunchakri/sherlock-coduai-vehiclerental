package com.sherlock.vehiclerental.data;

import java.util.List;

public class VehicleBean {

    private String id;
    private String type;
    private double price;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public VehicleBean(String id, String type, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }
}

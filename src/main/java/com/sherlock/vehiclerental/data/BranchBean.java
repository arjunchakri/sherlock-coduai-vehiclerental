package com.sherlock.vehiclerental.data;

import java.util.*;

public class BranchBean {

    private String id;
    private Set<String> vehicleTypes;
    private List<VehicleBean> vehicles;
    private Map<String, List<Booking>> bookingsMap;

    public BranchBean(String id, Set<String> vehicleTypes) {
        this.id = id;
        this.vehicleTypes = vehicleTypes;
        this.vehicles = new ArrayList<>();
        this.bookingsMap = new HashMap<>();
    }

    public Map<String, List<Booking>> getBookingsMap() {
        return bookingsMap;
    }

    public String getId() {
        return id;
    }

    public Set<String> getVehicleTypes() {
        return vehicleTypes;
    }

    public List<VehicleBean> getVehicles() {
        return vehicles;
    }

}

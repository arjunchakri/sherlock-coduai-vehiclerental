package com.sherlock.vehiclerental.data;

public class Booking {

    private String branchId;
    private String vehicleId;
    private String vehicleType;
    private int start;
    private int end;

    public String getBranchId() {
        return branchId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }


    public Booking(String branchId, String vehicleId, String vehicleType, int start, int end) {
        this.branchId = branchId;
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.start = start;
        this.end = end;
    }
}

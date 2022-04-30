package com.sherlock.vehiclerental.datastore;

import com.sherlock.vehiclerental.data.Booking;
import com.sherlock.vehiclerental.data.BranchBean;

import java.util.*;

public class DataRepository {

    private Map<String, BranchBean> branchesData;

    private static DataRepository INSTANCE = new DataRepository();

    private DataRepository() {
        init();
    }

    public synchronized void init() {
        branchesData = new HashMap<>();
    }

    public static DataRepository getInstance() {
        return INSTANCE;
    }

    public synchronized boolean addBranch(BranchBean branchBean) {
        if(branchesData.containsKey(branchBean.getId())) {
            return false;
        }
        branchesData.put(branchBean.getId(), branchBean);
        return true;
    }

    public synchronized BranchBean getBranch(String branchId) {
        return branchesData.get(branchId);
    }

    public synchronized boolean addBooking(Booking booking) {
        BranchBean branchBean = getBranch(booking.getBranchId());
        branchBean.getBookingsMap().computeIfAbsent(booking.getVehicleId(), x -> new ArrayList<>()).add(booking);
        return true;
    }

    public synchronized boolean isAvailable(Booking booking, String vehicleId) {
        if(!branchesData.containsKey(booking.getBranchId())) {
            return false;
        }
        BranchBean branchBean = getBranch(booking.getBranchId());
        List<Booking> bookings = branchBean.getBookingsMap().getOrDefault(vehicleId, Collections.emptyList());
        for(Booking eachBooking : bookings) {
            if(eachBooking.getStart() <= booking.getStart() && eachBooking.getEnd() > booking.getStart()) {
                return false;
            }
            if(eachBooking.getStart() < booking.getEnd() && eachBooking.getEnd() >= booking.getEnd()) {
                return false;
            }
            if(booking.getStart() <= eachBooking.getStart() && booking.getEnd() >= eachBooking.getEnd()) {
                return false;
            }
        }
        return true;
    }





}

package com.sherlock.vehiclerental.command.commands;

import com.sherlock.vehiclerental.data.CommandDetails;
import com.sherlock.vehiclerental.command.ICommand;
import com.sherlock.vehiclerental.data.Booking;
import com.sherlock.vehiclerental.data.BranchBean;
import com.sherlock.vehiclerental.data.VehicleBean;
import com.sherlock.vehiclerental.datastore.DataRepository;

import java.util.List;

public class BookVehicleCommand implements ICommand {


    @Override
    public String getName() {
        return "BOOK";
    }

    @Override
    public String perform(CommandDetails commandDetails) {
        List<String> inputData = commandDetails.getData();

        String branchId = inputData.get(1);
        String vehicleType = inputData.get(2);
        int start = Integer.parseInt(inputData.get(3));
        int end = Integer.parseInt(inputData.get(4));
        if(start > end) {
            return "-1";
        }

        Booking booking = new Booking(branchId, "", vehicleType, start, end);

        BranchBean branch = DataRepository.getInstance().getBranch(branchId);
        if(branch == null) {
            return "-1";
        }

        for (VehicleBean vehicleBean : branch.getVehicles()) {
            if(!vehicleBean.getType().equals(booking.getVehicleType())) {
                continue;
            }
            boolean available = DataRepository.getInstance().isAvailable(booking, vehicleBean.getId());
            if(available) {
                booking.setVehicleId(vehicleBean.getId());
                DataRepository.getInstance().addBooking(booking);
                return String.valueOf((int) vehicleBean.getPrice() * (end - start));
            }

        }
        return "-1";
    }
}

package com.sherlock.vehiclerental.command.commands;

import com.sherlock.vehiclerental.data.CommandDetails;
import com.sherlock.vehiclerental.command.ICommand;
import com.sherlock.vehiclerental.data.Booking;
import com.sherlock.vehiclerental.data.BranchBean;
import com.sherlock.vehiclerental.data.VehicleBean;
import com.sherlock.vehiclerental.datastore.DataRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DisplayVehiclesCommand implements ICommand {

    @Override
    public String getName() {
        return "DISPLAY_VEHICLES";
    }

    @Override
    public String perform(CommandDetails commandDetails) {
        List<String> inputData = commandDetails.getData();

        String branchId = inputData.get(1);
        int start = Integer.parseInt(inputData.get(2));
        int end = Integer.parseInt(inputData.get(3));
        if(start > end) {
            return "";
        }

        Booking booking = new Booking(branchId, "", "", start, end);

        BranchBean branch = DataRepository.getInstance().getBranch(branchId);
        if(branch == null) {
            return "";
        }

        List<String> vehicles = new ArrayList<>();
        for (VehicleBean vehicleBean : branch.getVehicles()) {
            boolean available = DataRepository.getInstance().isAvailable(booking, vehicleBean.getId());
            if(available) {
                vehicles.add(vehicleBean.getId());
            }
        }
        return vehicles.stream().collect(Collectors.joining(","));
    }
}

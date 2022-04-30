package com.sherlock.vehiclerental.command.commands;

import com.sherlock.vehiclerental.data.CommandDetails;
import com.sherlock.vehiclerental.command.ICommand;
import com.sherlock.vehiclerental.data.BranchBean;
import com.sherlock.vehiclerental.data.VehicleBean;
import com.sherlock.vehiclerental.datastore.DataRepository;

import java.util.*;

public class AddVehicleCommand implements ICommand {
    
    @Override
    public String getName() {
        return "ADD_VEHICLE";
    }

    @Override
    public String perform(CommandDetails commandDetails) {
        List<String> inputData = commandDetails.getData();

        String branchName = inputData.get(1);
        String vehicleType = inputData.get(2);
        String vehicleId = inputData.get(3);
        double price = Double.parseDouble(inputData.get(4));

        BranchBean branchBean = DataRepository.getInstance().getBranch(branchName);
        if(branchBean == null || !branchBean.getVehicleTypes().contains(vehicleType)) {
            return "FALSE";
        }

        List<VehicleBean> vehicleBeans = branchBean.getVehicles();
        vehicleBeans.add(new VehicleBean(vehicleId, vehicleType, price));
        Collections.sort(vehicleBeans, Comparator.comparingDouble(VehicleBean::getPrice));
        return "TRUE";
    }
}

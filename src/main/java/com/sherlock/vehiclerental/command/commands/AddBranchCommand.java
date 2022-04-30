package com.sherlock.vehiclerental.command.commands;

import com.sherlock.vehiclerental.data.CommandDetails;
import com.sherlock.vehiclerental.command.ICommand;
import com.sherlock.vehiclerental.data.BranchBean;
import com.sherlock.vehiclerental.datastore.DataRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddBranchCommand implements ICommand {

    @Override
    public String getName() {
        return "ADD_BRANCH";
    }

    @Override
    public String perform(CommandDetails commandDetails) {
        List<String> inputData = commandDetails.getData();

        BranchBean branchBean = new BranchBean(inputData.get(1), Arrays.stream(inputData.get(2).split(",")).collect(Collectors.toSet()));

        boolean response = DataRepository.getInstance().addBranch(branchBean);

        return Boolean.toString(response).toUpperCase();
    }
}

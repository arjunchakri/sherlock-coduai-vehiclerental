package com.sherlock.vehiclerental.command;

import com.sherlock.vehiclerental.data.CommandDetails;

public interface ICommand {

    public String getName();

    public String perform(CommandDetails commandDetails);

}

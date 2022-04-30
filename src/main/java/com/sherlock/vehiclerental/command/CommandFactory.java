package com.sherlock.vehiclerental.command;

import com.sherlock.vehiclerental.command.commands.AddBranchCommand;
import com.sherlock.vehiclerental.command.commands.AddVehicleCommand;
import com.sherlock.vehiclerental.command.commands.BookVehicleCommand;
import com.sherlock.vehiclerental.command.commands.DisplayVehiclesCommand;
import com.sherlock.vehiclerental.datastore.DataRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CommandFactory {

    private Map<String, ICommand> commandMap = new HashMap<>();

    private static CommandFactory INSTANCE = new CommandFactory();

    private CommandFactory() {
        commandMap = Arrays.asList(new AddBranchCommand(), new AddVehicleCommand(), new BookVehicleCommand(), new DisplayVehiclesCommand()).stream().collect(Collectors.toMap(ICommand::getName, Function.identity()));
    }

    public static CommandFactory getInstance() {
        return INSTANCE;
    }

    public ICommand getCommand(String commandName) {
        return commandMap.get(commandName);
    }

}

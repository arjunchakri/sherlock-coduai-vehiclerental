package com.sherlock.vehiclerental.command;

import com.sherlock.vehiclerental.data.CommandDetails;
import com.sherlock.vehiclerental.datastore.DataRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandExecutor {

    public List<String> executeCommands(List<String> inputLines) {
        DataRepository.getInstance().init();
        return inputLines.stream()
                .map(line -> getCommandDetails(line))
                .filter(cd -> CommandFactory.getInstance().getCommand(cd.getName()) != null)
                .map(cd -> executeCommand(cd))
                .collect(Collectors.toList());
    }

    private String executeCommand(CommandDetails commandDetails) {
        try {
            return CommandFactory.getInstance().getCommand(commandDetails.getName()).perform(commandDetails);
        } catch (Exception e) {
            return "ERROR EXECUTING COMMAND for command: " + commandDetails + " due to " + e.getMessage();
        }
    }

    private CommandDetails getCommandDetails(String input) {
        String[] pieces = input.split(" ");
        return new CommandDetails(pieces[0], Arrays.asList(pieces));
    }

}

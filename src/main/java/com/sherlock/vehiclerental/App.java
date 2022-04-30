package com.sherlock.vehiclerental;

import com.sherlock.vehiclerental.command.CommandExecutor;
import com.sherlock.vehiclerental.data.CommandDetails;
import com.sherlock.vehiclerental.command.CommandFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Please provide file path as argument.");
            return;
        }
        String fileName = args[0];
        Path path = Paths.get(fileName);

        if(!Files.exists(path) || Files.isDirectory(path)) {
            System.out.println("Invalid file provided on filesystem.");
            return;
        }

        List<String> inputLines = Files.readAllLines(path, Charset.defaultCharset());
        new CommandExecutor()
                .executeCommands(inputLines)
                .stream()
                .forEach(System.out::println);
    }



}

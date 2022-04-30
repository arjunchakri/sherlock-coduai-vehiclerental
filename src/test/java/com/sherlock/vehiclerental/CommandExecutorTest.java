package com.sherlock.vehiclerental;

import com.sherlock.vehiclerental.command.CommandExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class CommandExecutorTest {

    @Test
    public void testExecuteCommandsSuccessCase() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE,VAN",
                "ADD_VEHICLE B1 CAR V1 500",
                "ADD_VEHICLE B1 CAR V2 1000",
                "ADD_VEHICLE B1 BIKE V3 250",
                "ADD_VEHICLE B1 BIKE V4 300",
                "ADD_VEHICLE B1 BUS V5 2500",
                "BOOK B1 VAN 1 5",
                "BOOK B1 CAR 1 3",
                "BOOK B1 BIKE 2 3",
                "BOOK B1 BIKE 2 5",
                "DISPLAY_VEHICLES B1 1 5"
        );
        List<String> expectedOutput = Arrays.asList(
                "TRUE",
                "TRUE",
                "TRUE",
                "TRUE",
                "TRUE",
                "FALSE",
                "-1",
                "1000.0",
                "250.0",
                "900.0",
                "V2"
        );
        List<String> programOutput = new CommandExecutor().executeCommands(commands);
        Assertions.assertIterableEquals(expectedOutput, programOutput);
    }


    @Test
    public void testExecuteCommands_DuplicateBranch() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE,VAN",
                "ADD_BRANCH B1 CAR,BIKE,VAN"
        );
        List<String> expectedOutput = Arrays.asList(
                "TRUE",
                "FALSE"
        );
        List<String> programOutput = new CommandExecutor().executeCommands(commands);
        Assertions.assertIterableEquals(expectedOutput, programOutput);
    }

    @Test
    public void testExecuteCommands_BranchUnavailable() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE,VAN",
                "ADD_VEHICLE B2 BUS V5 2500"
        );
        List<String> expectedOutput = Arrays.asList(
                "TRUE",
                "FALSE"
        );
        List<String> programOutput = new CommandExecutor().executeCommands(commands);
        Assertions.assertIterableEquals(expectedOutput, programOutput);
    }

    @Test
    public void testExecuteCommands_InvalidVehicleType() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE,VAN",
                "ADD_VEHICLE B1 LORRY V5 2500"
        );
        List<String> expectedOutput = Arrays.asList(
                "TRUE",
                "FALSE"
        );
        List<String> programOutput = new CommandExecutor().executeCommands(commands);
        Assertions.assertIterableEquals(expectedOutput, programOutput);
    }

    @Test
    public void testExecuteCommands_InvalidStartEnd() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE,VAN",
                "ADD_VEHICLE B1 CAR V1 500",
                "BOOK B1 CAR 4 3"
        );
        List<String> expectedOutput = Arrays.asList(
                "TRUE",
                "TRUE",
                "-1"
        );
        List<String> programOutput = new CommandExecutor().executeCommands(commands);
        Assertions.assertIterableEquals(expectedOutput, programOutput);
    }

    @Test
    public void testExecuteCommands_BookInvalidBranch() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE,VAN",
                "ADD_VEHICLE B1 CAR V1 500",
                "BOOK B2 CAR 4 3"
        );
        List<String> expectedOutput = Arrays.asList(
                "TRUE",
                "TRUE",
                "-1"
        );
        List<String> programOutput = new CommandExecutor().executeCommands(commands);
        Assertions.assertIterableEquals(expectedOutput, programOutput);
    }

    @Test
    public void testExecuteCommands_DisplayMultipleVehicles() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE,VAN",
                "ADD_VEHICLE B1 CAR V1 500",
                "ADD_VEHICLE B1 CAR V2 1000",
                "ADD_VEHICLE B1 BIKE V3 250",
                "ADD_VEHICLE B1 BIKE V4 300",
                "DISPLAY_VEHICLES B1 1 5"
        );
        List<String> expectedOutput = Arrays.asList(
                "TRUE",
                "TRUE",
                "TRUE",
                "TRUE",
                "TRUE",
                "V3,V4,V1,V2"
        );
        List<String> programOutput = new CommandExecutor().executeCommands(commands);
        Assertions.assertIterableEquals(expectedOutput, programOutput);
    }

    @Test
    public void testExecuteCommands_DisplayInvalidBranch() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE,VAN",
                "ADD_VEHICLE B1 CAR V1 500",
                "ADD_VEHICLE B1 CAR V2 1000",
                "ADD_VEHICLE B1 BIKE V3 250",
                "ADD_VEHICLE B1 BIKE V4 300",
                "DISPLAY_VEHICLES B2 1 5"
        );
        List<String> expectedOutput = Arrays.asList(
                "TRUE",
                "TRUE",
                "TRUE",
                "TRUE",
                "TRUE",
                ""
        );
        List<String> programOutput = new CommandExecutor().executeCommands(commands);
        Assertions.assertIterableEquals(expectedOutput, programOutput);
    }


}

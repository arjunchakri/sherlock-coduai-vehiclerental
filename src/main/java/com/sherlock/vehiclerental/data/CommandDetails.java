package com.sherlock.vehiclerental.data;

import java.util.List;

public class CommandDetails {

    String name;
    List<String> data;

    @Override
    public String toString() {
        return "CommandDetails{" +
                "name='" + name + '\'' +
                ", data=" + data +
                '}';
    }

    public CommandDetails(String name, List<String> data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public List<String> getData() {
        return data;
    }

}

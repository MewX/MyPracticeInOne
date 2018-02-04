package org.mewx.practice.javamockito;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> input = new ArrayList<>();
        input.add("One message");

        Message messageHost = new Message();
        new ServiceCaller().callService(input, messageHost);
    }
}

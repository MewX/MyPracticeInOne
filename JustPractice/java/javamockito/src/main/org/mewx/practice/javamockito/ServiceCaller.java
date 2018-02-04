package org.mewx.practice.javamockito;

import java.util.List;

public class ServiceCaller {
    public void callService(List<String> list, Message messageHost) {
        new Service().runService(list, messageHost);
    }
}

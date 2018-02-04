package org.mewx.practice.javamockito;

import java.util.List;

public class ServiceCaller {
    private Service service = new Service();

    public void callService(List<String> list, Message messageHost) {
        service.runService(list, messageHost);
    }
}

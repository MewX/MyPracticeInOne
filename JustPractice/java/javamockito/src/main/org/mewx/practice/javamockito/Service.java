package org.mewx.practice.javamockito;

import java.util.List;

/**
 * The supposed rule is when the size of the list is less than 2, add a message to message
 */
public class Service {
    static final String ERROR_MSG = "This is an error message";

    public void runService(List<String> list, Message message) {
        final int THRESHOLD = 2;
        if (list.size() < THRESHOLD) {
            message.addMessage(ERROR_MSG);
        }
    }
}

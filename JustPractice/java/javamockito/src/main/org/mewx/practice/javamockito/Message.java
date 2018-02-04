package org.mewx.practice.javamockito;

import java.util.ArrayList;

public class Message {
    private ArrayList<String> messages = new ArrayList<>();

    public void addMessage(String msg) {
        messages.add(msg);
    }

    public int getSize() {
        return messages.size();
    }

    public String getMessageAt(int idx) {
        return idx < messages.size() ? messages.get(idx) : null;
    }
}

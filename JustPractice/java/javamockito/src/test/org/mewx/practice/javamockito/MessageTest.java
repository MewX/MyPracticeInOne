package org.mewx.practice.javamockito;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageTest {
    private Message message;

    @Before
    public void setUp() {
        message = new Message();
    }

    @Test
    public void addMessage() {
        assertEquals(0, message.getSize());
        message.addMessage("new message");
        assertEquals(1, message.getSize());
    }

    @Test
    public void getMessageAt() {
        final String NEW_MSG = "new message";
        assertEquals(null, message.getMessageAt(0));
        message.addMessage(NEW_MSG);
        assertEquals(NEW_MSG, message.getMessageAt(0));
    }
}
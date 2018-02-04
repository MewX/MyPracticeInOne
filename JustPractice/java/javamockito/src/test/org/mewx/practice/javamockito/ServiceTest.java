package org.mewx.practice.javamockito;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ServiceTest {
    private Service service;
    private Message message;
    private ArrayList<String> list;

    @Before
    public void setUp() {
        service = new Service();
        message = new Message();
        list = new ArrayList<>();
    }

    @Test
    public void runServiceWithNoneInList() {
        service.runService(list, message);
        assertEquals(1, message.getSize());
        assertEquals(Service.ERROR_MSG, message.getMessageAt(0));
    }

    @Test
    public void runServiceWithOneInList() {
        list.add("One");

        service.runService(list, message);
        assertEquals(1, message.getSize());
        assertEquals(Service.ERROR_MSG, message.getMessageAt(0));
    }

    @Test
    public void runServiceWithTwoInList() {
        list.add("One");
        list.add("Two");

        service.runService(list, message);
        assertEquals(0, message.getSize());
    }
}
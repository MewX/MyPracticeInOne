package org.mewx.practice.javamockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ServiceCallerTest {
    @InjectMocks
    private ServiceCaller serviceCaller = new ServiceCaller();
    @Spy
    private Service serviceInside = new Service(); // NOTE: cannot apply to new Service() objects inside methods // TODO: try PowerMockito
    @Captor
    private ArgumentCaptor<List<String>> argCaptor;

    private Message message;
    private ArrayList<String> list;

    @Before
    public void setUp() {
        // Note: for JUnit 4.5+, no need to initMocks() any more
        message = new Message();
        list = new ArrayList<>();
        list.add("Initial item");
    }

    @Test
    public void callServiceWithOneInList() {
        serviceCaller.callService(list, message);
        verify(serviceInside, times(1)).runService(argCaptor.capture(), eq(message));
        assertEquals(1, argCaptor.getValue().size());
        assertEquals(1, message.getSize());
        assertEquals(Service.ERROR_MSG, message.getMessageAt(0));
    }

    @Test
    public void callServiceWithTwoInList() {
        list.add("Another item");

        serviceCaller.callService(list, message);
        verify(serviceInside, times(1)).runService(argCaptor.capture(), eq(message));
        assertEquals(2, argCaptor.getValue().size());
        assertEquals(0, message.getSize());
    }
}
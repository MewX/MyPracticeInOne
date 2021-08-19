package problem.algorithm;

import org.testng.annotations.Test;
import utils.ListNode;
import utils.ListNodeTools;

import java.util.Arrays;
import java.util.Collections;

import static org.testng.Assert.*;

public class S876Test {

    @Test
    public void testExample1() {
        ListNode listNode = ListNodeTools.buildLinkedList(Arrays.asList(1, 2, 3, 4, 5));
        assertEquals(3, new S876().middleNode(listNode).val);
    }

    @Test
    public void testExample2() {
        ListNode listNode = ListNodeTools.buildLinkedList(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(4, new S876().middleNode(listNode).val);
    }

    @Test
    public void testNull() {
        assertNull(new S876().middleNode(null));
    }

    @Test
    public void testOneElement() {
        ListNode listNode = ListNodeTools.buildLinkedList(Collections.singletonList(1));
        assertEquals(1, new S876().middleNode(listNode).val);
    }

    @Test
    public void testTwoElements() {
        ListNode listNode = ListNodeTools.buildLinkedList(Arrays.asList(1, 2));
        assertEquals(2, new S876().middleNode(listNode).val);
    }

}
package utils;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ListNodeTest {

    @Test
    public void testEqualsDefault() {
        ListNode node1 = ListNodeTools.buildLinkedList(new Integer[]{1, 2, 3});
        ListNode node2 = ListNodeTools.buildLinkedList(new Integer[]{1, 2, 3});
        assertEquals(node1, node2);
    }

    @Test
    public void testNotEqual() {
        ListNode defaultNode = ListNodeTools.buildLinkedList(new Integer[]{1, 2, 3});
        ListNode wrongNode1 = ListNodeTools.buildLinkedList(new Integer[]{0, 2, 3});
        ListNode wrongNode2 = ListNodeTools.buildLinkedList(new Integer[]{1, 0, 3});
        ListNode wrongNode3 = ListNodeTools.buildLinkedList(new Integer[]{1, 2, 0});

        assertNotEquals(defaultNode, wrongNode1);
        assertNotEquals(defaultNode, wrongNode2);
        assertNotEquals(defaultNode, wrongNode3);
    }
}
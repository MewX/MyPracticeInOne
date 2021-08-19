package utils;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class ListNodeToolsTest {

    @Test
    public void testBuildLinkedList() {
        ListNode listNode = ListNodeTools.buildLinkedList(new Integer[]{1, 2, 3});
        assertNotNull(listNode);
        assertEquals(1, listNode.val);
        assertEquals(2, listNode.next.val);
        assertEquals(3, listNode.next.next.val);
    }

    @Test
    public void testBuildLinkedList1() {
        ListNode listNode = ListNodeTools.buildLinkedList(Arrays.asList(1, 2, 3));
        assertNotNull(listNode);
        assertEquals(1, listNode.val);
        assertEquals(2, listNode.next.val);
        assertEquals(3, listNode.next.next.val);
    }

    @Test
    public void testListNodeToList() {
        List<Integer> input = Arrays.asList(1, 2, 3);
        assertNotNull(input);

        ListNode listNode = ListNodeTools.buildLinkedList(input);
        List<Integer> result = ListNodeTools.listNodeToList(listNode);
        assertEquals(input, result);
    }
}
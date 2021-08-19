package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListNodeTools {
    public static ListNode buildLinkedList(Integer[] arr) {
        return buildLinkedList(Arrays.asList(arr));
    }

    public static ListNode buildLinkedList(List<Integer> standardList) {
        if (standardList.isEmpty()) return null;

        ListNode list = new ListNode(standardList.get(0));
        ListNode root = list;
        for (int i = 1; i < standardList.size(); i++) {
            ListNode next = new ListNode(standardList.get(i));
            list.next = next;
            list = next;
        }
        return root;
    }

    public static List<Integer> listNodeToList(ListNode listNode) {
        List<Integer> list = new ArrayList<>();
        while (listNode != null) {
            list.add(listNode.val);
            listNode = listNode.next;
        }
        return list;
    }

}

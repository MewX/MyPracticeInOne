package problem.algorithm;

import utils.ListNode;

public class S876 {

    public ListNode middleNode(ListNode head) {
        if (head == null) return null;

        ListNode mid = head;
        while (head != null && head.next != null) {
            head = head.next;
            mid = mid.next;

            head = head.next;
        }
        return mid;
    }
}

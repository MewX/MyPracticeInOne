package utils;

public class ListNode {
    public int val;
    public ListNode next = null;

    public ListNode(int x) {
        val = x;
    }


    @Override
    public boolean equals(Object other){
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof ListNode)) return false;

        ListNode thisList = this;
        ListNode otherList = (ListNode) other;

        // compare other properties
        while (thisList != null && otherList != null) {
            if (thisList.val != otherList.val) return false;

            thisList = thisList.next;
            otherList = otherList.next;
        }

        return thisList == null && otherList == null;
    }
}

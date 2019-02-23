package problem.algorithm;

import utils.Node;

import java.util.*;

public class S590 {

    // Recursive solution is trivial, could you do it iteratively?
    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        LinkedList<Node> queue = new LinkedList<>();
        Set<Node> done = new HashSet<>();
        queue.push(root);

        while (!queue.isEmpty()) {
            List<Node> first = queue.getFirst().children;
            if (first == null || first.isEmpty() || allChildrenAreDone(queue.getFirst(), done)) {
                Node temp = queue.pollFirst();
                assert temp != null;
                result.add(temp.val);
                done.add(temp);
            } else {
                // not null, add them to the queue
                for (int i = first.size() - 1; i >= 0; i--) {
                    queue.addFirst(first.get(i));
                }
            }
        }
        return result;
    }

    private boolean allChildrenAreDone(Node node, Set<Node> done) {
        for (Node child : node.children) {
            if (!done.contains(child)) {
                return false;
            }
        }
        return true;
    }
}

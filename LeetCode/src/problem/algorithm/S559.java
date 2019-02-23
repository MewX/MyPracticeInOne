package problem.algorithm;

import utils.Node;

public class S559 {
    public int maxDepth(Node root) {
        if (root == null) return 0;

        int extra = 0;
        if (root.children == null) return 1;
        for (Node child : root.children) {
            extra = Math.max(extra, maxDepth(child));
        }
        return extra + 1;
    }
}

package org.mewx.topcoder.problems;

import java.util.Arrays;

/**
 * Created by MewX on 9/9/2016.
 */
public class DeadCode {
    private class GraphNode {
        public int[] next = new int[] {-1, -1}; // -2, -2 means return
    }
    private GraphNode[] nodes;
    private boolean[] accessBegin;
    private boolean[] accessReturn;

    public int deadCount(String[] code) {
        nodes = new GraphNode[code.length];
        accessReturn = new boolean[code.length];
        Arrays.fill(accessReturn, false);
        accessBegin = new boolean[code.length];
        Arrays.fill(accessBegin, false);
        for (int i = 0; i < nodes.length; i ++) {
            nodes[i] = new GraphNode();
        }
        preProceed(code, 0);
        setFromBegin(0);
        setFromEnd();

        // count
        int count = 0;
        for (int i = 0; i < accessReturn.length; i ++) {
            if (!(accessBegin[i] && accessReturn[i])) count ++;
        }
        return count;
    }

    private void preProceed(String[] code, int idx) {
        if (nodes[idx].next[0] != -1) return;
        if (code[idx].equals("RETURN")) {
            nodes[idx].next[0] = nodes[idx].next[1] = -2;
        } else {
            String[] parts = code[idx].split(" "); // [1], [3]
            nodes[idx].next[0] = Integer.valueOf(parts[1]);
            preProceed(code, nodes[idx].next[0]);
            nodes[idx].next[1] = Integer.valueOf(parts[3]);
            preProceed(code, nodes[idx].next[1]);
        }
    }

    private void setFromEnd() {
        for (int r = 0; r < nodes.length; r ++) {
            if (nodes[r].next[0] == nodes[r].next[1] && nodes[r].next[0] == -2) {
                // find the r, set accessible
                accessReturn[r] = true;
                setDependency(r);
            }
        }
    }

    private void setDependency(int idx) {
        for (int i = 0; i < nodes.length; i ++) {
            if (accessReturn[i]) continue;
            if (nodes[i].next[0] == idx || nodes[i].next[1] == idx) {
                accessReturn[i] = true;
                setDependency(i);
            }
        }
    }

    private void setFromBegin(int idx) {
        if (accessBegin[idx]) return;
        accessBegin[idx] = true;
        if (nodes[idx].next[0] == nodes[idx].next[1] && nodes[idx].next[0] == -2) return;
        setFromBegin(nodes[idx].next[0]);
        setFromBegin(nodes[idx].next[1]);
    }
}

package org.mewx.topcoder.problems;

import java.util.*;

/**
 * Created by MewX on 9/7/2016.
 */
public class EllysCandyGame {
    private final String[] candidates = new String[] {"Elly", "Draw", "Kris"}; // 0, 1, 2

    private class Tree {
        public int[] values;
        public int winner = 1;
        public List<Tree> trees;
    }

    public String getWinner(int[] sweets) {
        Tree root = new Tree();
        root.values = Arrays.copyOf(sweets, sweets.length);
        makeSubTree(root, 0, 0, 0);
//        printTree(root, 1);

        return candidates[root.winner];
    }

    private void printTree(Tree node, int level) {
        StringBuilder sb = new StringBuilder();
        sb.append("(" + level + ") - " + node.winner + " | ");
        for (int v : node.values) {
            sb.append(v + ", ");
        }
        System.out.println(sb.toString());

        for (Tree t : node.trees) {
            printTree(t, level + 1);
        }
    }

    private int makeSubTree(Tree node, int sum0, int sum1, int turn) {
        node.trees = new ArrayList<>();
        int resultSave = turn == 0 ? 2 : 0;
        for (int i = 0; i < node.values.length; i ++) {
            if (node.values[i] == 0) continue;

            Tree next = new Tree();
            node.trees.add(next); // not necessary
            next.values = Arrays.copyOf(node.values, node.values.length);
            int save = next.values[i];
            next.values[i] = 0;
            if (i - 1 >= 0) next.values[i - 1] <<= 1;
            if (i + 1 < next.values.length) next.values[i + 1] <<= 1;
            if (turn == 0) {
                int temp = makeSubTree(next, sum0 + save, sum1, 1); // recursive to make tree
                if (temp != 2 && resultSave > temp) resultSave = temp;
            } else {
                int temp = makeSubTree(next, sum0, sum1 + save, 0); // recursive to make tree
                if (temp != 0 && resultSave < temp) resultSave = temp;
            }
        }

        // end condition
        if (node.trees.size() == 0) {
            if (sum0 == sum1) node.winner = 1;
            else if (sum0 > sum1) node.winner = 0;
            else node.winner = 2;
        } else {
            node.winner = resultSave;
        }
        return node.winner;
    }

    public static void main(String[] args) {
        System.out.println(new EllysCandyGame().getWinner(new int[]{20, 50, 70, 0, 30}));
//        System.out.println(new EllysCandyGame().getWinner(new int[]{3, 1, 7, 11, 1, 1}));
//        System.out.println(new EllysCandyGame().getWinner(new int[]{10, 20}));
    }
}

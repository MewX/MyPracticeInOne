package org.mewx.topcoder.problems;

import java.util.Arrays;
import java.util.LinkedList;

public class Fragile2 {

    // it is brute force & graph problem
    public int countPairs(String[] graph) {
        // calc initial parts number
        int parts = 0;
        int[] counts = new int[graph.length];
        Arrays.fill(counts, -1);
        for (int i = 0; i < counts.length; i ++) {
            if (counts[i] != -1) continue;
            counts[i] = ++parts;

            // bfs
            LinkedList<Integer> queue = new LinkedList<>();
            for (int j = 0; j < counts.length; j++) {
                if (graph[i].charAt(j) == 'Y' && counts[j] == -1) {
                    queue.addLast(j);
//                    System.out.format("Main Added: %d\n", j);
                }
            }
            while (queue.size() != 0) {
                int get = queue.removeFirst(); // get first
                if (counts[get] != -1) continue; // already searched
                counts[get] = parts;
                for (int j = 0; j < counts.length; j++) {
                    if (graph[get].charAt(j) == 'Y' && counts[j] == -1) {
                        queue.addLast(j);
//                        System.out.format("While Added: %d\n", j);
                    }
                }
            }
        }
        System.out.println("Main: " + parts);

        // enumerate all the possibility
        int result = 0;
        for (int i = 0; i < counts.length; i ++) {
            for (int j = i + 1; j < counts.length; j ++) {
                if (i == j) continue; // remove the same
                if (countPart(graph, i, j) > parts)
                    result ++;
            }
        }

        return result;
    }

    private int countPart(String[] graph, int r1, int r2) {
        int parts = 0;
        int[] counts = new int[graph.length];
        Arrays.fill(counts, -1);
        for (int i = 0; i < counts.length; i ++) {
            if (counts[i] != -1 || i == r1 || i == r2) continue;
            counts[i] = ++parts;

            // bfs
            LinkedList<Integer> queue = new LinkedList<>();
            for (int j = 0; j < counts.length; j++) {
                if (graph[i].charAt(j) == 'Y' && counts[j] == -1) {
                    queue.addLast(j);
//                    System.out.format("Main Added: %d\n", j);
                }
            }
            while (queue.size() != 0) {
                int get = queue.removeFirst();
                if (counts[get] != -1 || get == r1 || get == r2) continue; // already searched
                counts[get] = parts;
                for (int j = 0; j < counts.length; j++) {
                    if (graph[get].charAt(j) == 'Y' && counts[j] == -1) {
                        queue.addLast(j);
//                        System.out.format("While Added: %d\n", j);
                    }
                }
            }
        }
//        System.out.format("Parts count: %d; (%d) & (%d) are selected.\n", parts, r1, r2);
        return parts;
    }
}

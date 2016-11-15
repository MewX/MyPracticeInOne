package org.mewx.topcoder.problems;

import java.util.HashSet;
import java.util.Set;

public class TeamBuilder {
    private Set<Integer>[] table; // generic array

    public static void main(String[] args) {
        int[] temp = new TeamBuilder().specialLocations(new String[]{"0010", "0001", "0100", "1000"});
        System.out.println(temp[0] + ", " + temp[1]);
    }

    public int[] specialLocations(String[] paths) {
        table = new Set[paths.length];
        for (int i = 0; i < paths.length; i ++) {
            table[i] = new HashSet<>();
            table[i].add(i); // add itself
        }

        HashSet<Integer> tempSet = new HashSet<>();
        for (int i = 0; i < paths.length; i ++) {
            markTheSet(i, tempSet, paths);
        }

        for (int i = 0; i < table.length; i ++) {
            System.out.format("idx: %d  = {", i);
            for (int key : table[i]) {
                System.out.print(key + "  ");
            }
            System.out.println("}");
        }

        // statistic
        int fromAll = 0, toAll = 0;
        for (int i = 0; i < table.length; i ++) {
            if (table[i].size() == table.length) fromAll ++;

            int temp = 0;
            for (int j = 0; j < table.length; j ++)
                if (table[j].contains(i)) temp++;
            if (temp == table.length) toAll ++;
        }


        return new int[] {toAll, fromAll};
    }

    private void markTheSet(final int idx, Set<Integer> current, final String[] paths) {
        table[idx].addAll(current);
        current.add(idx);
        for (int i = 0; i < table.length; i ++) {
            if (paths[idx].charAt(i) == '0' || idx == i) continue;
            if (table[i].contains(idx)) {
                appendOneElement(current, i);
                continue;
            }
//            System.out.format("Accessed :   %2d -> %2d  - carrying {", idx, i);
//            for (int key : current) {
//                System.out.print(key + "  ");
//            }
//            System.out.println("}");

            HashSet<Integer> temp = new HashSet<>(current);
            temp.addAll(table[idx]);
            markTheSet(i, temp, paths); // '1'
        }
        current.remove(idx);
    }

    private void appendOneElement(final Set<Integer> set, final int targetContain) {
        for (int i = 0; i < table.length; i ++) {
            if (table[i].contains(targetContain)) {
                table[i].addAll(set);
//                System.out.format("Appended %d elements by finding {%d} - {", set.size(), i);
//                for (int key : set) {
//                    System.out.print(key + "  ");
//                }
//                System.out.println("}");
            }
        }
    }
}

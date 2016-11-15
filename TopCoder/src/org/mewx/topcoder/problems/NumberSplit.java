package org.mewx.topcoder.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by a1700831 on 31/08/16.
 */
public class NumberSplit {
    private HashMap<Integer, MyTree> map = new HashMap<>();

    private class MyTree {
        public int[] parts;
        public int mainValue; // memorize the largest one
        @Deprecated public MyTree[] childs;
    }

    private class MyTreeFinal {
        public int rootValue;
        public int mainValue;
        public HashMap<String, MyTree> treemap = new HashMap<>();
    }

    public int longestSequence(int start) {

        MyTree myTree = new MyTree();
        myTree.parts = new int[1];
        myTree.parts[0] = start;
        myTree.mainValue = 1;
        buildTree(myTree, myTree.mainValue);
        MyTreeFinal myTreeFinal = convertToMyTreeFinal(myTree); // this can be more efficient
        myTree = convertMyTreeFinalToMyTreeAgain(myTreeFinal);
        map.put(myTree.parts[0], myTree); // only if myTree.parts.length == 1!!!

//        System.out.println("==================");
//        printMyTree(myTree);
//        System.out.println("==================");

        return DFS(myTree);
    }

    @SuppressWarnings("deprecation")
    private void buildTree(MyTree root, int baseValue) {
        List<MyTree> listPart = new ArrayList<>();
        for (int idxPart = 0; idxPart < root.parts.length; idxPart++) {
            // let each part be divided
            String part = Integer.toString(root.parts[idxPart]);
            if (part.length() == 1) continue;

            for (int i = 1; i < part.length(); i++) {
                int part1 = Integer.valueOf(part.substring(0, i));
                int part2 = Integer.valueOf(part.substring(i));
//                if (part1 == 0 || part2 == 0) continue; // no meaning

                MyTree temp = new MyTree();
                temp.parts = new int[root.parts.length + 1];
                for (int idxCp = 0; idxCp < root.parts.length; idxCp++) {
                    if (idxCp == idxPart) continue;
                    temp.parts[idxCp] = root.parts[idxCp];
                }
                temp.parts[idxPart] = part1;
                temp.parts[temp.parts.length - 1] = part2;
                temp.mainValue = baseValue; // for count
                buildTree(temp, baseValue);
                listPart.add(temp);
            }
        }
        root.childs = listPart.toArray(new MyTree[listPart.size()]);
        Arrays.sort(root.parts); // sort all
    }

    private MyTree convertMyTreeFinalToMyTreeAgain(MyTreeFinal myTreeFinal) {
        MyTree root = new MyTree();
        root.mainValue = myTreeFinal.mainValue;
        root.parts = new int[1];
        root.parts[0] = myTreeFinal.rootValue;
        root.childs = new MyTree[myTreeFinal.treemap.size()];

        int i = 0;
        for (String key : myTreeFinal.treemap.keySet()) {
            MyTree node = myTreeFinal.treemap.get(key);
            node.childs = null;
            root.childs[i ++] = node;
        }
        return root;
    }

    @SuppressWarnings("deprecation")
    private MyTreeFinal convertToMyTreeFinal(MyTree root) {
        MyTreeFinal t = new MyTreeFinal();
        t.mainValue = root.mainValue;
        t.rootValue = root.parts[0];
        for (MyTree tree : root.childs) {
            addToMyTreeFinal(tree, t);
        }
        return t;
    }

    @SuppressWarnings("deprecation")
    private void addToMyTreeFinal(MyTree root, MyTreeFinal dest) {
        String arg = intToStringSig(root.parts);
        if (!dest.treemap.containsKey(arg)) {
            dest.treemap.put(arg, root);
            for (MyTree tree : root.childs) {
                addToMyTreeFinal(tree, dest);
            }
        }
    }

    private String intToStringSig(int[] arr) {
        StringBuilder temp = new StringBuilder();
        for (int i : arr) {
            temp.append(i + ",");
        }
        return temp.toString();
    }

    @Deprecated
    private void printMyTree(MyTree root) {
        System.out.print("Node value: ");
        for (int i : root.parts) {
            System.out.print(i + ", ");
        }
        System.out.println();

        if (root.childs == null) return;
        for (MyTree tree : root.childs) {
            printMyTree(tree);
        }
    }

    private void printMyTreeFinal(MyTreeFinal root) {
        for (String key : root.treemap.keySet()) {
            System.out.println("Node value: " + key);
        }
    }

    private int DFS(MyTree root) {
        int largest = root.mainValue;
        int lenSub = 0;
        if (root.parts.length != 1) {
            int m = multiply(root.parts);
            if (Integer.toString(m).length() == 1) return largest + 1;

            if (!map.containsKey(m)) {
                MyTree newTree = new MyTree();
                newTree.parts = new int[1];
                newTree.parts[0] = m;
                newTree.mainValue = root.mainValue + 1;
                buildTree(newTree, newTree.mainValue);
                MyTreeFinal myTreeFinal = convertToMyTreeFinal(newTree); // this can be more efficient
                newTree = convertMyTreeFinalToMyTreeAgain(myTreeFinal);

//                System.out.println("==================");
//                printMyTree(newTree);
//                System.out.println("==================");

                map.put(m, newTree);
            } else {
                resetMainValue(map.get(m), root.mainValue + 1);
            }
            lenSub = DFS(map.get(m));
        } else if (Integer.toString(root.parts[0]).length() == 1)
            return largest;

        if (root.childs != null) {
            for (MyTree tree : root.childs) {
                int lenCurrent = DFS(tree);
                if (lenCurrent > largest) largest = lenCurrent;
            }
        }

        return Integer.max(lenSub, largest);
    }

    private void resetMainValue(MyTree root, int newVal) {
        if (root.mainValue > newVal) return;

        // DFS to reset all the main value
        root.mainValue = newVal;
        if (root.childs != null) {
            for (MyTree tree : root.childs) {
                resetMainValue(tree, newVal);
            }
        }
    }

    private int multiply(int[] arr) {
        int m = 1;
        for (int i : arr) m *= i;
        return m;
    }
}

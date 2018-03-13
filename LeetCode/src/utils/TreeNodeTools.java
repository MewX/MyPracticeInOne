package utils;

import java.util.ArrayList;
import java.util.LinkedList;

public class TreeNodeTools {

    public static TreeNode buildBinaryTree(Integer[] nodes) {
        if(nodes == null || nodes.length == 0) return null;

        LinkedList<TreeNode> list = new LinkedList<>();
        TreeNode root = nodes[0] == null ? null : new TreeNode(nodes[0]);
        list.addLast(root);

        int i = 1;
        while (i < nodes.length && list.size() != 0) {
            if (list.getFirst() == null) {
                list.removeFirst();
                continue;
            }

            // left node
            TreeNode newNodeLeft = nodes[i] == null ? null : new TreeNode(nodes[i]);
            i ++;
            list.getFirst().left = newNodeLeft;
            list.addLast(newNodeLeft);

            // right node
            if (i < nodes.length) {
                TreeNode newNodeRight = nodes[i] == null ? null : new TreeNode(nodes[i]);
                i ++;
                list.getFirst().right = newNodeRight;
                list.addLast(newNodeRight);
            }

            list.removeFirst();
        }
        return root;
    }

    public static TreeNode makeBinaryTree(Integer[] nodes) {
        if(nodes.length == 0) return null;

        LinkedList<TreeNode> current = new LinkedList<>(), next;
        TreeNode root = new TreeNode(nodes[0]);
        current.offer(root);

        int i = 1;
        while(i < nodes.length) {
            next = new LinkedList<>();
            while(current.size() > 0) {
                TreeNode temp = current.poll();
                if(temp == null) continue;
                if(i < nodes.length) {
                    if(nodes[i] == null) temp.left = null;
                    else temp.left = new TreeNode(nodes[i]);
                    next.offer(temp.left);
                    i ++;
                }
                if(i < nodes.length) {
                    if(nodes[i] == null) temp.right = null;
                    else temp.right = new TreeNode(nodes[i]);
                    next.offer(temp.right);
                    i ++;
                }
                else break;
            }
            current = next;
        }
        return root;
    }

    public static Integer[] fellBinaryTree(TreeNode root) {
        if (root == null) return new Integer[0];

        ArrayList<Integer> ret = new ArrayList<>();
        LinkedList<TreeNode> temp = new LinkedList<>();
        temp.add(root);

        while (temp.size() != 0) {
            if (temp.getFirst() == null) {
                ret.add(null);
                temp.removeFirst();
                continue;
            }

            ret.add(temp.getFirst().val);
            temp.addLast(temp.getFirst().left);
            temp.addLast(temp.getFirst().right);
            temp.removeFirst();
        }

        // remove trailing nulls
        for (int i = ret.size() - 1; i >= 0; i --) {
            if (ret.get(i) != null) break;
            ret.remove(i);
        }
        return ret.toArray(new Integer[ret.size()]);
    }

    public static TreeNode findBinaryTreeByInt(TreeNode root, Integer val) {
        if(root == null) return null;

        if(root.val.equals(val)) return root;
        else {
            TreeNode temp = findBinaryTreeByInt(root.left, val);
            if(temp != null) return temp;
            else return findBinaryTreeByInt(root.right, val);
        }
    }

    public static boolean compareTwoTrees(TreeNode root1, TreeNode root2) {
        if ((root1 == null) ^ (root2 == null)) return false; // only one of them is null

        if (root1 != null) {
            if (!root1.val.equals(root2.val)) return false;
            // else
            return compareTwoTrees(root1.left, root2.left) && compareTwoTrees(root1.right, root2.right);
        }
        return true;
    }
}

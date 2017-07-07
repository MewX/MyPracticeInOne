package utils;

import java.util.LinkedList;

public class TreeNodeTools {
    public static TreeNode makeBinaryTree(Integer[] nodes) {
        if(nodes.length == 0) return null;

        LinkedList<TreeNode> current = new LinkedList<>(), next;
        TreeNode root = new TreeNode(nodes[0]);
        current.offer(root);

        int i = 1, node;
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

    public static TreeNode findBinaryTreeByInt(TreeNode root, Integer val) {
        if(root == null) return null;

        if(root.val.equals(val)) return root;
        else {
            TreeNode temp = findBinaryTreeByInt(root.left, val);
            if(temp != null) return temp;
            else return findBinaryTreeByInt(root.right, val);
        }
    }
}

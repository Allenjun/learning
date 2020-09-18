package com.allen.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JUN @Description TODO
 * @createTime 11:48
 */
public class TreeNode {

    private int data;
    private TreeNode[] childs;

    public TreeNode(int data, TreeNode[] childs) {
        this.data = data;
        this.childs = childs;
    }

    public static void main(String[] args) {
        TreeNode cc1 = new TreeNode(5, new TreeNode[]{});
        TreeNode cc2 = new TreeNode(6, new TreeNode[]{});
        TreeNode cc3 = new TreeNode(7, new TreeNode[]{});
        TreeNode aa1 = new TreeNode(2, new TreeNode[]{cc1, cc2});
        TreeNode aa2 = new TreeNode(3, new TreeNode[]{cc3});
        TreeNode aa3 = new TreeNode(4, new TreeNode[]{});
        TreeNode r = new TreeNode(1, new TreeNode[]{aa1, aa2, aa3});

        System.out.println(r.transferToBinaryTree().preOrder());
        System.out.println(r.transferToBinaryTree().midOrder());
        System.out.println(r.transferToBinaryTree().lastOrder());
    }

    /**
     * @description: 普通树转二叉树
     */
    public BinaryTreeNode transferToBinaryTree() {
        if (childs != null && childs.length > 0) {
            List<BinaryTreeNode> nodes = new ArrayList<>();
            for (TreeNode child : childs) {
                nodes.add(child.transferToBinaryTree());
            }
            for (int i = 0; i < nodes.size() - 1; i++) {
                nodes.get(i).setRight(nodes.get(i + 1));
            }
            return new BinaryTreeNode(nodes.get(0), null, data);
        }
        return new BinaryTreeNode(data);
    }
}

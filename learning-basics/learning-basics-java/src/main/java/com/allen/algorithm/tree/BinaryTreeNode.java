package com.allen.algorithm.tree;

/**
 * @author JUN @Description TODO
 * @createTime 11:35
 */
public class BinaryTreeNode {

    private BinaryTreeNode parent;
    private BinaryTreeNode left;
    private BinaryTreeNode right;
    private int height;
    private int data;

    public BinaryTreeNode(int data) {
        this(null, null, data);
    }

    public BinaryTreeNode(BinaryTreeNode left, BinaryTreeNode right, int data) {
        this(null, left, right, data);
    }

    public BinaryTreeNode(
            BinaryTreeNode parent, BinaryTreeNode left, BinaryTreeNode right, int data) {
        this(parent, left, right, 1, data);
    }

    public BinaryTreeNode(
            BinaryTreeNode parent, BinaryTreeNode left, BinaryTreeNode right, int height, int data) {
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.height = height;
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BinaryTreeNode getParent() {
        return parent;
    }

    public void setParent(BinaryTreeNode parent) {
        this.parent = parent;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String lastOrder() {
        return (left == null ? "X" : left.lastOrder())
                + ", "
                + (right == null ? "X" : right.lastOrder())
                + ", "
                + data;
    }

    public String midOrder() {
        return (left == null ? "X" : left.midOrder())
                + ", "
                + data
                + ", "
                + (right == null ? "X" : right.midOrder());
    }

    public String preOrder() {
        return data
                + ", "
                + (left == null ? "X" : left.preOrder())
                + ", "
                + (right == null ? "X" : right.preOrder());
    }
}

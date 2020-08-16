package com.allen.algorithm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author JUN
 * @Description TODO
 * @createTime 10:53
 */
public class TreeTraversal {
    
    public static void main(String[] args) {
        BinaryTreeNode node = new BinaryTreeNode(1);
        node.setLeft(new BinaryTreeNode(2));
        node.setRight(new BinaryTreeNode(3));
        node.getLeft().setLeft(new BinaryTreeNode(4));
        node.getLeft().setRight(new BinaryTreeNode(5));
        node.getRight().setLeft(new BinaryTreeNode(6));
        node.getRight().setRight(new BinaryTreeNode(7));
        System.out.println(TreeTraversal.preOrder(node));
        System.out.println(TreeTraversal.midOrder(node));
        System.out.println(TreeTraversal.postOrder(node));
    }
    
    public static List<Integer> preOrder(BinaryTreeNode node) {
        ArrayList<Integer> order = new ArrayList<>();
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode cur = node;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                order.add(cur.getData());
                cur = cur.getLeft();
            }
            if (!stack.isEmpty()) {
                cur = stack.pop().getRight();
            }
        }
        return order;
    }
    
    public static List<Integer> midOrder(BinaryTreeNode node) {
        ArrayList<Integer> order = new ArrayList<>();
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode cur = node;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.getLeft();
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
                order.add(cur.getData());
                cur = cur.getRight();
            }
        }
        return order;
    }
    
    public static List<Integer> postOrder(BinaryTreeNode node) {
        ArrayList<Integer> order = new ArrayList<>();
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode cur = node;
        BinaryTreeNode pre = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.getLeft();
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
                
                cur = cur.getRight();
            }
        }
        return order;
    }
    
}

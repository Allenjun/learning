package com.allen.algorithm.tree;

/**
 * @author JUN
 * @Description TODO
 * @createTime 16:13
 */
public class AVLTree extends BinarySearchTree {
    
    public static void main(String[] args) {
        // 3,2,1,4,5,6,7,16,15,14,13,12,11,10,8,9
        AVLTree avlTree = new AVLTree();
        BinaryTreeNode root = avlTree.avlInsert(null, 3);
        root = avlTree.avlInsert(root, 2);
        root = avlTree.avlInsert(root, 1);
        root = avlTree.avlInsert(root, 4);
        root = avlTree.avlInsert(root, 5);
        root = avlTree.avlInsert(root, 6);
        root = avlTree.avlInsert(root, 7);
        root = avlTree.avlInsert(root, 16);
        root = avlTree.avlInsert(root, 15);
        root = avlTree.avlInsert(root, 14);
        root = avlTree.avlInsert(root, 13);
        root = avlTree.avlInsert(root, 12);
        root = avlTree.avlInsert(root, 11);
        root = avlTree.avlInsert(root, 10);
        root = avlTree.avlInsert(root, 8);
        root = avlTree.avlInsert(root, 9);
        System.out.println(root.preOrder());
        System.out.println(root.midOrder());
        
        avlTree.avlDelete(root, 13);
        System.out.println(root.preOrder());
        System.out.println(root.midOrder());
    }
    
    public BinaryTreeNode avlInsert(BinaryTreeNode node, int data) {
        if (node == null) {
            return new BinaryTreeNode(data);
        }
        if (node.getData() == data) {
            throw new IllegalArgumentException();
        }
        if (node.getData() < data) {
            node.setRight(avlInsert(node.getRight(), data));
            if (height(node.getRight()) - height(node.getLeft()) == 2) {
                if (node.getRight().getData() > data) {
                    node = rightLeftRotation(node);
                } else {
                    node = rightRightRotation(node);
                }
            }
        } else {
            node.setLeft(avlInsert(node.getLeft(), data));
            if (height(node.getLeft()) - height(node.getRight()) == 2) {
                if (node.getLeft().getData() > data) {
                    node = leftLeftRotation(node);
                } else {
                    node = leftRightRotaion(node);
                }
            }
        }
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        return node;
    }
    
    private BinaryTreeNode leftRightRotaion(BinaryTreeNode node) {
        node.setLeft(rightRightRotation(node.getLeft()));
        return leftLeftRotation(node);
    }
    
    private BinaryTreeNode leftLeftRotation(BinaryTreeNode node) {
        BinaryTreeNode left = node.getLeft();
        node.setLeft(left.getRight());
        left.setRight(node);
        left.setHeight(Math.max(height(left.getLeft()), height(left.getRight())) + 1);
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        return left;
    }
    
    private int height(BinaryTreeNode node) {
        return node == null ? 0 : node.getHeight();
    }
    
    private BinaryTreeNode rightRightRotation(BinaryTreeNode node) {
        BinaryTreeNode right = node.getRight();
        node.setRight(right.getLeft());
        right.setLeft(node);
        right.setHeight(Math.max(height(right.getLeft()), height(right.getRight())) + 1);
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        return right;
    }
    
    private BinaryTreeNode rightLeftRotation(BinaryTreeNode node) {
        node.setRight(leftLeftRotation(node.getRight()));
        return rightRightRotation(node);
    }
    
    public BinaryTreeNode avlDelete(BinaryTreeNode node, int data) {
        if (node == null) {
            return null;
        }
        if (node.getData() < data) {
            node.setRight(avlDelete(node.getRight(), data));
            if (height(node.getRight()) - height(node.getLeft()) == 2) {
                if (node.getRight().getData() > data) {
                    node = rightLeftRotation(node);
                } else {
                    node = rightRightRotation(node);
                }
            }
        } else if (node.getData() > data) {
            node.setLeft(avlDelete(node.getLeft(), data));
            if (height(node.getLeft()) - height(node.getRight()) == 2) {
                if (node.getLeft().getData() > data) {
                    node = leftLeftRotation(node);
                } else {
                    node = leftRightRotaion(node);
                }
            }
        } else {
            if (node.getLeft() != null && node.getRight() != null) {
                BinaryTreeNode pre = max(node.getLeft());
                node.setData(pre.getData());
                node.setLeft(avlDelete(node.getLeft(), pre.getData()));
            } else {
                node = node.getLeft() == null ? node.getRight() : node.getLeft();
            }
        }
        return node;
    }
    
}

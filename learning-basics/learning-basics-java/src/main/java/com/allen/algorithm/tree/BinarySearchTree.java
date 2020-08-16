package com.allen.algorithm.tree;

/**
 * @author JUN
 * @Description TODO
 * @createTime 16:33
 */
public class BinarySearchTree {
    
    public static void main(String[] args) {
        BinaryTreeNode c1 = new BinaryTreeNode(null, null, 7);
        BinaryTreeNode b2 = new BinaryTreeNode(null, c1, 6);
        BinaryTreeNode b1 = new BinaryTreeNode(null, null, 1);
        BinaryTreeNode a2 = new BinaryTreeNode(b2, null, 8);
        BinaryTreeNode a1 = new BinaryTreeNode(b1, null, 3);
        BinaryTreeNode root = new BinaryTreeNode(a1, a2, 5);
        a1.setParent(root);
        a2.setParent(root);
        b1.setParent(a1);
        b2.setParent(a2);
        c1.setParent(b2);
        
        BinarySearchTree searchTree = new BinarySearchTree();
        System.out.println(searchTree.search(root, 1));
        
        searchTree.insert(root, 9);
        System.out.println(root.midOrder());
        
        searchTree.delete(root, 3);
        System.out.println(root.midOrder());
    }
    
    public BinaryTreeNode search(BinaryTreeNode node, int data) {
        if (node == null) {
            return null;
        }
        if (node.getData() == data) {
            return node;
        } else if (node.getData() < data) {
            return search(node.getRight(), data);
        } else {
            return search(node.getLeft(), data);
        }
    }
    
    public boolean insert(BinaryTreeNode node, int data) {
        if (node == null) {
            return false;
        }
        if (node.getData() == data) {
            return false;
        } else if (node.getData() < data) {
            if (node.getRight() == null) {
                node.setRight(new BinaryTreeNode(node, null, null, data));
                return true;
            } else {
                return insert(node.getRight(), data);
            }
        } else {
            if (node.getLeft() == null) {
                node.setLeft(new BinaryTreeNode(node, null, null, data));
                return true;
            } else {
                return insert(node.getLeft(), data);
            }
        }
    }
    
    public boolean delete(BinaryTreeNode node, int data) {
        BinaryTreeNode search = search(node, data);
        if (search == null) {
            return true;
        }
        // 情况一：被删除的是叶子结点
        if (search.getLeft() == null && search.getRight() == null) {
            if (search.getParent().getRight() == search) {
                search.getParent().setRight(null);
            }
            if (search.getParent().getLeft() == search) {
                search.getParent().setLeft(null);
            }
            return true;
        }
        // 情况二：被删除的节点有一个子节点
        if (search.getLeft() == null || search.getRight() == null) {
            if (search.getParent().getRight() == search) {
                if (search.getLeft() == null) {
                    search.getParent().setRight(search.getRight());
                } else {
                    search.getParent().setRight(search.getLeft());
                }
            }
            if (search.getParent().getLeft() == search) {
                if (search.getLeft() == null) {
                    search.getParent().setLeft(search.getRight());
                } else {
                    search.getParent().setLeft(search.getLeft());
                }
            }
            return true;
        }
        // 情况三：被删除的节点有两个子节点
        if (search.getLeft() != null && search.getRight() != null) {
            BinaryTreeNode next = min(search.getRight());
            search.setData(next.getData());
            next.getParent().setLeft(next.getRight());
            return true;
        }
        return false;
    }
    
    public BinaryTreeNode max(BinaryTreeNode node) {
        if (node == null) {
            return null;
        }
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }
    
    public BinaryTreeNode min(BinaryTreeNode node) {
        if (node == null) {
            return null;
        }
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }
}

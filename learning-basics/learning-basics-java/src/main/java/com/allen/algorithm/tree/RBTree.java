package com.allen.algorithm.tree;

/**
 * @author JUN
 * @Description TODO
 * @createTime 13:36
 */
public class RBTree<K extends Comparable<K>, V> {
    
    public static final boolean RED = true;
    public static final boolean BLACK = false;
    
    private Node root;
    
    public static void main(String[] args) {
        RBTree<Integer, Object> rbTree = new RBTree<>();
        rbTree.insert(1, 1);
        rbTree.insert(2, 1);
        rbTree.insert(3, 1);
        rbTree.insert(4, 1);
        rbTree.insert(5, 1);
        rbTree.insert(1, 6);
        System.out.println(rbTree.printTree());

//        boolean del = rbTree.delete(1);
//        System.out.println(rbTree.printTree());
        
        Object o = rbTree.get(1);
        System.out.println(o);
    }
    
    public String printTree() {
        return printTree(root);
    }
    
    private String printTree(Node node) {
        if (node != null) {
            return printTree(node.left) + ", " + node.key + ", " + printTree(node.right);
        }
        return "X";
    }
    
    private V get(K key) {
        return get(root, key);
    }
    
    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) > 0) {
            return get(node.left, key);
        } else if (node.key.compareTo(key) < 0) {
            return get(node.right, key);
        } else {
            return node.value;
        }
    }
    
    private V delete(K key) {
        V v = get(root, key);
        if (v != null) {
            root = delete(root, key);
            return v;
        }
        return null;
    }
    
    private Node delete(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) > 0) {
            node.left = delete(node.left, key);
        } else if (node.key.compareTo(key) < 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.right != null) {
                Node successor = minNode(node.right);
                node.key = successor.key;
                node.value = successor.value;
                node.right = delete(node.right, successor.key);
            } else {
                return node.left;
            }
        }
        return null;
    }
    
    private void insert(K key, V value) {
        root = insert(root, key, value);
        root.color = RBTree.BLACK;
    }
    
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }
    
    private Node insert(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value);
        }
        if (key.compareTo(node.key) > 0) {
            node.right = insert(node.right, key, value);
        } else if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key, value);
        } else {
            node.value = value;
        }
        
        // 把左黑或（nil），右红的情况进行左旋转
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotation(node);
        }
        // 把连续是两个红子节点进行右旋转
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotation(node);
        }
        // 把左右节点都是红色进行重绘色
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        
        return node;
    }
    
    private Node leftRotation(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        right.color = node.color;
        node.color = RED;
        return right;
    }
    
    private Node rightRotation(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        left.color = node.color;
        node.color = RED;
        return left;
    }
    
    private boolean isRed(Node node) {
        return node != null && node.color;
    }
    
    private Node minNode(Node node) {
        if (node.left == null) {
            return node;
        }
        return minNode(node.left);
    }
    
    class Node {
        
        public boolean color;
        public Node left;
        public Node right;
        public K key;
        public V value;
        
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.color = RED;
        }
    }
}

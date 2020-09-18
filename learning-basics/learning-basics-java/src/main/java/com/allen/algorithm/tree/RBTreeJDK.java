package com.allen.algorithm.tree;

import java.util.Map;

/**
 * @author JUN @Description 用jdk1.8的TreeMap源码实现红黑树
 * @createTime 16:01
 */
public class RBTreeJDK<K extends Comparable<K>, V> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private Node<K, V> root;

    public static void main(String[] args) {
        RBTreeJDK<Integer, Object> rbTreeJDK = new RBTreeJDK<>();
        rbTreeJDK.insert(7, 1);
        rbTreeJDK.insert(2, 2);
        rbTreeJDK.insert(6, 3);
        rbTreeJDK.insert(1, 4);
        rbTreeJDK.insert(3, 5);
        rbTreeJDK.insert(5, 6);
        rbTreeJDK.insert(9, 7);
        rbTreeJDK.insert(0, 8);
        rbTreeJDK.printTree();
        System.out.println(rbTreeJDK);

        Object obj = rbTreeJDK.remove(6);
        System.out.println(obj);
    }

    private V remove(K key) {
        Node<K, V> p = find(key);
        if (p == null) {
            return null;
        }
        V oldValue = p.value;
        remove(p);
        return oldValue;
    }

    private void remove(Node<K, V> p) {
        if (p.left != null && p.right != null) {
            Node<K, V> s = successor(p);
            p.key = s.key;
            p.value = s.value;
            p = s;
        }
        Node<K, V> replacement = p.right;
        if (replacement == null) {
            if (p.color == BLACK) {
                fixAfterDeletion(p);
            }
            if (p.parent != null) {
                if (p.parent.left == p) {
                    p.parent.left = null;
                } else {
                    p.parent.right = null;
                }
                p.parent = null;
            }
        } else {
            // 待删除节点有一个子节点
            replacement.parent = p.parent;
            if (replacement.parent == null) {
                root = replacement;
            } else {
                if (replacement.parent.left == p) {
                    replacement.parent.left = replacement;
                } else {
                    replacement.parent.right = replacement;
                }
                p.parent = p.left = p.right = null;
            }
            if (p.color == BLACK) {
                fixAfterDeletion(replacement);
            }
        }
    }

    private void fixAfterDeletion(Node<K, V> x) {
        while (x != root && colorOf(x) == BLACK) {
            if (x == leftOf(parentOf(x))) {
                Node<K, V> sib = rightOf(parentOf(x));
                if (colorOf(sib) == RED) {
                    // 情况一：兄弟节点是红色
                    setColor(parentOf(x), RED);
                    setColor(sib, BLACK);
                    rotateLeft(parentOf(x));
                    sib = rightOf(parentOf(x));
                }
                // 情况二：兄弟节点是黑色
                if (colorOf(rightOf(sib)) == BLACK && colorOf(leftOf(sib)) == BLACK) {
                    // 情况三: 兄弟节点的子节点都是黑色
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    // 情况四：兄弟节点的子节点不全是黑色
                    if (colorOf(rightOf(sib)) == BLACK) {
                        // 黑色节点在右边
                        setColor(sib, RED);
                        setColor(leftOf(sib), BLACK);
                        rotateRight(sib);
                        sib = rightOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(rightOf(sib), BLACK);
                    rotateLeft(parentOf(x));
                    x = root;
                }
            } else {
                Node<K, V> sib = leftOf(parentOf(x));
                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateRight(parentOf(x));
                    sib = leftOf(parentOf(x));
                }
                if (colorOf(leftOf(sib)) == BLACK && colorOf(rightOf(sib)) == BLACK) {
                    setColor(sib, BLACK);
                    x = parentOf(x);
                } else {
                    if (colorOf(leftOf(sib)) == BLACK) {
                        setColor(sib, RED);
                        setColor(rightOf(sib), BLACK);
                        rotateLeft(sib);
                        sib = leftOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(leftOf(sib), BLACK);
                    rotateRight(parentOf(x));
                    x = root;
                }
            }
        }
        setColor(x, BLACK);
    }

    private Node<K, V> successor(Node<K, V> t) {
        if (t == null) {
            return null;
        } else if (t.right != null) {
            Node<K, V> r = t.right;
            while (r.left != null) {
                r = r.left;
            }
            return r;
        } else {
            Node<K, V> p = t.parent;
            while (p != null && p.right == t) {
                t = p;
                p = p.parent;
            }
            return p;
        }
    }

    private Node<K, V> find(K key) {
        Node<K, V> t = this.root;
        while (t != null && t.key.compareTo(key) != 0) {
            t = t.key.compareTo(key) > 0 ? t.left : t.right;
        }
        return t;
    }

    private void printTree() {
        printTree(root);
    }

    private void printTree(Node<K, V> x) {
        if (x != null) {
            System.out.print(x.color == RED ? "RED " : "BLACK ");
            System.out.print(x.key);
            System.out.print(x.left != null ? x.left.key : "x");
            System.out.print(x.right != null ? x.right.key : "x");
            System.out.println();
            printTree(x.left);
            printTree(x.right);
        }
    }

    public V insert(K key, V value) {
        Node<K, V> t = root;
        // root是否为空
        if (t == null) {
            root = new Node<>(key, value, null);
            return null;
        }
        // 查找是否已存在key
        Node<K, V> parent = null;
        while (t != null) {
            parent = t;
            if (key.compareTo(t.key) > 0) {
                t = t.right;
            } else if (key.compareTo(t.key) < 0) {
                t = t.left;
            } else {
                return t.setValue(value);
            }
        }
        // 插入
        Node<K, V> e = new Node<>(key, value, parent);
        if (key.compareTo(parent.key) > 0) {
            parent.right = e;
        } else {
            parent.left = e;
        }
        // 调整
        fixAfterInsertion(e);
        return null;
    }

    private void fixAfterInsertion(Node<K, V> x) {
        // x为红节点
        setColor(x, RED);
        while (x != root && colorOf(parentOf(x)) == RED) {
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                Node<K, V> y = rightOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == rightOf(parentOf(x))) {
                        // parent左旋转
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    setColor(parentOf(parentOf(x)), RED);
                    setColor(parentOf(x), BLACK);
                    rotateRight(parentOf(parentOf(x)));
                }
            } else {
                Node<K, V> y = leftOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                } else {
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    setColor(parentOf(parentOf(x)), RED);
                    setColor(parentOf(x), BLACK);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        setColor(root, BLACK);
    }

    private boolean colorOf(Node<K, V> x) {
        return x == null ? BLACK : x.color;
    }

    private void setColor(Node<K, V> x, boolean color) {
        if (x != null) {
            x.color = color;
        }
    }

    private void rotateLeft(Node<K, V> p) {
        if (p != null) {
            Node<K, V> r = p.right;
            p.right = r.left;
            if (r.left != null) {
                r.left.parent = p;
            }
            r.parent = p.parent;
            if (r.parent != null) {
                if (r.parent.left == p) {
                    r.parent.left = r;
                } else {
                    r.parent.right = r;
                }
            } else {
                root = r;
            }
            r.left = p;
            p.parent = r;
        }
    }

    private void rotateRight(Node<K, V> p) {
        if (p != null) {
            Node<K, V> l = p.left;
            p.left = l.right;
            if (l.right != null) {
                l.right.parent = p;
            }
            l.parent = p.parent;
            if (l.parent != null) {
                if (l.parent.right == p) {
                    l.parent.right = l;
                } else {
                    l.parent.left = l;
                }
            } else {
                root = l;
            }
            l.right = p;
            p.parent = l;
        }
    }

    private Node<K, V> rightOf(Node<K, V> x) {
        return x == null ? null : x.right;
    }

    private Node<K, V> leftOf(Node<K, V> x) {
        return x == null ? null : x.left;
    }

    private Node<K, V> parentOf(Node<K, V> x) {
        return x == null ? null : x.parent;
    }

    class Node<K, V> implements Map.Entry<K, V> {

        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;
        boolean color = BLACK;

        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }
}

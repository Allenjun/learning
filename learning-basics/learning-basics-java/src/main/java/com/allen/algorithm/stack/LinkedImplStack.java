package com.allen.algorithm.stack;

/**
 * @author JUN @Description TODO
 * @createTime 17:44
 */
public class LinkedImplStack<E> implements Stack<E> {

    private Node<E> link;

    public static void main(String[] args) {
        LinkedImplStack<Integer> linkedImplStack = new LinkedImplStack<>();
        linkedImplStack.push(1);
        linkedImplStack.push(2);
        linkedImplStack.push(3);
        linkedImplStack.push(4);
        linkedImplStack.push(5);
        linkedImplStack.push(6);
        System.out.println(linkedImplStack.pop());
        System.out.println(linkedImplStack.pop());
        System.out.println(linkedImplStack.pop());
        System.out.println(linkedImplStack.pop());
        System.out.println(linkedImplStack.pop());
        System.out.println(linkedImplStack.pop());
        System.out.println(linkedImplStack.pop());
    }

    @Override
    public E push(E item) {
        Node<E> node = new Node<>(item);
        if (link == null) {
            link = node;
            return item;
        }
        link.next = node;
        node.prev = link;
        link = node;
        return item;
    }

    @Override
    public E pop() {
        if (empty()) {
            throw new IllegalArgumentException();
        }
        E value = link.value;
        link = link.prev;
        if (link != null) {
            link.next = null;
        }
        return value;
    }

    @Override
    public boolean empty() {
        return link == null;
    }

    class Node<E> {

        E value;
        Node<E> next;
        Node<E> prev;

        public Node(E value) {
            this.value = value;
        }
    }
}

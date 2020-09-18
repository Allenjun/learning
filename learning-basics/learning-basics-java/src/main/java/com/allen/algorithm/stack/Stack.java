package com.allen.algorithm.stack;

/**
 * @author JUN @Description TODO
 * @createTime 17:16
 */
public interface Stack<E> {

    E push(E item);

    E pop();

    boolean empty();
}

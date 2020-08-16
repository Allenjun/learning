package com.allen.algorithm.stack;

/**
 * @author JUN
 * @Description TODO
 * @createTime 17:20
 */
public class ArrayImplStack<E> implements Stack<E> {
    
    final static int DEFAULT_ARRAY_SIZE = 2;
    private E[] elementData;
    private int index;

    public static void main(String[] args) {
        ArrayImplStack<Integer> arrayImplStack = new ArrayImplStack<>();
        arrayImplStack.push(1);
        arrayImplStack.push(2);
        arrayImplStack.push(3);
        arrayImplStack.push(4);
        arrayImplStack.push(5);
        arrayImplStack.push(6);
        arrayImplStack.push(7);
        System.out.println(arrayImplStack.pop());
        System.out.println(arrayImplStack.pop());
        System.out.println(arrayImplStack.pop());
        System.out.println(arrayImplStack.pop());
        System.out.println(arrayImplStack.pop());
        System.out.println(arrayImplStack.pop());
        System.out.println(arrayImplStack.pop());
        
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public E push(E item) {
        if (elementData == null) {
            elementData = (E[]) new Object[DEFAULT_ARRAY_SIZE];
        }
        if (index + 1 > elementData.length) {
            enlarge(elementData.length * 2);
        }
        elementData[index++] = item;
        return item;
    }
    
    @Override
    public E pop() {
        if (empty()) {
            throw new IllegalArgumentException();
        }
        E oldValue = elementData[--index];
        elementData[index] = null;
        return oldValue;
    }
    
    @Override
    public boolean empty() {
        return elementData == null || index == 0;
    }
    
    @SuppressWarnings("unchecked")
    private void enlarge(int i) {
        E[] tmp = (E[]) new Object[i];
        System.arraycopy(elementData, 0, tmp, 0, elementData.length);
        elementData = tmp;
    }
}

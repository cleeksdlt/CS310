package edu.sdsu.cs.datastructures;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by shawn on 2/22/17.
 */
public class ArrayDeque<E> extends AbstractQueue<E> implements IBasicDeque<E> {

    List<E> list;

    public ArrayDeque() {
        list = new java.util.ArrayList<>();
    }

    public ArrayDeque(Collection<E> col) {
        this();
        list.addAll(col);
    }

    public void addFirst(E e) {
        list.add(0, e);
    }

    public void addLast(E e) {
        list.add(e);
    }

    public E pollFirst() {
        return list.remove(0);
    }

    public E pollLast() {
        return list.remove(size() - 1);
    }

    public E peekFirst() {
        return list.get(0);
    }

    public E peekLast() {
        return list.get(size() - 1);
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public int size() {
        return list.size();
    }

    public boolean offer(E e) {
        return list.add(e);
    }

    public E poll() {
        return pollFirst();
    }

    public E peek() {
        return peekFirst();
    }
}

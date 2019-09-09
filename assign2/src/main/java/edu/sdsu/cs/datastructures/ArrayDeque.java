package edu.sdsu.cs.datastructures;

/**
 * assign2
 * Christian De La Torre
 * account: cssc0650
 * San Diego State University
 * CS310 Data Structures, Spring 2017
 * Date: 02/21/17
 * This deque implements IBasicDeque to set ArrayDeque and calls the
 * ArrayList to do different functions to test the complexity
 * of the ArrayList data structure.
 */

import java.util.Collection;
import java.util.Iterator;

public class ArrayDeque<E> extends java.util.AbstractQueue<E> implements IBasicDeque<E> {
    ArrayList<E> arrList = new ArrayList<E>();

    public ArrayDeque() {}

    public ArrayDeque(Collection<E> col) {
        this();
        addAll(col);
    }


    /**
     * Inserts the specified element at the front of the ArrayList if it is possible to do so immediately without
     * violating capacity restrictions.
     *
     * @param e the element to add.
     */
    public void addFirst(E e) {
        arrList.add(0,e);
    }

    /**
     * Inserts the specified element at the end of the ArrayList if it is possible to do so immediately without
     * violating capacity restrictions.
     *
     * @param e the element to add.
     */
    public void addLast(E e) { arrList.add(size(),e); }

    /**
     * Retrieves and removes the first element of the ArrayList, or returns null if the index is empty.
     *
     * @return either null or the removed element that was first in ArrayList
     */
    public E pollFirst() {
        if (arrList.size() == 0) {
            return null;
        } else {
            return arrList.remove(0);
        }
    }

    /**
     * Retrieves and removes the last element of the ArrayList, or returns null if the index is empty.
     * @return either null or the removed element that was last in ArrayList
     */
    public E pollLast() {
        if (arrList.size() == 0) {
            return null;
        } else {
            return arrList.remove(size() - 1);
        }
    }

    /**
     * Retrieves, but does not remove, the first element of the ArrayList, or returns null if this deque is empty.
     *
     * @return either null or first element in ArrayList index
     */
    public E peekFirst() {
        if (arrList.size() == 0) {
            return null;
        } else {
            return arrList.get(0);
        }
    }

    /**
     * Retrieves, but does not remove, the last element of the ArrayList, or returns null if the ArrayList is empty.
     *
     * @return either null or the last element within ArrayList
     */
    public E peekLast() {
        if (arrList.size() == 0) {
            return null;
        } else {
            return arrList.get(size() - 1);
        }
    }

    /**
     * Calls the iterator class within the ArrayList
     *
     * @return the next value
     */
    public Iterator<E> iterator() { return arrList.iterator(); }

    /**
     * Return the size of the ArrayList
     *
     * @return the size of the array
     */
    public int size() {
        return arrList.size();
    }

    /**
     * Inserts the specified element into the ArrayList if it is possible to do so immediately without
     * violating capacity restrictions.
     *
     * @param e element to be inserted within the ArrayList
     * @return last element stored within index
     */
    public boolean offer(E e) {
        if (arrList.size() == 0) {
            return false;
        } else if (arrList.get(size() - 1) == e) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retrieves and removes the end of the ArrayList, or returns null if the
     * element index is empty.
     *
     * @return last element removed that was stored at the end of the ArrayList
     */
    public E poll() {
        if (arrList.size() == 0) {
            return null;
        } else {
            return arrList.remove(size() - 1);
        }
    }

    /**
     * Retrieves, but does not remove, the start of the ArrayList, or returns null if the
     * element index is empty.
     *
     * @return last element stored within first start of ArrayList
     */
    public E peek() {
        if (arrList.size() == 0) {
            return null;
        } else {
            return arrList.get(0);
        }
    }
}
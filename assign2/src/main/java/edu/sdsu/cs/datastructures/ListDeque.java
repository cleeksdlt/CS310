package edu.sdsu.cs.datastructures;

/**
 * assign2
 * Christian De La Torre
 * account: cssc0650
 * San Diego State University
 * CS310 Data Structures, Spring 2017
 * Date: 03/11/17
 * This deque implements IBasicDeque to set ListDeque and calls the
 * SinglyLinkedList to do different functions to test the complexity
 * of the SinglyLinkedList data structure.
 */

import java.util.Collection;
import java.util.Iterator;

public class ListDeque<E> extends java.util.AbstractQueue<E> implements IBasicDeque<E> {
    SinglyLinkedList<E> linkList = new SinglyLinkedList<E>();

    public ListDeque() {}

    public ListDeque(Collection<E> col) {
        this();
        addAll(col);
    }

    /**
     * Inserts the specified element at the front of the SinglyLinkedList if it is possible to do so immediately without
     * violating capacity restrictions.
     *
     * @param e the element to add.
     */
    public void addFirst(E e) {
        linkList.add(0, e);
    }

    /**
     * Inserts the specified element at the end of the SinglyLinkedList if it is possible to do so immediately without
     * violating capacity restrictions.
     *
     * @param e the element to add.
     */
    public void addLast(E e) {
        linkList.add(0, e);
    }

    /**
     * Retrieves and removes the first element of the SinglyLinkedList or returns null if the SinglyLinkedList
     * element index is empty.
     *
     * @return either null or the first element removed in the SinglyLinkedList
     */
    public E pollFirst() {
        if (linkList.size() == 0) {
            return null;
        } else {
            return linkList.remove(0);
        }
    }

    /**
     * Retrieves and removes the last element of the SinglyLinkedList, or returns null if the SinglyLinkedList
     * element index is empty.
     *
     * @return either null or last element removed in the SinglyLinkedList
     */
    public E pollLast() {
        if (linkList.size() == 0) {
            return null;
        } else {
            return linkList.remove(size() - 1);
        }
    }

    /**
     * Retrieves, but does not remove, the first element of the SinglyLinkedList, or returns null if the SinglyLinkedList
     * element index is empty.
     *
     * @return either null or the first element in the SinglyLinkedList
     */
    public E peekFirst() {
        if (linkList.size() == 0) {
            return null;
        } else {
            return linkList.get(0);
        }
    }

    /**
     * Retrieves, but does not remove, the last element of the SinglyLinkedList, or returns null if the SinglyLinkedList
     * element index is empty.
     * @return either null or the last element in the SinglyLinkedList
     */
    public E peekLast() {
        if (linkList.size() == 0) {
            return null;
        } else {
            return linkList.get(size() - 1);
        }
    }

    /**
     * Calls the iterator class within the SinglyLinkedList
     *
     * @return the next node in the SinglyLinkedList
     */
    public Iterator<E> iterator() {
        return linkList.iterator();
    }

    /**
     * Returns the size of the SinglyLinkedList
     *
     * @return size
     */
    public int size() {
        return linkList.size();
    }

    /**
     * Inserts the specified element into the SinglyLinkedList if it is possible to do so immediately without
     * violating capacity restrictions.
     *
     * @param e element to be inserted within the SinglyLinkedList
     * @return true or false if possible to do so
     */
    public boolean offer(E e) {
        if (linkList.size() == 0) {
            return false;
        } else if (linkList.get(size() - 1) == e) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Retrieves and removes the tail of the SinglyLinkedList, or returns null if the
     * element index is empty.
     *
     * @return the last element removed within the SinglyLinkedList
     */
    public E poll() {
        if (linkList.size() == 0) {
            return null;
        } else {
            return linkList.remove(size() - 1);
        }
    }

    /**
     * Retrieves, but does not remove, the head of the SinglyLinkedList, or returns null if the
     * element index is empty.
     *
     * @return the last element within the SinglyLinkedList
     */
    public E peek() {
        if (linkList.size() == 0) {
            return null;
        } else {
            return linkList.get(0);
        }
    }
}

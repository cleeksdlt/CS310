/**
 * Support file for HW2.
 *
 * @version 1.0
 * @author Shawn Healey, San Diego State University
 */
package edu.sdsu.cs.datastructures;

/**
 * Defines the requirements for a basic deque.
 *
 * @param <E> Type of objects stored in the ADT
 */
public interface IBasicDeque<E> extends java.util.Queue<E> {

    /**
     * Inserts the specified element at the front of this deque if it is possible to do so immediately without
     * violating capacity restrictions.
     *
     * @param e the element to add.
     * @throws NullPointerException if the element to add is null
     */
    void addFirst(E e);


    /**
     * Inserts the specified element at the end of this deque if it is possible to do so immediately without
     * violating capacity restrictions.
     *
     * @param e the element to add.
     */
    void addLast(E e);

    /**
     * Retrieves and removes the first element of this deque, or returns null if this deque is empty.
     *
     * @return the head of this deque, or null if this deque is empty
     */
    E pollFirst();

    /**
     * Retrieves and removes the last element of this deque, or returns null if this deque is empty.
     *
     * @return the tail of this deque, or null if this deque is empty
     */
    E pollLast();

    /**
     * Retrieves, but does not remove, the first element of this deque, or returns null if this deque is empty.
     *
     * @return the head of this deque, or null if this deque is empty
     */
    E peekFirst();

    /**
     * Retrieves, but does not remove, the last element of this deque, or returns null if this deque is empty.
     *
     * @return the tail of this deque, or null if this deque is empty
     */
    E peekLast();

}

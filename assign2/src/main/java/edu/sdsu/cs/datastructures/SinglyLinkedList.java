package edu.sdsu.cs.datastructures;

/**
 * assign2
 * Christian De La Torre
 * account: cssc0650
 * San Diego State University
 * CS310 Data Structures, Spring 2017
 * Date: 03/11/17
 * This data structure implements IBasicList to develop a SinglyLinkedList and
 * adds, removes, gets and sets data according to user preference. As
 * well as increments and decrements size according to add or remove method.
 */

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> extends AbstractCollection<E> implements IBasicList<E> {

    private Node<E> head, tail;
    private int size;

    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public SinglyLinkedList(Collection<E> col) {
        this();
        addAll(col);
    }

    /**
     * Node class that acquires the data and next node in line
     *
     * @param <E> the data within the node
     */
    private class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
            next = null;
        }
    }

    /**
     * Iterates through the SinglyLinkedList nodes and implements Iterator
     */
    public class iteratorHelper implements Iterator<E> {
        Node<E> iter;

        public iteratorHelper() {
            iter = head;
        }

        public boolean hasNext() {
            return iter != null;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No Such Element.");
            }
            E value = iter.data;
            iter = iter.next;
            return value;
        }
        public void remove () {}
    }

    /**
     * Calls the iteratorHelper and returns the value of the next node
     *
     * @return value of next node in line
     */
    public Iterator<E> iterator() {
        return new iteratorHelper();
    }

    /**
     * Inserts the specified element at the specified position in the SinglyLinkedList. Shifts the element
     * currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     * @throws NullPointerException if the element is a null
     */
    public void add(int index, E element) {
        Node<E> newNode = new Node<E>(element);
        try {
            if(checkIndex(index) == -1) {
                throw new IndexOutOfBoundsException("Invalid Index: Out Of Bounds.");
            }
            if (checkElement(element) == -1) {
                throw new NullPointerException("Invalid Element: Null Pointer");
            }
            if (head == null) {
                head = tail = newNode;
                size++;
                return;
            }
            if (index == size) {
                tail.next = newNode;
                tail = newNode;
                size++;
                return;
            }
            Node<E> curNode = head;
            if(index == 0){
                newNode.next = head;
                head = newNode;
                size++;
                return;
            }
            for (int curIndex = 0; curIndex < index-1; curIndex++) {
                curNode = curNode.next;
            }
            newNode.next = curNode.next;
            curNode.next = newNode;
            size++;

        } catch (IndexOutOfBoundsException error) {
            error.printStackTrace();
        } catch (NullPointerException error) {
            error.printStackTrace();
        }
    }

    /**
     * Returns the element at the specified position in the SinglyLinkedList.
     *
     * @param index index of the element to return
     * @return the element at the specified position in the SinglyLinkedList
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public E get(int index) {
        try {
            if (checkIndex(index) == -1) {
                throw new IndexOutOfBoundsException("Invalid Index: Out Of Bounds.");
            }
            Node curNode = head;
            for (int curIndex = 0; curIndex < index; curIndex++) {
                curNode = curNode.next;
            }
            return (E)curNode.data;
        } catch (IndexOutOfBoundsException error) {
            error.printStackTrace();
        }
        return null;
    }

    /**
     * Removes the element at the specified position in the SinglyLinkedList. Shifts any subsequent
     * elements to the left (subtracts one from their indices). Returns the element that was removed from the list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public E remove(int index) {
        try {
            if (checkIndex(index) == -1) {
                throw new IndexOutOfBoundsException("Invalid Index: Out Of Bounds.");
            }
            Node<E> curNode = head;
            Node<E> prevNode = null;
            if (index == 0) {
                head = head.next;
                size--;
                return curNode.data;
            }
            for (int curIndex = 0; curIndex < index; curIndex++) {
                prevNode = curNode;
                curNode = curNode.next;
            }
            prevNode.next = curNode.next;
            size--;
            return curNode.data;
        } catch (IndexOutOfBoundsException error) {
            error.printStackTrace();
        }
        return null;
    }

    /**
     * Replaces the element at the specified position in the SinglyLinkedList with the specified element.
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public E set(int index, E element) {
        try {
            if (checkIndex(index) == -1) {
                throw new IndexOutOfBoundsException("Invalid Index: Out Of Bounds.");
            }
            if (checkElement(element) == -1) {
                throw new NullPointerException("Invalid Element: Null Pointer.");
            }
            Node<E> curNode = head;
            Node<E> temp;
            for (int curIndex = 0; curIndex < index; curIndex++) {
                curNode = curNode.next;
            }
            temp = curNode;
            curNode.data = element;
            return (E)temp;
        } catch (IndexOutOfBoundsException error) {
            error.printStackTrace();
        } catch (NullPointerException error) {
            error.printStackTrace();
        }
        return null;
    }

    /**
     * Returns the size of the SinglyLinkedList
     *
     * @return size of list
     */
    public int size() {
        return size;
    }

    /**
     * Checks if index is valid and within certain values
     *
     * @param index index to be accessed within the SinglyLinkedList
     * @return negative or zero
     */
    private int checkIndex(int index) {
        if (index < 0) {
            return -1;
        }
        return 0;
    }

    /**
     * Checks if element is a null or have data to be stored within SinglyLinkedList
     *
     * @param element element to be checked for null or value
     * @return negative or zero
     */
    private int checkElement(Object element) {
        if (element == null) {
            return -1;
        }
       return 0;
    }
}

package edu.sdsu.cs.datastructures;

/**
 * assign1
 * Christian De La Torre
 * account: cssc0650
 * San Diego State Univeristy
 * CS310 Data Structures, Spring 2017
 * Date: 02/21/17
 * This data structure sets an ArrayList to a default capacity and then
 * resizes according to the maximum capacity.
 */

import java.util.AbstractList;
import java.util.Collection;

public class ArrayList<E> extends AbstractList<E> implements IBasicList<E> {

    private static final int defaultCapacity = 16;
    private int curCapacity;
    private E[] storage;

    /**
     * Sets the default capacity of the ArrayList
     */
    public ArrayList() {
        this(defaultCapacity);
    }

    private ArrayList (int initCapacity) {
        curCapacity = 0;
        storage = (E[])(new Object[initCapacity]);
    }

    public ArrayList(Collection<E> col) {
        this(col.size());
        this.addAll(col);
    }

    /**
     * Returns the size of the ArrayList
     *
     * @return size of ArrayList
     */
    public int size() {
        return curCapacity;
    }

    /**
     * Gets the index of an item within the ArrayList and checks to make
     * sure element is not out of bounds
     *
     * @param index - item within ArrayList
     * @return
     */
    public E get(int index) {
        try {
            if (index < 0) {
                throw new IllegalArgumentException("Illegal Argument Exception.");
            }
            if (index > curCapacity) {
                throw new IndexOutOfBoundsException("Invalid array: Out of bounds.");
            }
            if (index >= 0 && index <= storage.length) {
                for (int i = 0; i < curCapacity; i++) {
                    if (index == i) {
                        return storage[index];
                    }
                }
            }
        }
        catch (IllegalArgumentException error) {
            error.printStackTrace();
        }
        catch (IndexOutOfBoundsException error) {
            error.printStackTrace();
        }
        return null;
    }

    /**
     * Sets the element in the index at a certain location and checks to see if
     * element is out of bounds
     *
     * @param index - where element in ArrayList location is located
     * @param data - what the index will hold
     * @return
     */
    public E set(int index, E data) {
        if(data != null) {
            if (index < 0 || index >= size()) {
                throw new IndexOutOfBoundsException("Invalid array: Out of bounds.");
            } else {
                storage[index] = data;
            }
        }
        return null;
    }

    /**
     * Adds an element a certain index within the ArrayList
     *
     * @param data
     * @return
     */
    public boolean add(E data) {
        if (data != null) {
            if (curCapacity == storage.length) {
                resizeArray(this.storage.length * 2);
            }
            storage[curCapacity++] = data;
            return true;
        }
        return false;
    }

    /**
     * Adds an element at a certain index within the ArrayList
     *
     * @param index
     * @param data
     */
    public void add(int index, E data) {
        if (data != null) {
            if (index < 0 || index > size()) {
                throw new IndexOutOfBoundsException("Invalid array: Out of bounds.");
            }
            if (curCapacity == storage.length) {
                resizeArray(storage.length * 2);
            }
            for (int i = curCapacity; i > index; i--) {
                storage[i] = storage[i - 1];
            }
            curCapacity++;
            storage[index] = data;
        }
    }

    /**
     * Removes the element at a certain index within the ArrayList
     *
     * @param index
     * @return
     */
    public E remove (int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid array: Out of bounds.");
        }
        E temp = storage[index];

        for (int i = index + 1; i < curCapacity; i++) {
            storage[i - 1] = storage[i];
        }
        curCapacity--;
        return temp;
    }

    /**
     * @param newCapacity - size of new ArrayList to be resized to
     */
    private void resizeArray(int newCapacity) {
        E[] tempArray = (E[]) new Object[newCapacity];
        try {
            System.arraycopy(storage, 0, tempArray, 0, curCapacity);
        }
        catch (OutOfMemoryError error){
            error.printStackTrace();
        }
        storage = tempArray;
    }
}

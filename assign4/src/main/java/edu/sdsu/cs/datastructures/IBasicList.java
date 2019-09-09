/**
 * Support file for HW2.
 *
 * @version 1.0
 * @author Shawn Healey, San Diego State University
 */
package edu.sdsu.cs.datastructures;

/**
 * A basic list extending the java Collection interface. Unlike the java.util.List interface, objects of this type do
 * not include the ListIterator. This makes for a slightly easier implementation, but objects implementing this
 * interface *cannot* be used in place of a java.util.List.
 *
 * @author Shawn Healey
 * @version 1.0
 */
public interface IBasicList<E> extends java.util.Collection<E> {

    /**
     * Inserts the specified element at the specified position in this list. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right (adds one to their indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     */
    void add(int index, E element);

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    E get(int index);

    /**
     * Removes the element at the specified position in this list. Shifts any subsequent elements to the left
     * (subtracts one from their indices). Returns the element that was removed from the list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    E remove(int index);

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    E set(int index, E element);
}

package edu.sdsu.cs.datastructures;

/**
 * Title: assign3
 * Author: Christian De La Torre
 * Class: CS310 Data Structures, Spring 2017
 * San Diego State University
 * Class Account: cssc0650
 * Date: 04/09/17
 * Purpose: This map shares functionality from both the
 * HashTable and the BinarySearchTree. The map also
 * denies null keys and only allows null values.
 **/


import java.util.Map;
import java.util.Set;

public abstract class BasicAbstractMap<K,V> implements java.util.Map<K,V> {

    protected int curSize;

    /**
     * Returns the number of key-value mappings in HashTable or BasicSearchTree
     * @return size
     */
    public int size() { return curSize; }

    /**
     * Checks to see if the HashTable or BasicSearchTree is empty
     * @return true if empty or false is not
     */
    public boolean isEmpty() {
        if (curSize == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Copies all of the mappings from the specified map to this map
     * @param m - mappings to be stored in this map
     */
    public void putAll(Map m) {
        Set<Entry<K,V>> setDaShit = m.entrySet(); // Fix variable name
        for (Entry<K,V> node : setDaShit) {
            put(node.getKey(), node.getValue());
        }
    }

    public static class MapEntry<K extends Comparable<K>, V> implements Map.Entry<K,V>, Comparable<Entry<K,V>> {
        private K key;
        private V value;

        /**
         * Sets the key and value
         * @param key
         * @param value
         */
        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Gets the key from the HashTable or BinarySearchTree
         * @return key mapping
         */
        public K getKey() {
            return key;
        }

        /**
         * Gets the value from the key mapping within the HashTable
         * or BinarySearchTree
         * @return value within key mapping
         */
        public V getValue() {
            return value;
        }

        /**
         * Sets the value at which the key is mapped to
         * @param value - value within key
         * @return the previous value that was stored
         */
        public V setValue(V value) {
            V temp = this.value;
            this.value = value;
            return temp;
        }

        /**
         * Compares two objects
         * @param obj - object to be compared to
         * @return either -1, 0, or 1 if less than, equal, or greater than
         */
        @Override
        public int compareTo(Entry<K, V> obj) {
            return this.key.compareTo(obj.getKey());
        }
    }
}
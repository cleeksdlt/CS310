package edu.sdsu.cs.datastructures;

/**
 * Title: assign3
 * Author: Christian De La Torre
 * Class: CS310 Data Structures, Spring 2017
 * San Diego State University
 * Class Account: cssc0650
 * Date: 04/09/17
 * Purpose: This dara structure is a list of lists, in which the hash table
 * with chaining apply a hash function to each key stored therein when determining
 * within which bin to place or search for the entry. Also resizes when
 * the load factor reaches maximum capacity or minimum capacity.
 **/

import java.util.*;

public class HashTable<K extends Comparable<K>,V> extends BasicAbstractMap<K,V> {

    protected int capacity;
    protected double minLoadFactor;
    protected double maxLoadFactor;

    private List<Entry<K,V>>[] buckets;

    /**
     * Initializes the values to be used by the HashTable and creates
     * an ArrayList of buckets to store the keys and values
     **/
    public HashTable() {
        curSize = 0;
        capacity = 11;
        minLoadFactor = 0.15;
        maxLoadFactor = 0.75;
        buckets = new List[capacity];
        for (int curBin = 0; curBin < capacity; curBin++) {
            buckets[curBin] = new ArrayList<>();
        }
    }

    /**
     * Copies all of the mappings from the specified map to this map
     * @param map - mappings to be stored in this map
     **/
    public HashTable(Map<? extends K,? extends V> map) {
        this();
        if (map == null) {
            throw new NullPointerException("Invalid map extending");
        }
        putAll(map);
    }

    /**
     * Returns true if the HashTable contains a mapping for the specified key
     * @param key - key whose presence in the map is to be tested
     * @return true if map contains a mapping for the specified key
     **/
    public boolean containsKey(Object key) {
        if (key == null) {
            throw new NullPointerException("Invalid Key: Key is null");
        }
        return get(key) != null;
    }

    /**
     * Returns true if the map, maps one or more keys to the specified value
     * @param value - value whose presence in this map is to be tested
     * @return true if the map, maps one or more keys to the specified value
     **/
    public boolean containsValue(Object value) {
        if (value == null) {
            throw new NullPointerException("Invalid Value: Value is null");
        }
        for (List<Entry<K, V>> index : buckets) {
            for (Entry<K, V> node : index) {
                if (node.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null
     * if this map contains no mapping for the key
     * @param key - the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if the map
     * contains no mapping for the key
     **/
    public V get(Object key) {
        if (key == null) {
            throw new NullPointerException("Invalid Key: Key is null");
        }
        BasicAbstractMap.MapEntry<K,V> node = new BasicAbstractMap.MapEntry<>((K) key, null);
        int mapIndex = key.hashCode() & 0x7FFFFFF % capacity;
        V value = null;

        for (BasicAbstractMap.Entry<K,V> i: buckets[mapIndex]) {
            if (i.getKey().equals(node.getKey())) {
                value = i.getValue();
            }
        }
        return value;
    }

    /**
     * Associates the specified value with the specified key in this map
     * @param key - key with which the specified value is to be associated with
     * @param value - value to be associated with the specified key
     * @return the previous value associated with key, or null if there was no mapping for key
     **/
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException("Invalid Key or Value: Either Key or Value is null");
        }

        if (loadFactor() >= maxLoadFactor) {
            capacity = nearestPrimeAfter(capacity *= 2);
            resize(capacity);
        }

        V prevVal = null;
        BasicAbstractMap.MapEntry<K,V> node = new BasicAbstractMap.MapEntry<>((K) key, (V) value);
        int mapIndex = key.hashCode() & 0x7FFFFFF % capacity;
        for (BasicAbstractMap.Entry<K,V> index: buckets[mapIndex]) {
            if (index.getKey().equals(node.getKey())) {
                buckets[mapIndex].add(node);
                return index.getValue();
            }
        }
        buckets[mapIndex].add(node);
        curSize++;
        return prevVal;
    }

    /**
     * Removes the mapping for a key from this map if it is present
     * @param key - key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if
     * there was no mapping for key
     **/
    public V remove(Object key) {
        if (key == null) {
            throw new NullPointerException("Invalid Key: Key is null");
        }
        int mapIndex = key.hashCode() & 0x7FFFFFF % capacity;
        for (BasicAbstractMap.Entry<K,V> index : buckets[mapIndex]) {
            if (index.getKey().equals(key)) {
                buckets[mapIndex].remove(index);
                curSize--;
                return index.getValue();
            }
        }
        if (loadFactor() <= minLoadFactor) {
            capacity = nearestPrimeAfter(capacity * 2);
            resize(capacity);
        }
        return null;
    }

    /**
     * Removes all of the mappings from the map
     **/
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            buckets[i].clear();
        }
        curSize = 0;
    }

    /**
     * Sets the key hashcode and returns the current key
     * @return the key
     **/
    public Set<K> keySet() {
        TreeSet<K> key = new TreeSet<K>();
        for (Entry<K,V> node: entrySet()) {
            key.add(node.getKey());
        }
        return key;
    }

    /**
     * Collects all the values and stores them within an array
     * @return the array of values
     **/
    public Collection<V> values() {
        ArrayList<V> array = new ArrayList<V>();
        for (Entry<K,V> node: entrySet()) {
            array.add(node.getValue());
        }
        return array;
    }

    /**
     * Sets both the key and value within an array of node
     * @return key and value of specified key
     **/
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K,V>> setEntry = new TreeSet<>();
        for (List<Entry<K, V>> index : buckets) {
            for (Entry<K, V> node : index) {
                setEntry.add(node);
            }
        }
        return setEntry;
    }

    /**
     * Checks to see whether the ArrayList has hit maximum capacity or minimum capacity
     * @return a double load factor to determine if resize is necessary
     **/
    private double loadFactor() {
        return (double) curSize/capacity;
    }

    /**
     * Checks to find the nearest possible prime number after using the
     * initial target value
     * @param target - value needed to increase HashTable array size
     * @return the specified new resize capacity value
     **/
    private int nearestPrimeAfter(int target) {
        int num = target+1;
        while (!isPrime(target)) {
            target++;
        }
        return num;
    }

    /**
     * Checks to see if the target is the prime value to use
     * @param target - value needed to increase HashTable array size
     * @return whether true if target is prime, else returns false
     **/
    private boolean isPrime(int target) {
        if (target <= 2) {
            return true;
        }
        for (int i = 2; i*i <= target; i++) {
            if (target % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Resizes the ArrayList within the HashTable once the load factor has either
     * surpassed the maximum or minimum load factor.
     * @param tempCapacity the original ArrayList capacity
     **/
    private void resize (int tempCapacity) {
        int newCapacity = tempCapacity;
        int hashIndex;
        List<Entry<K,V>>[] tempTable = new List[newCapacity];
        for (int tempBin = 0; tempBin < capacity; tempBin++) {
            tempTable[tempBin] = new ArrayList<>();
        }
        for (List<Entry<K, V>> index : buckets) {
            for (Entry<K, V> node : index) {
                hashIndex = node.getKey().hashCode() & 0x7FFFFFF % capacity;
                tempTable[hashIndex].add(node);
            }
        }
        buckets = tempTable;
    }
}

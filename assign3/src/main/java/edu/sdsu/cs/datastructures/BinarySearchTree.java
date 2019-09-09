package edu.sdsu.cs.datastructures;

/**
 * Title: assign3
 * Author: Christian De La Torre
 * Class: CS310 Data Structures, Spring 2017
 * San Diego State University
 * Class Account: cssc0650
 * Date: 04/09/17
 * References: Supplementary Material, Alan Riggins, Montezuma Publishing, Online Version.
 *             https://zybooks.zyante.com/#/zybook/SDSUCS310HealeySpring2017/chapter/4/section/5
 * Purpose: This data structure goes through a tree of inorder keys and
 * adds according to whether the key is greater than, less than, or equal to
 * the value of the current node the tree pointer is at.
 **/

import java.util.*;

public class BinarySearchTree<K extends Comparable<K>,V> extends BasicAbstractMap<K,V> {

    /**
     * Creates a class of Node that will store the key, value, leftChild, rightChild and root
     * @param <K> - key node
     * @param <V> - value node
     */
    private class Node<K extends Comparable<K>,V> extends BasicAbstractMap.MapEntry<K,V> {
        public Node<K,V> leftChild;
        public Node<K,V> rightChild;

        public Node (K key, V value) {
            super(key, value);
            leftChild = rightChild = null;
        }
    }
    private Node<K,V> root;

    /**
     * Basic constructor that sets the value to root and size of the Binary Search Tree
     **/
    public BinarySearchTree() {
        curSize = 0;
        root = null;
    }

    /**
     * Copies all of the mappings from the specified map to this map
     * @param map - mappings to be stored in this map
     **/
    public BinarySearchTree(Map<? extends K,? extends V> map) {
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
       return containsVal(root, value);
    }

    /**
     * Recursive helper method for containsValue that searches the Binary Search Tree
     * and returns true or false if specified key mapping has a value
     * @param node - stores the current node
     * @param value - value to find within the Binary Search Tree
     * @return true or false if value can be found
     **/
    private boolean containsVal(Node<K, V> node, Object value) {
        if(node == null) {
            return false;
        }
        if(node.getValue().equals(value)) {
            return true;
        }
        if(containsVal(node.leftChild,value)) {
            return true;
        }
        return containsVal(node.rightChild,value);
    }

    /**
     * Returns the value to which the specified key is mapped, or null
     * if this map contains no mapping for the key
     * @param key - the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if the map
     * contains no mapping for the key
     * @references Supplementary Material, Alan Riggins, Montezuma Publishing, Online Version.
     **/
    public V get(Object key) {
        if (key == null) {
            throw new NullPointerException("Invalid Key: Key is null");
        }
        if (isEmpty()) {
            return null;
        }
        if (((root.getKey())).compareTo((K)key) == 0) {
            return root.getValue();
        }
        return getValues(root, (K)key);
    }

    /**
     * Recursive helper method for get that helps iterate through the Binary Search Tree
     * and gets the value that needs to be found
     * @param node - current node
     * @param key - the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if the map
     * contains no mapping for the key
     * @references Supplementary Material, Alan Riggins, Montezuma Publishing, Online Version.
     **/
    private V getValues(Node<K,V> node, K key) {
        if (node == null) {
            return null;
        }
        if (((key)).compareTo((K) node.getKey()) == 0) {
            return node.getValue();
        }
        if (((key)).compareTo((K) node.getKey()) < 0) {
            return getValues(node.leftChild, key);
        }
        return getValues(node.rightChild, key);
    }

    /**
     * Associates the specified value with the specified key in this map
     * @param key - key with which the specified value is to be associated with
     * @param value - value to be associated with the specified key
     * @return the previous value associated with key, or null if there was no mapping for key
     * @references Supplementary Material, Alan Riggins, Montezuma Publishing, Online Version.
     **/
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException("Invalid Key/Value: Key/Value is null");
        }
        if (root == null) {
            root = new Node(key, value);
            curSize++;
            return null;
        }
        return put(key, value, root, null, false);
    }

    /**
     * Helper recursive method for put that iterates through the Binary Search Tree to add onto
     * each tree leaf depending on whether it is greater than or less than the parent key
     * @param k - key with which the specified value is to be associated with
     * @param v - value to be associated with the specified key
     * @param node - root
     * @param parent - null
     * @param wasLeft - boolean value to go between left and right
     * @return the previous value associated with key, or null if there was no mapping for key
     * @references Supplementary Material, Alan Riggins, Montezuma Publishing, Online Version.
     **/
    private V put(K k, V v, Node<K,V> node, Node<K,V> parent, boolean wasLeft) {
        if (node == null) {
            if (wasLeft) {
                parent.leftChild = new Node<K,V>(k, v);
                curSize++;
                return null;
            } else {
                parent.rightChild = new Node<K,V>(k,v);
                curSize++;
                return null;
            }
        } else if((k).compareTo((K)node.getKey()) < 0) {
            put(k, v, node.leftChild, node, true); //go left
        } else if(k.compareTo((K)node.getKey()) > 0) {
            put(k, v, node.rightChild, node, false); //go right
        } else {
            return node.setValue(v);
        }
        return null;
    }

    /**
     * Removes the mapping for the key from this map if it is present
     * @param key - key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if
     * there was no mapping for key
     * @references https://zybooks.zyante.com/#/zybook/SDSUCS310HealeySpring2017/chapter/4/section/5
     **/
    public V remove(Object key) {
        if (key == null) {
            throw new NullPointerException("Invalid Key: Key is null");
        }
        return remove((K)key, root, null);
    }

    /**
     * Recursive helper method for the remove method to traverse the Binary Search
     * Tree and remove the mapping for the key from this map if it is present
     * @param k - key whose mapping is to be removed from the map
     * @param current - starts at root and traverses Binary Search Tree and each new leaf
     *                becomes current node
     * @param parent - parent of children
     * @return the previous value associated with key, or null if
     * there was no mapping for key
     * @references https://zybooks.zyante.com/#/zybook/SDSUCS310HealeySpring2017/chapter/4/section/5
     **/
    private V remove (K k, Node<K,V> current, Node<K,V> parent) {
        V val = null;
        if ((k).compareTo((K) current.getKey()) < 0) {
            return remove(k, current.leftChild, current);
        } else if (k.compareTo((K) current.getKey()) > 0) {
            return remove(k, current.rightChild, current);
        } else {
            val = current.getValue();
            if (current.leftChild == null && current.rightChild == null) {
                if (parent == null) {
                    root = null;
                } else if (parent.leftChild == current) {
                    parent.leftChild = null;
                } else {
                    parent.rightChild = null;
                }
            } else if (current.leftChild != null && current.rightChild == null) {
                if (parent == null) {
                    current = current.leftChild;
                    root = current;
                } else if (parent.leftChild == current) {
                    parent.leftChild = current.leftChild;
                } else {
                    parent.rightChild = current.leftChild;
                }
            } else if (current.leftChild == null && current.rightChild != null) {
                if (parent == null) {
                    current = current.rightChild;
                    root = current;
                } else if (parent.leftChild == current) {
                    parent.leftChild = current.rightChild;
                } else {
                    parent.rightChild = current.rightChild;
                }
            } else {
                Node<K, V> successor = inOrderSuc(current);
                current = successor;
                return remove(k, current, parent);
            }
            curSize--;
        }
        return val;
    }

    /**
     * Recursive helper method that searches for the
     * successor of the left most child.
     * @param node - start of the root tree
     * @return the successor of the leftChild
     **/
    private Node<K,V> inOrderSuc(Node<K, V> node) {
        Node<K,V> successor = node.rightChild;
        if (successor.leftChild == null) {
            return node;
        }
        return inOrderSuc(successor.leftChild);
    }

    /**
     * Removes all of the mappings from the map
     **/
    public void clear() {
        root = null;
        curSize = 0;
    }

    /**
     * Sets the key hashcode and returns the current key
     * @return the key
     **/
    public Set<K> keySet() {
        TreeSet<K> treeSet = new TreeSet<>();
        for (Entry<K,V> node : entrySet()) {
            treeSet.add(node.getKey());
        }
        return treeSet;
    }

    /**
     * Collects all the values and stores them within an array
     * @return the array of values
     **/
    public Collection<V> values() {
        ArrayList<Object> array = new ArrayList<>();
        for (Entry<K, V> node : entrySet()) {
            array.add(node.getValue());
        }
        return (Collection<V>) array;
    }

    /**
     * Sets both the key and value within an array called setEntry
     * @return key and value of specified key
     **/
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> setEntry = new TreeSet<>();
         inOrder(root, setEntry);
         return setEntry;
    }

    /**
     * Puts the data into order for easier access
     * @param node - the root of the tree
     * @param tree - the Set
     **/
    private void inOrder(Node<K,V> node, Set<Entry<K,V>> tree) {
        Set<Entry<K,V>> set = tree;
        if (node == null) {
            return;
        }
        inOrder(node.leftChild, set);
        set.add(node);
        inOrder(node.rightChild, set);
    }
}

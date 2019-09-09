/**
 * File: BinarySearchTreeTest.java
 * Author: Shawn Healey
 * Version: 1.0
 *
 * CS 310 - Data Structures
 * San Diego State University
 * Spring 2017
 */
package edu.sdsu.cs.datastructures;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

import static edu.sdsu.cs.datastructures.TestValues.RANDOM_SEED;
import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test suite to aid in the development of a compliant binary search tree. This package assumes a basic, unbalanced
 * binary tree.
 */
public class BinarySearchTreeTest {

    /**
     * Establishes, for some tests, an upper bound to use when building maps.
     */
    private static final int TEST_SIZE = 1024;

    /**
     * The system under test. Tests may incorporate new maps as necessary, but this is the default one available to all.
     */
    java.util.Map<String, Integer> sut;

    /**
     * Good keys in an unbalanced tree must, themselves, be somewhat balanced. An in-order series would create a
     * linked list. We want to test a tree, so our data must arrive in apparently random order.
     */
    static String[] goodKeys = TestValues.getRandomKeys(TEST_SIZE,RANDOM_SEED);
    static Integer[] goodValues = TestValues.getRandomSquareValues(TEST_SIZE, RANDOM_SEED);

    /**
     * This method runs before each and every test, so every one of the tests identified in this file run with a
     * clean data structure every time. This helps cut back on the number of places one needs to call the
     * constructor, so changing the data structure for debug and test is easier.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        sut = new BinarySearchTree<String, Integer>();
    }

    @Test
    public void defaultConstructor_builtCorrectly() throws Exception {

        assertThat(sut.size(), is(equalTo(0)));

        for (Map.Entry<String, Integer> entry : sut.entrySet()) {
            fail("Default constructed sut contains Entry: " + entry);
        }

        for (String key : sut.keySet()) {
            fail("Default constructed sut contains key: " + key);
        }

        for (Integer value : sut.values()) {
            fail("Default constructed sut contains key: " + value);
        }
    }

    @Test
    public void put_newKeys_returnsNull() throws Exception {
        for (String key : goodKeys) {
            assertThat(sut.put(key, TestValues.VALUE_IGNORED), is(equalTo(null)));
        }
    }

    @Test
    public void put_duplicateKeys_sizeCorrect() throws Exception {

        // insert the initial set of keys
        for (String key : goodKeys) {
            sut.put(key, TestValues.VALUE_IGNORED);
        }
        assertThat(sut.size(), is(equalTo(goodKeys.length)));

        // insert the keys again with the same set of values
        for (String key : goodKeys) {
            sut.put(key, TestValues.VALUE_IGNORED);
        }
        assertThat(sut.size(), is(equalTo(goodKeys.length)));

        // again with a different set of values
        for (String key : goodKeys) {
            sut.put(key, TestValues.VALUE_IGNORED + 1);
        }
        assertThat(sut.size(), is(equalTo(goodKeys.length)));
    }

    @Test(expected = NullPointerException.class)
    public void put_nullKey_nullPointerExceptionThrown() throws Exception {
        sut.put(null, TestValues.VALUE_IGNORED);
        fail("Map shall not allow null KEYS");
    }

    @Test
    public void put_newKey_sizeIncreases() throws Exception {
        sut.put(TestValues.KEY_IGNORED, TestValues.VALUE_IGNORED);
        assertThat(sut.size(), is(equalTo(1)));
    }

    @Test
    public void remove_singleEntry_correctSizeAndContents() throws Exception {
        sut.put(goodKeys[0], goodValues[0]);
        assertThat(sut.remove(goodKeys[0]), is(equalTo(goodValues[0])));
        assertThat(sut.size(), is(equalTo(0)));
    }

    @Test
    public void remove_rootWithOneChild_correctSizeAndContents() throws Exception {
        sut.put(goodKeys[0], goodValues[0]);
        sut.put(goodKeys[1], goodValues[1]);

        assertThat(sut.remove(goodKeys[0]), is(equalTo(goodValues[0])));
        assertThat(sut.size(), is(equalTo(1)));
        assertThat(sut.get(goodKeys[0]), is(equalTo(null)));
        assertThat(sut.get(goodKeys[1]), is(equalTo(goodValues[1])));
    }

    @Test
    public void remove_rootWithTwoChildren_correctSizeAndContents() throws Exception{

        sut.put("5", 5);
        sut.put("1", TestValues.VALUE_IGNORED);
        sut.put("10", TestValues.VALUE_IGNORED);

        assertThat(sut.remove("5"), is(equalTo(5)));
        assertThat(sut.containsKey("5"), is(equalTo(false)));
        assertThat(sut.containsKey("1"), is(equalTo(true)));
        assertThat(sut.containsKey("10"), is(equalTo(true)));
        assertThat(sut.size(), is(equalTo(2)));
    }

    @Test
    public void remove_nonRootWithTwoChildren_correctSizeAndContents() throws Exception{

        sut.put("10", TestValues.VALUE_IGNORED);
        sut.put("100", TestValues.VALUE_IGNORED);
        sut.put("1000", 1000);
        sut.put("500", TestValues.VALUE_IGNORED);
        sut.put("1500",TestValues.VALUE_IGNORED);

        assertThat(sut.remove("1000"),is(equalTo(1000)));
        assertThat(sut.containsKey("1000"), is(equalTo(false)));
        assertThat(sut.containsKey("10"), is(equalTo(true)));
        assertThat(sut.containsKey("100"), is(equalTo(true)));
        assertThat(sut.containsKey("500"), is(equalTo(true)));
        assertThat(sut.containsKey("1500"), is(equalTo(true)));
        assertThat(sut.size(), is(equalTo(4)));
    }

    @Test
    public void remove_onlyChildOfRoot_correctSizeAndContents() throws Exception {
        sut.put(goodKeys[0], goodValues[0]);
        sut.put(goodKeys[1], goodValues[1]);

        // test the first side
        assertThat(sut.remove(goodKeys[1]), is(equalTo(goodValues[1])));
        assertThat(sut.size(), is(equalTo(1)));
        assertThat(sut.get(goodKeys[0]), is(equalTo(goodValues[0])));
        assertThat(sut.get(goodKeys[1]), is(equalTo(null)));

        sut.clear();
        sut.put(goodKeys[1], goodValues[1]);
        sut.put(goodKeys[0], goodValues[0]);

        // test the other side
        assertThat(sut.remove(goodKeys[0]), is(equalTo(goodValues[0])));
        assertThat(sut.size(), is(equalTo(1)));
        assertThat(sut.get(goodKeys[1]), is(equalTo(goodValues[1])));
        assertThat(sut.get(goodKeys[0]), is(equalTo(null)));
    }

    @Test
    public void remove_middleOfChain_correctSizeAndContents(){

        // in-order insertion in non-balanced BST results in single child chain
        String[] sequence = new String[TEST_SIZE];
        IntStream.range(0, TEST_SIZE).forEach(val -> sequence[val] = String.valueOf(val));
        for( int index = 0; index < sequence.length; index++){
            sut.put(sequence[index],TestValues.VALUE_IGNORED);
        }

        // every node in the chain must have only one child, so the middle
        int indexToDelete = sequence.length >> 1;
        assertThat(sut.remove(sequence[indexToDelete]), not(equalTo(null)));
        assertThat(sut.containsKey(sequence[indexToDelete]), is(equalTo(false)));
        assertThat(sut.size(), is(equalTo(sequence.length-1)));
    }

    @Test
    public void put_existingKey_returnsPreviousValue() throws Exception {
        sut.put(goodKeys[0], goodValues[0]);
        assertThat(sut.put(goodKeys[0], TestValues.VALUE_IGNORED), is(equalTo(goodValues[0])));
    }

    @Test
    public void get_missingKey_nullReturned() throws Exception {
        assertThat(sut.get(TestValues.KEY_IGNORED), is(equalTo(null)));
    }

    @Test
    public void clear_nothingPresent() throws Exception {
        assert (goodKeys.length > 0);
        for (int index = 0; index < goodKeys.length; index++) {
            sut.put(goodKeys[index], goodValues[index]);
        }

        sut.clear();
        for (String key : sut.keySet()) {
            fail("Key present: " + key);
        }
        for (Integer value : sut.values()) {
            fail("Value present: " + value);
        }
        for (Map.Entry<String, Integer> entry : sut.entrySet()) {
            fail("Entry present: " + entry);
        }

        assertThat(sut.size(), is(equalTo(0)));
    }

    @Test
    public void containsKey_evenKeysInMap_containsEvensNoOdds() throws Exception {

        // insert even keys
        for (int index = 0; index < goodKeys.length; index += 2) {
            sut.put(goodKeys[index], TestValues.VALUE_IGNORED);
        }

        for (int index = 0; index < goodKeys.length; index += 2) {
            // evens present
            assertThat(sut.containsKey(goodKeys[0]), is(equalTo(true)));
            // odds not
            assertThat(sut.containsKey(goodKeys[index + 1]), is(equalTo(false)));
        }
    }

    @Test
    public void containsValue_duplicateEvenValuesInMap_evenPresentOddNot() throws Exception {

        // smaller size for this (div by 4), slow test (value checking is SLOW)
        int testSize = goodKeys.length >> 2;

        // insert even AND odd keys with the same value.
        for (int index = 0; index < testSize; index += 2) {
            sut.put(goodKeys[index], goodValues[index]);
            sut.put(goodKeys[index + 1], goodValues[index]);
        }

        for (int index = 0; index < testSize; index += 2) {
            // even value present
            assertThat(sut.containsValue(goodValues[index]), is(equalTo(true)));
            // odd value not
            assertThat(sut.containsValue(goodValues[index+1]), is(equalTo(false)));
        }

    }

    @Test
    public void mapConstructor_constructedCorrectly(){

        Map<String,Integer> fromMap = new java.util.TreeMap<String, Integer>();
        for( int index = 0; index < goodKeys.length; index++){
            fromMap.put(goodKeys[index], goodValues[index]);
        }

        sut = new BinarySearchTree<String, Integer>(fromMap);

        assertThat(sut.size(), is(equalTo(fromMap.size())));

        // verify all keys present
        for( int index = 0; index < goodKeys.length; index++){
            assertThat(sut.containsKey(goodKeys[index]),is(equalTo(true)));
        }

        // verify all values present
        for( int index = 0; index < goodKeys.length; index++){
            assertThat(sut.containsValue(goodValues[index]),is(equalTo(true)));
        }
    }

    @Test
    public void iterateMap_keysValuesEntriesOrderMatches(){
        for(int index = 0; index < goodKeys.length; index++){
            sut.put(goodKeys[index],goodValues[index]);
        }

        Set<Map.Entry<String,Integer>> entries = sut.entrySet();
        Set<String> keys = sut.keySet();
        List<Integer> values = new LinkedList(sut.values());

        assertThat(entries.size(), is(equalTo(keys.size())));
        assertThat(values.size(), is(equalTo(keys.size())));

        Iterator<Map.Entry<String,Integer>> entryIt = entries.iterator();
        Iterator<String> keyIt = keys.iterator();
        Iterator<Integer> valueIt = values.iterator();

        while(entryIt.hasNext()){
            Map.Entry<String,Integer> entry = entryIt.next();

            assertThat(entry.getKey(), is(equalTo(keyIt.next())));
            assertThat(entry.getValue(),is(equalTo(valueIt.next())));;
        }
    }

    @Test
    public void get_filledWithGoodValues_allPresent(){
        for( int index = 0; index < goodKeys.length; index++){
            sut.put(goodKeys[index],goodValues[index]);
        }

        for( int index = 0; index < goodKeys.length; index++){
            assertThat(sut.get(goodKeys[index]), is(equalTo(goodValues[index])));
        }
    }

}
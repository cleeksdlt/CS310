/**
 * File: TestValues.java
 * Author: Shawn Healey
 * Version: 1.0
 *
 * CS 310 - Data Structures
 * San Diego State University
 * Spring 2017
 */
package edu.sdsu.cs.datastructures;

import java.util.Arrays;
import java.util.Collections;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * A common set of utilities for generating data sets and maintaining global constants.
 */
public class TestValues {

    /**
     * The number to use as the pseudo-random number generator seed value. By using a fixed seed, we will always use
     * the same series of 'random' values.
     */
    public static final int RANDOM_SEED = 310;

    public static final String KEY_IGNORED = "Ignored";
    public static final Integer VALUE_IGNORED = -310;

    public static String[] getSequentialKeys(int size){
        String[] toReturn = new String[size];
        for(int i = 0; i < size; i++){
            toReturn[i] = String.valueOf(i);
        }
        return toReturn;
    }

    public static String[] getRandomKeys(int size, int seed){

        List<String> randList = Arrays.asList(getSequentialKeys(size));

        Random dice = new Random(seed);
        dice.nextInt();
        Collections.shuffle(randList,dice);

        return (String[]) randList.toArray();
    }

    public static Integer[] getSequentialSquareValues(int size){
        Integer[] toReturn = new Integer[size];
        IntStream.range(0,size).forEach(val -> toReturn[val] = val*val);
        return toReturn;
    }

    public static Integer[] getRandomSquareValues(int size, int seed){

        Integer [] values = new Integer[size];
        IntStream.range(0,size).forEach(val -> values[val] = val*val);

        List<Integer> randList = Arrays.asList(values);

        Random dice = new Random(seed);
        dice.nextInt();
        Collections.shuffle(randList,dice);

        return (Integer[]) randList.toArray();
    }

}

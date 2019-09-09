package edu.sdsu.cs;

/**
 * Title: assign3
 * Author: Christian De La Torre
 * Class: CS310 Data Structures, Spring 2017
 * San Diego State University
 * Class Account: cssc0650
 * Date: 04/09/17
 * Purpose: The purpose of this class is to construct
 * the most used token, bigram and trigram using the
 * data from the users input file.
 * Issues: Methods are incomplete
 **/

import edu.sdsu.cs.datastructures.ArrayList;

public class Bigram implements Comparable<Bigram> {
    ArrayList<String> biGram = new ArrayList<>();
    String biGramToken = null;

    public Bigram() {

    }

    public Bigram(ArrayList<String> arrList) {

    }

    @Override
    public int compareTo(Bigram o) {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public String toString() {
        return null;
    }
}


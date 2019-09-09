package edu.sdsu.cs;

/**
 * assign2
 * Christian De La Torre
 * account: cssc0650
 * San Diego State University
 * CS310 Data Structures, Spring 2017
 * Date: 03/11/17
 * The Driver class calls the Tester class in order to test the complexities
 * of both the ArrayList and SinglyLinkedList.
 */

import java.io.IOException;

public class Driver {
    public static void main (String [] args) throws IOException {
        Tester test = new Tester();
        String outfile = args[0];
        if (args.length == 2) {
            Integer testSize = Integer.valueOf(args[1]);
            test.UserOptionalInput(outfile, testSize);
        } else {
            test.DefaultInput(outfile);
        }
        System.out.println("Complete!");
    }
}


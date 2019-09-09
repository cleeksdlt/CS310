package edu.sdsu.cs;

/**
 * Title: assign3
 * Author: Christian De La Torre
 * Class: CS310 Data Structures, Spring 2017
 * San Diego State University
 * Class Account: cssc0650
 * Date: 04/09/17
 * Purpose: The purpose of this class is to read
 * in the data from the users input file, and then call
 * the Bigram class to write to the output file, the most
 * used tokens, the most used bigrams and trigrams.
 * Issues: Only reads in the file but does not write to the file,
 * and does not compute the token, bigram and trigram.
 **/

import edu.sdsu.cs.datastructures.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("You must supply 2 arguments: Either an input file or an output file.");
            System.exit(0);
        }
        String firstFile = args[0];
        String secondFile = args[1];
        readFile(firstFile);
        writeFile(secondFile);
    }

    /**
     * Reads in the data from the users input file and
     * adds it onto an arraylist to be used later on.
     * @param firstFile - users input file data
     * @throws IOException
     **/
    public static void readFile (String firstFile) throws IOException {
        ArrayList<String> arr = new ArrayList<>();
        try {
            String line;
            String [] temp;
            FileReader fileReader = new FileReader(firstFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                temp = line.split("\\s+");
                for (int i = 0; i < temp.length; i++) {
                    arr.add(temp[i].toLowerCase());
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file " + firstFile);
            System.exit(1);
        } catch (IOException ex) {
            System.out.println("Error reading file " + firstFile);
            System.exit(1);
        }
    }

    /**
     * Writes to an output file, the information regarding the
     * most used tokens, bigrams and trigrams within the users
     * input file.
     * @param secondFile
     **/
    public static void writeFile (String secondFile) {

    }
}


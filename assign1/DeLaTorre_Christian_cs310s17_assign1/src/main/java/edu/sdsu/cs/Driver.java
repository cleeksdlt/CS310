package edu.sdsu.cs;

import edu.sdsu.cs.datastructures.ArrayList;
import java.io.*;
import java.util.Collections;
import java.util.Random;

/**
 * assign1
 * Christian De La Torre
 * account: cssc0650
 * San Diego State Univeristy
 * CS310 Data Structures, Spring 2017
 * Date: 02/21/17
 * assign1
 * This program reads in a CSV file containing different pokemon names and their attributes,
 * then calls the Creature class to sort the pokemon according to combat score,
 * alphabetical name, or their natural order. Which then afterwards, calls the fightResults
 * method and compares two pokemon and writes to the user outfile displaying who won.
 */
public class Driver {
    public static void main(String[] args) throws IOException {
        ArrayList<Creature> creatureData = new ArrayList<Creature>();
        String firstFile = args[0];
        String secondFile = args[1];
        creatureData = readInFile(creatureData, firstFile);
        fightResults(creatureData, secondFile);
    }

    /**
     * Reads in the CSV file from the user and adds it to the ArrayList data structure
     * @param creatureData - every creatures attributes
     * @param inFile - users CSV file
     * @return - returns the creatures attributes to main method to be stored in creatureData ArrayList
     */
    public static ArrayList<Creature> readInFile(ArrayList<Creature> creatureData, String inFile) {
        try {
            FileReader fileReader = new FileReader(inFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            while (bufferedReader.ready()) {
                Creature creature = new Creature(bufferedReader.readLine());
                Collections.addAll(creatureData, creature);
            }
            bufferedReader.close();
        } catch (IOException exception) {
            System.out.println(exception);
            System.exit(1);
        }
        return creatureData;
    }

    /**
     * Calls the Creature class after randomly selecting two pokemon from the creatureData ArrayList. By calling the
     * Creature class, fightResults then gathers a value from comparing both pokemon inside the Creature class. It is
     * then that after comparing both pokemon, that the winner and loser is displayed in the outfile.
     * @param creatureData - every creatures attributes stored in an ArrayList data structure
     * @param outfile - where the file writes to display winners and losers after each round
     * @throws IOException
     */
    public static void fightResults(ArrayList<Creature> creatureData, String outfile) throws IOException {
        FileWriter fileWrite = new FileWriter(outfile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWrite);
        ArrayList<Creature> survivors = new ArrayList<Creature>();
        ArrayList<Creature> vanquished = new ArrayList<Creature>();
        int round = 0;
        Random randGen = new Random();
        Collections.shuffle(creatureData, randGen);
        while (creatureData.size() != 1) {
            round = round + 1;
            bufferedWriter.newLine();
            bufferedWriter.write("Round: " + round);
            bufferedWriter.newLine();
            bufferedWriter.write("Combatants: " + creatureData.size());
            bufferedWriter.newLine();
            for (int i = 0; i < creatureData.size() - 1; i += 2) {
                Creature creature1 = creatureData.get(i);
                Creature creature2 = creatureData.get(i + 1);
                if(creature1 == creature2) {
                    bufferedWriter.write(creature1.toString() + " automatically advances.");
                    bufferedWriter.newLine();
                }
                int creature = creature1.Fight(creature2);
                if (creature == 0 && !creature2.legendary) {
                    vanquished.add(creature2);
                    bufferedWriter.write(creature1.toString() + " defeats " + creature2.toString());
                    bufferedWriter.newLine();
                    creatureData.remove(creature2);
                }
                if (creature == 0 && creature2.legendary) {
                    if (creature2.isloser) {
                        vanquished.add(creature2);
                        creatureData.remove(creature2);
                        bufferedWriter.write(creature1.toString() + " finally vanquished " + creature2.toString());
                        bufferedWriter.newLine();
                    } else {
                        bufferedWriter.write(creature1.toString() + " defeats " + creature2.toString());
                        bufferedWriter.newLine();
                    }
                }
                if (creature == 1 && !creature1.legendary) {
                    vanquished.add(creature1);
                    bufferedWriter.write(creature2.toString() + " defeats " + creature1.toString());
                    bufferedWriter.newLine();
                    creatureData.remove(creature1);
                }
                if (creature == 1 && creature1.legendary) {
                    if (creature1.isloser == true) {
                        vanquished.add(creature1);
                        creatureData.remove(creature1);
                        bufferedWriter.write(creature2.toString() + " finally vanquished " + creature1.toString());
                        bufferedWriter.newLine();
                    } else {
                        bufferedWriter.write(creature2.toString() + " defeats " + creature1.toString());
                        bufferedWriter.newLine();
                    }
                }
                if (creatureData.size() == 1) {
                    bufferedWriter.newLine();
                    bufferedWriter.write("Round: " + (round + 1));
                    bufferedWriter.newLine();
                    bufferedWriter.write(creatureData.get(0).toString() + " is the champion");
                    survivors.add(creatureData.get(0));
                    bufferedWriter.newLine();
                    bufferedWriter.write("Round Complete!");
                    bufferedWriter.close();
                }
            }
        }
    }
}

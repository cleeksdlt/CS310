package edu.sdsu.cs;

/**
 * assign2
 * Christian De La Torre
 * account: cssc0650
 * San Diego State University
 * CS310 Data Structures, Spring 2017
 * Date: 03/11/17
 * The Tester demonstrates the complexity of the both the
 * SinglyLinkedList and the ArrayList data structure. By implementing
 * a timer and recording the time each series of operations takes as
 * input size increases, the Tester class is able to write to an OpenCSV
 * file to illustrate complexity speed of both data structures.
 */

import au.com.bytecode.opencsv.CSVWriter;
import edu.sdsu.cs.datastructures.ArrayDeque;
import edu.sdsu.cs.datastructures.ListDeque;
import java.io.FileWriter;
import java.io.IOException;

public class Tester {
    public void UserOptionalInput (String outfile, Integer testSize) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(outfile));
        ArrayDeque arrDeque = new ArrayDeque();
        ListDeque listDeque = new ListDeque();
        long start = System.nanoTime();
        long time;
        int N = 1;

        writer.writeNext(new String[]{"ArrayList Complexity Results"});
        writer.writeNext("method,time,n".split(","));
        for (int i = 0; i < testSize; i++) {
            arrDeque.addFirst(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.addFirst(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.addFirst(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addFirst,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            arrDeque.addLast(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.addLast(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.addLast(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addLast,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            arrDeque.pollFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.pollFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.pollFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollFirst,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            arrDeque.pollLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.pollLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.pollLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollLast,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            arrDeque.peekFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.peekFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.peekFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekFirst,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            arrDeque.peekLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.peekLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.peekLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekLast,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        writer.writeNext(new String[]{"SinglyLinkedList Complexity Results"});
        writer.writeNext("method,time,n".split(","));
        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            listDeque.addFirst(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.addFirst(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.addFirst(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addFirst,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            listDeque.addLast(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.addLast(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.addLast(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addLast,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            listDeque.pollFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.pollFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.pollFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollFirst,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            listDeque.pollLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.pollLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.pollLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollLast,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            listDeque.peekFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.peekFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.peekFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekFirst,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            listDeque.peekLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.peekLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.peekLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekLast,0." + time + "," + testSize});

        writer.close();
    }

    public void DefaultInput(String outfile) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(outfile));
        ArrayDeque arrDeque = new ArrayDeque();
        ListDeque listDeque = new ListDeque();
        long start = System.nanoTime();
        long testSize = 2048;
        long time;
        int N = 1;

        writer.writeNext(new String[]{"ArrayList Complexity Results"});
        writer.writeNext("method,time,n".split(","));
        for (int i = 0; i < testSize; i++) {
            arrDeque.addFirst(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.addFirst(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.addFirst(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addFirst,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            arrDeque.addLast(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.addLast(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.addLast(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addLast,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            arrDeque.pollFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.pollFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.pollFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollFirst,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            arrDeque.pollLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.pollLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.pollLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollLast,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            arrDeque.peekFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.peekFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.peekFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekFirst,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            arrDeque.peekLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.peekLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            arrDeque.peekLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekLast,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        writer.writeNext(new String[]{"SinglyLinkedList Complexity Results"});
        writer.writeNext("method,time,n".split(","));
        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            listDeque.addFirst(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.addFirst(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.addFirst(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addFirst,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            listDeque.addLast(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.addLast(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.addLast(N);
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"addLast,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            listDeque.pollFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.pollFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.pollFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollFirst,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            listDeque.pollLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.pollLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.pollLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"pollLast,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            listDeque.peekFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.peekFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekFirst,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.peekFirst();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekFirst,0." + time + "," + testSize});
        writer.writeNext(new String[]{});

        start = System.nanoTime();
        testSize = 2048;
        for (int i = 0; i < testSize; i++) {
            listDeque.peekLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.peekLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekLast,0." + time + "," + testSize});

        start = System.nanoTime();
        testSize = (testSize * 2);
        for (int i = 0; i < testSize; i++) {
            listDeque.peekLast();
        }
        time = (System.nanoTime() - start);
        writer.writeNext(new String[]{"peekLast,0." + time + "," + testSize});

        writer.close();

    }
}

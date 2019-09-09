/**
 * San Diego State University.<br> CS 310: Data Structures<br> Spring 2016<br>
 * <p>
 * <p> Driver file for the breadth-first-search programming assignment. Do not
 * submit this file. </p>
 *
 * @version 1.0
 */
package edu.sdsu.cs.maze;

import edu.sdsu.cs.datastructures.IBasicDeque;
import edu.sdsu.cs.datastructures.ArrayDeque;

import java.awt.*;

/**
 * A simple driver class which, upon construction, generates and solves a series
 * of randomly generated mazes. This implementation must uses each
 * <b>student's</b> <code>LinkedList</code> (or <code>SlowDeque</code>)
 * implementation to fulfill the <code>Deque</code> requirements. Alternatively,
 * f one chooses to import <code>java.util.LinkedList</code> instead, perhaps
 * due to missing the previous assignment, the solution will receive a <i>point
 * penalty</i>, but I will accept the submission.
 *
 * @version 1.0
 */
public class MazeDriver {

    /**
     * Color everything reverts to when cleared.
     */
    static final Color CLR_BACKGROUND = Color.BLACK;
    /**
     * Primary color used when highlighting the first player's route.
     */
    static final Color CLR_PLAYER_1 = Color.BLUE;
    /**
     * Primary color used for the second player's route.
     */
    static final Color CLR_PLAYER_2 = Color.GREEN;
    /**
     * Establishes the highlighted route's "head" length during display.
     */
    static final int NUM_STEPS_TO_HIGHLIGHT = 3;
    /**
     * Establishes the pixel resolution for each edge of the display window.
     * Adjust this so it looks best on your machine.
     */
    private static int displayResolution = 280;
    /**
     * Number of <code>MazeRoom</code> objects per maze side.
     */
    private static int edgeSize = 15;

    /**
     * specifies the size of maze in <code>MazeRoom</code> objects.
     *
     * @param size number of rooms on each edge, so the maze has size^2 rooms total
     */
    private MazeDriver(int size) {
        initDraw(size);
    }

    /**
     * A simple exercise of the <code>MazeBuilder</code> and
     * <code>MazeSolution</code> classes. This program displays and solves an
     * infinite series of randomly generated mazes. The generation and solution
     * process relies heavily on the <code>java.util.Deque</code> interface.
     *
     * @param args Although one may provide arguments, this program does not use them
     * @throws InterruptedException when one of the runner threads throws an exception.
     */
    public static void main(String[] args) throws InterruptedException {

        if (args.length > 0) {
            edgeSize = Integer.parseInt(args[0]);
        }

        if (args.length == 2) {
            displayResolution = Integer.parseInt(args[1]);
        }

        SimpleDraw.setCanvasSize(displayResolution, displayResolution);
        MazeDriver maze = new MazeDriver(edgeSize);
        maze.start();
    }

    private void initDraw(int size) {
        SimpleDraw.setXscale(0, size);
        SimpleDraw.setYscale(0, size);
        SimpleDraw.clear(CLR_BACKGROUND);
        SimpleDraw.setPenColor(Color.green);
    }

    /**
     * Creates and starts a new coordinator thread. The Coordinator object, in
     * turn, handles generating, displaying, and creating the maze solving
     * threads.
     */
    public void start() {
        Thread th = new Thread(new Coordinator(edgeSize));
        th.start();
    }

    /**
     * A coordinating thread responsible for controlling the creation and solution
     * of random mazes.
     */
    static class Coordinator implements Runnable {

        private final int numRoomsPerEdge;
        /**
         * Periodically the program pauses to facilitate human processing, and this
         * establishes the delay in milliseconds.
         */
        private final int pauseMillis = 2000;

        /**
         * Create a new <code>Coordinator</code> object for mazes with the indicated
         * edge size.
         *
         * @param size number of <code>MazeRoom</code> objects per maze size, so passing in 25 produces a 25x25 room
         *             maze.
         */
        public Coordinator(int size) {
            numRoomsPerEdge = size;
        }

        private static void displayStats(int[] wins, IBasicDeque<MazeRoom> lowLeftToUpRight,
                                         IBasicDeque<MazeRoom> lowRightToUpLeft, IBasicDeque<MazeRoom> common) {

            if (lowLeftToUpRight.size() < lowRightToUpLeft.size()) {
                wins[0]++;
            } else {
                wins[1]++;
            }

            System.out.println("Blue: " + wins[0] + " vs. Yellow: " + wins[1]);
            System.out.println("Length blue route: " + lowLeftToUpRight.size());
            System.out.println("Length yellow route: " + lowRightToUpLeft.size());
            System.out.println("Steps in common: " + common.size());
        }

        private void pauseBriefly() {
            try {
                Thread.sleep(pauseMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**
         * As a runnable object, the outside world will call this method when it
         * wishes it to perform work. In this example, it occurs when the
         * <code>main</code> method creates and starts the coordinator thread.
         */
        @Override
        public void run() {

            int[] score = {0, 0};

            while (true) {
                Maze sut = new Maze(numRoomsPerEdge);
                SimpleDraw.clear(CLR_BACKGROUND);
                sut.draw();
                pauseBriefly();

                // Generate the maze solutions
                IBasicDeque<MazeRoom> lowLeftToUpRight = new MazeSolution(sut).solve(
                        new Point(0, 0), new Point(numRoomsPerEdge - 1, numRoomsPerEdge - 1));
                IBasicDeque<MazeRoom> lowRightToUpLeft = new MazeSolution(sut).solve(
                        new Point(0, numRoomsPerEdge - 1), new Point(numRoomsPerEdge - 1, 0));

                try {
                    // Determine how much of the maze they shared
                    IBasicDeque<MazeRoom> common = new ArrayDeque<>(lowLeftToUpRight);
                    common.retainAll(lowRightToUpLeft);

                    displayStats(score, lowLeftToUpRight, lowRightToUpLeft, common);

                    // launch the threads responsible for running the maze
                    runCornerPaths(sut, lowLeftToUpRight, lowRightToUpLeft);
                    runCommonPath(sut, common);
                } catch (NullPointerException e) {
                    System.out.println("Error: unable to find solution");
                }
                pauseBriefly();
            }
        }

        private void runCommonPath(IRenderable sut, IBasicDeque<MazeRoom> common) {
            Thread th3 = new Thread(new RunMaze(sut, common, Color.red));
            th3.start();
            try {
                th3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void runCornerPaths(IRenderable sut,
                                    IBasicDeque<MazeRoom> lowLeftToUpRight, IBasicDeque<MazeRoom> lowRightToUpLeft) {
            Thread th1 = new Thread(new RunMaze(sut, lowLeftToUpRight, CLR_PLAYER_1));
            Thread th2 = new Thread(new RunMaze(sut, lowRightToUpLeft, CLR_PLAYER_2));

            th1.start();
            th2.start();

            try {
                th1.join();
                th2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * A simple thread responsible for displaying a route in a maze.
     */
    private static class RunMaze implements Runnable {

        final Color myColor;
        final IRenderable myMaze;
        final IBasicDeque<MazeRoom> myRoute;

        /**
         * Establishes the values to use when running the route.
         *
         * @param maze  The <code>Maze</code> type object to render
         * @param route A series of <code>MazeRoom</code> objects the caller wishes to highlight in the order they
         *              appear in the list
         * @param color color to use when rendering the maze highlights
         */
        public RunMaze(IRenderable maze, IBasicDeque<MazeRoom> route, Color color) {
            myRoute = route;
            myMaze = maze;
            myColor = color;
        }

        /**
         * periodically highlights MazeRoom objects within the maze and updates the
         * maze's clock (and a bad place to do it). In reality, this thread should
         * simply update the maze's highlights and then sleep. The coordinator
         * object should then wait for all its threads to put themselves to sleep
         * after highlighting the path, and then call the draw and update method
         * once for all of them. When the update completes, it should notify the
         * sleeping threads, and they then update the maze again for the next turn.
         */
        @Override
        public void run() {
            for (MazeRoom rm : myRoute) {
                rm.setRenderHighlight(true, myColor);
                myMaze.draw();
                myMaze.update();
            }
        }
    }

}

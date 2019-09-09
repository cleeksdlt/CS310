/**
 * This file defines a <code>Renderable</code> maze object. You will not submit
 * this when you deliver the assignment, so feel free to modify the colors to
 * suit your style. That said, if you need to change the package in order for
 * this to compile on your machine, you have an incorrect classpath setting.
 * This will yield a major points deduction.
 *
 * @version 1.1
 * @author Shawn Healey, San Diego State University
 */

package edu.sdsu.cs.maze;

/**
 * A maze object capable of being rendered to the screen.
 */
public class Maze implements IRenderable {

    /**
     * Number of mS to yield between route rendering steps. Increasing this value
     * will slow the route's running down.
     */
    private static final int displayTime = 30;

    /**
     * Number of <code>MazeRoom</code> objects per side.
     */
    private final int edgeLength;

    /**
     * The array of <code>MazeRoom</code> objects representing the maze.
     */
    private MazeRoom[][] theGrid;

    /**
     * Used to help reduce rendering artifacts due to multiple processes trying to
     * write at the same time.
     *
     * @see //https://en.wikipedia.org/wiki/Semaphore_%28programming%29
     */
    private Object drawLock = new Object();

    /**
     * Generates a square maze of given edge length.
     *
     * @param size number of rooms per side
     */
    public Maze(int size) {
        edgeLength = size;
        theGrid = (new MazeBuilder(size)).generate();
    }

    /**
     * The length and width of the maze.
     *
     * @return a non-negative number indicating the number of rooms per side on the maze.
     */
    public int size() {
        return edgeLength;
    }

    /**
     * Provides outside classes with access to the <code>MazeRoom</code> object
     * stored at the indicated position in the grid array.
     *
     * @param row The Y value (theGrid[row][col])
     * @param col The X value
     * @return A reference to the room at the provided coordinates.
     */
    public MazeRoom getRoom(int row, int col) {
        return onGrid(row, col) ? theGrid[row][col] : null;
    }

    public void draw() {

        SimpleDraw.show(0);
        for (int col = 0; col < edgeLength; col++) {
            synchronized (drawLock) {
                for (int row = 0; row < edgeLength; row++) {
                    theGrid[row][col].draw();
                }
            }
        }
        SimpleDraw.show(displayTime);
    }

    /**
     * indicates if the coordinates provided by the caller correspond to a valid
     * position within the Maze.
     *
     * @param startRow the Y coordinate to check
     * @param startCol The X coordinate to check
     * @return true if the provided coordinates are, in fact, within the maze's bounds
     */
    private boolean onGrid(int startRow, int startCol) {
        return !(outOfRange(startRow) || outOfRange(startCol));
    }

    private boolean outOfRange(final int index) {
        return (index < 0 || index >= edgeLength);
    }

    public void update() {
        for (int rowCounter = 0; rowCounter < edgeLength; rowCounter++) {
            for (int colCounter = 0; colCounter < edgeLength; colCounter++) {
                theGrid[rowCounter][colCounter].update();
            }
        }
    }
}

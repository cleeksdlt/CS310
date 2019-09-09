package edu.sdsu.cs.maze;

import edu.sdsu.cs.datastructures.IBasicDeque;
import edu.sdsu.cs.datastructures.ArrayDeque;

import java.awt.*;
import java.util.Random;

public class MazeBuilder {

    /**
     * By providing the pseudo-random number generator with a seed number, we
     * guarantee we get the same "random" numbers every time. Thus, one may
     * perform testing without the added uncertainty of not knowing if the reason
     * it didn't fail this time was because you fixed the problem, or because it
     * just had a different maze.
     */
    private static final Random dice = new Random(310);

    private boolean[][] visited;
    private MazeRoom[][] theGrid;

    public MazeBuilder(int edgeLength) {
        visited = new boolean[edgeLength][edgeLength];
        theGrid = initializeRooms(edgeLength);
    }

    /**
     * @return
     */
    public MazeRoom[][] generate() {

        generate(theGrid[0][0]);
        return theGrid;
    }

    private void generate(MazeRoom start) {

        if (!unvisited(start.getRow(), start.getCol())) {
            return;
        }

        visited[start.getRow()][start.getCol()] = true;

        while (!getUnvisitedNeighbors(start).isEmpty()) {
            MazeRoom neighbor = connectToUnvisitedNeighbor(start);
            generate(neighbor);
        }
    }

    private MazeRoom connectToUnvisitedNeighbor(MazeRoom start) {

        MazeRoom connected;
        int row = start.getRow();
        int col = start.getCol();

        while (true) {
            int roll = dice.nextInt(4);
            if (roll == 0) {
                if (unvisited(row + 1, col)) {
                    connected = theGrid[row + 1][col];
                    break;
                }
            } else if (roll == 1) {
                if (unvisited(row - 1, col)) {
                    connected = theGrid[row - 1][col];
                    break;
                }
            } else if (roll == 2) {
                if (unvisited(row, col + 1)) {
                    connected = theGrid[row][col + 1];
                    break;
                }
            } else {
                if (unvisited(row, col - 1)) {
                    connected = theGrid[row][col - 1];
                    break;
                }
            }
        }
        connectNeighbors(start, connected);
        return connected;
    }

    private static MazeRoom[][] initializeRooms(int size) {
        MazeRoom[][] grid = new MazeRoom[size][size];
        for (int rowCounter = 0; rowCounter < size; rowCounter++) {
            for (int colCounter = 0; colCounter < size; colCounter++) {
                grid[rowCounter][colCounter] = new MazeRoom(new Point(colCounter, rowCounter), 1);
            }
        }
        return grid;
    }

    private boolean unvisited(int row, int col) {
        return !(Math.min(row, col) < 0 || Math.max(row, col) >= visited.length) && !visited[row][col];
    }

    private IBasicDeque<MazeRoom> getUnvisitedNeighbors(MazeRoom target) {

        final int row = target.posInMaze.y;
        final int col = target.posInMaze.x;

        IBasicDeque<MazeRoom> toVisit = new ArrayDeque<MazeRoom>();
        if (unvisited(row + 1, col)) {
            toVisit.offer(theGrid[row + 1][col]);
        }
        if (unvisited(row - 1, col)) {
            toVisit.offer(theGrid[row - 1][col]);
        }
        if (unvisited(row, col + 1)) {
            toVisit.offer(theGrid[row][col + 1]);
        }
        if (unvisited(row, col - 1)) {
            toVisit.offer(theGrid[row][col - 1]);
        }

        return toVisit;
    }

    private static void connectNeighbors(MazeRoom source, MazeRoom neighbor) {

        if (source.getRow() == neighbor.getRow() + 1) {
            source.removeWallSouth();
            neighbor.removeWallNorth();
        } else if (source.getRow() == neighbor.getRow() - 1) {
            source.removeWallNorth();
            neighbor.removeWallSouth();
        } else if (source.getCol() == neighbor.getCol() + 1) {
            source.removeWallWest();
            neighbor.removeWallEast();
        } else if (source.getCol() == neighbor.getCol() - 1) {
            source.removeWallEast();
            neighbor.removeWallWest();
        }
    }
}

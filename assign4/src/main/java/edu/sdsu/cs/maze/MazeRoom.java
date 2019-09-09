/**
 * This file defines a <code>MazeRoom</code> object. Do not submit this file.
 *
 * @version 1.0
 * @author Shawn Healey, San Diego State University
 */
package edu.sdsu.cs.maze;

import java.awt.Color;
import java.awt.Point;

/**
 * A single cell within the maze. Theoretically, one could extend this class to
 * add different functionality. That is, one might want to create a custom room
 * which renders cave-like walls instead of the crisp, clean lines drawn here.
 *
 * @author Shawn Healey
 */
public class MazeRoom implements IRenderable {

    final Point posInMaze;
    /**
     * As noted in the setRenderHighlight method, directly calling these global,
     * static members is convenient at the moment, but a flawed design decision.
     * Changing the maze colors is an unnecessarily onerous task. By making
     * something easier for us at the moment, we have ultimately hurt ourselves in
     * the long-run.
     */
    private Color clrClear = Color.DARK_GRAY;
    private Color clrHighlight = Color.GREEN;
    private int highlightDuration;
    private boolean highlightVisible = false;
    private double highlightSize = 0.25;
    private boolean wallEast = true;
    private boolean wallNorth = true;
    private boolean wallSouth = true;
    private boolean wallWest = true;

    /**
     * constructs a complete room with four walls one may remove later.
     *
     * @param position A point representing the location of this cell within the grid. The point's Y-value references
     *                 the row.
     */
    public MazeRoom(Point position, int offset) {
        posInMaze = position;
    }

    /**
     * checks to see if it is possible to move to the east (col+1) in the maze.
     *
     * @return true if one may move from this room in the indicated direction
     */
    public boolean canMoveEast() {
        return !wallEast;
    }

    /**
     * checks to see if it is possible to move to the north (row+1) in the maze.
     *
     * @return true if one may move from this room in the indicated direction
     */
    public boolean canMoveNorth() {
        return !wallNorth;
    }

    /**
     * destroys the wall in the indicated direction.
     */
    public void removeWallEast() {
        wallEast = false;
    }

    /**
     * destroys the wall in the indicated direction.
     */
    public void removeWallWest() {
        wallWest = false;
    }

    /**
     * destroys the wall in the indicated direction.
     */
    public void removeWallSouth() {
        wallSouth = false;
    }

    /**
     * destroys the wall in the indicated direction.
     */
    public void removeWallNorth() {
        wallNorth = false;
    }

    /**
     * checks to see if it is possible to move to the south (row-1) in the maze.
     *
     * @return true if one may move from this room in the indicated direction
     */
    public boolean canMoveSouth() {
        return !wallSouth;
    }

    /**
     * checks to see if it is possible to move to the west (col-1) in the maze.
     *
     * @return true if one may move from this room in the indicated direction
     */
    public boolean canMoveWest() {
        return !wallWest;
    }

    /**
     * Renders the individual maze directly to the back-image.
     */
    @Override
    public void draw() {
        render();
    }

    /**
     * provides the caller with this room's stored column (x-position) within the
     * maze.
     *
     * @return the room's position <code>(0 < (edge-size -1))</code> along this axis
     */
    public int getCol() {
        return posInMaze.x;
    }

    /**
     * provides the caller with this room's row (y-position) within the maze.
     *
     * @return the room's position <code>(0 < (edge-size -1))</code> along this axis
     */
    public int getRow() {
        return posInMaze.y;
    }

    private void render() {

        final Point adjustedPos = new Point(posInMaze.x,
                posInMaze.y);

        if (wallNorth) {
            SimpleDraw.line(adjustedPos.getY() + 1, adjustedPos.getX(),
                    adjustedPos.getY() + 1, adjustedPos.getX() + 1);
        }
        if (wallSouth) {
            SimpleDraw.line(adjustedPos.getY(), adjustedPos.getX(),
                    adjustedPos.getY(), adjustedPos.getX() + 1);
        }
        if (wallEast) {
            SimpleDraw.line(adjustedPos.getY(), adjustedPos.getX() + 1,
                    adjustedPos.getY() + 1, adjustedPos.getX() + 1);
        }
        if (wallWest) {
            SimpleDraw.line(adjustedPos.getY(), adjustedPos.getX(),
                    adjustedPos.getY() + 1, adjustedPos.getX());
        }

        renderHighlight(adjustedPos.getY() + .5, adjustedPos.getX() + .5);
    }

    protected void renderHighlight(double row, double col) {
        if (highlightVisible) {
            double size = highlightSize;
            if (highlightDuration <= 0) {
                highlightVisible = false;
                SimpleDraw.setPenColor(clrClear);
                SimpleDraw.filledCircle(row, col, size);
                size *= .25;
                SimpleDraw.setPenColor(clrHighlight.darker());
            } else {
                SimpleDraw.setPenColor(clrHighlight);
            }
            SimpleDraw.filledCircle(row, col, size);
            SimpleDraw.setPenColor(clrClear);
        }
    }

    /**
     * Sets the color of the room's highlight and establishes if this object
     * should draw the highlight on the next draw cycle. If it does so, then the
     * highlight will appear for the established duration. Note: this method
     * presently makes global call to the MazeDriver class. Given the unique color
     * information used during the rendering process, it makes design sense to try
     * and find a better way of passing in these data. The way this is written,
     * the MazeRoom is entirely dependent on the MazeDriver, and one may not use
     * the MazeRoom without the MazeDriver in the project. This is an added and
     * entirely unnecessary dependency. The intent is to provide you with a
     * working example of why we want to avoid this type of design.
     *
     * @param enabled true to display the highlight during the rendering process
     * @param color   color to use when rendering the highlight
     */
    public void setRenderHighlight(boolean enabled, Color color) {
        highlightVisible = enabled;
        highlightDuration = MazeDriver.NUM_STEPS_TO_HIGHLIGHT;
        clrHighlight = color;
    }

    /**
     * Decays the highlight from its large size to its smaller size.
     */
    @Override
    public void update() {
        if (highlightDuration > 0) {
            highlightDuration--;
        }
    }
}
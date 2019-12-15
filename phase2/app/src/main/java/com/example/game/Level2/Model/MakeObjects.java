package com.example.game.Level2.Model;

import android.graphics.Color;
import android.util.Pair;

import com.example.game.Level2.Presenter.DrawObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Make objects creates all the objects in the canvas and then stores them to a list
 */
public class MakeObjects {

    private List<RightBall> right;
    private List<LeftBall> left;
    private List<Drawable> allObjects;
    private List<Line> lines;
    private List<UndoButton> undoObject;
    private int difficulty;

    /**
     * Instantiates a new Make objects.
     *
     * @param difficulty the difficulty
     */
    public MakeObjects(int difficulty) {
        this.difficulty = difficulty;
        right = new ArrayList<>();
        left = new ArrayList<>();
        lines = new ArrayList<>();
        allObjects = new ArrayList<>();
        undoObject = new ArrayList<>();
        makeBalls(difficulty);
        makePairs();
        setTarget();
        makeLines();
        createUndo();
    }

    /**
     * makes four balls on each side
     * @param number of balls to draw
     */
    private void makeBalls(int number) {
        int count = 300;
        for (int i = number; i > 0; i--) {
            LeftBall b = new LeftBall(200, count, Color.BLUE);
            RightBall pair = new RightBall(900, count, Color.BLUE);
            right.add(pair);
            left.add(b);
            allObjects.add(pair);
            allObjects.add(b);
            count += 400;
        }
    }

    /**
     * randomizes the bounds for one of the two points for the lines
     * @param bounds the min x values
     * @return integer value for x
     */
    private int randomizeBounds(int bounds) {
        return (int) (Math.random() * (bounds - 350)) + 350;
    }

    /**
     * randomizes two points in the middle of two sets of balls then draws lines between them
     */
    private void makeLines() {
        List<Pair> coords = new ArrayList<>();
        int x1, x2, y1, y2;
        for (LeftBall b : left) {
            while (true) {
                x1 = randomizeBounds(751);
                y1 = randomizeBounds(1751);
                x2 = randomizeBounds(751);
                y2 = randomizeBounds(1751);
                Pair c1 = Pair.create(x1, y1);
                Pair c2 = Pair.create(x2, y2);
                if (x1 < (x2 - 50) && Math.abs(y1 - y2) >= 100 &&
                        !coords.contains(c1) && !coords.contains(c2)) {
                    break;
                }
            }
            coords.add(Pair.create(x1, y1));
            coords.add(Pair.create(x2, y2));
            Line l1 = new Line(b.getX() + 50, b.getY(), x1, y1, Color.BLUE);
            Line l2 = new Line(x1, y1, x2, y2, Color.BLUE);
            Line l3 = new Line(x2, y2, b.getPair().getX(), b.getPair().getY(), Color.BLUE);
            List<Line> createdLines = Arrays.asList(l1, l2, l3);
            lines.addAll(createdLines);
            allObjects.addAll(createdLines);

        }
    }

    /**
     * sets the 1 target
     */
    private void setTarget() {
        int r = (int) (Math.random() * right.size());
        RightBall b1 = right.get(r);
        b1.setTarget();
        b1.getPair().setTarget();
    }

    /**
     * makes pairs
     */
    private void makePairs() {

        ArrayList<Integer> ints = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        int r;
        for (LeftBall b : left) {
            r = (int) (Math.random() * (ints.size()));
            RightBall b1 = right.get(ints.get(r));
            ints.remove(r);
            b.setPair(b1);
            b1.setPair(b);
        }
    }

    /**
     * Gets left.
     *
     * @return the left
     */
    List<LeftBall> getLeft() {
        return left;
    }

    /**
     * Gets all objects.
     *
     * @return the all objects
     */
    public List<Drawable> getAllObjects() {
        return allObjects;
    }

    /**
     * Gets undo object.
     *
     * @return the undo object
     */
    public List<UndoButton> getUndoObject() {
        return undoObject;
    }

    /**
     * Create undo.
     */
    private void createUndo() {
        UndoButton undo = new UndoButton();
        undoObject.add(undo);
    }

    /**
     * Reset game.
     */
    void resetGame() {
        left = new ArrayList<>();
        right = new ArrayList<>();
        lines = new ArrayList<>();
        allObjects = new ArrayList<>();
        makeBalls(difficulty);
        makePairs();
        setTarget();
        makeLines();
    }
}

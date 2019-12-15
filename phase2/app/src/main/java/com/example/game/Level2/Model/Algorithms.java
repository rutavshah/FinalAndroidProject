package com.example.game.Level2.Model;

/**
 * Interface for all the algorithms
 */
public interface Algorithms{

    /**
     * Button pressed.
     *
     * @param x the x variable of the click
     * @param y the y variable of the click
     */
    void buttonPressed(float x, float y);

    /**
     * resets the game
     */
    void resetGame();

    /**
     * Gets score.
     *
     * @return the score
     */
    String getScore();

    /**
     * Gets lives.
     *
     * @return returns number of lives remaining
     */
    int getLives();

    /**
     * undo's the last click that was wrong
     */
    void undo();

    /**
     * Gets boolean variable gameOver
     *
     * @return gets game over
     */
    boolean getGameOver();

    /**
     * Gets undo to see if undo has been pressed
     *
     * @return the undo
     */
    boolean getUndo();

    /**
     * Gets game score.
     *
     * @return the game score
     */
    int getGameScore();
}

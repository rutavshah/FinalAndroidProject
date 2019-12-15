package com.example.game.Level3.GameLogic;

import com.example.game.Level3.Entities.Ball;
import com.example.game.Level3.GameElements.GameElements;

import java.util.ArrayList;

/**
 * The Easter egg.
 */
class EasterEgg {
    private boolean executed;

    /**
     * Instantiates a new Easter egg.
     */
    EasterEgg(){
        this.executed = false;
    }

    /**
     * Execute easter egg on very specific and rare conditions.
     *
     * @param gameElements the game elements
     */
    void executeEasterEgg(GameElements gameElements) {
        if (!this.executed) {
            ArrayList<Ball> balls = gameElements.balls;
            if (balls.get(0).isTouched() && balls.get(2).isTouched() && balls.get(6).isTouched() && balls.get(8).isTouched() && gameElements.lives == 1) {
                gameElements.lives = 7;
                gameElements.score = gameElements.score + 25;
                this.executed = true;
            }
        }
    }
}

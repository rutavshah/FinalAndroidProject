package com.example.game.Game1.Logic;

import android.graphics.Canvas;

import com.example.game.Game1.Entities.GameManager;

public class Game1Interactor {

    boolean checker ;
    void ballMove(float touchX, GameManager manager) {

        manager.ballMove(touchX);
    }

    void update(GameManager manager) {
        manager.update();

    }

    public boolean draw(Canvas canvas, GameManager gameManager) {
        checker = gameManager.stopGame();


        return gameManager.draw(canvas);
    }


}

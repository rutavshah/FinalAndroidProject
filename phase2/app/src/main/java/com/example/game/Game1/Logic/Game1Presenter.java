package com.example.game.Game1.Logic;

import android.graphics.Canvas;

import com.example.game.Game1.Entities.GameManager;

public class Game1Presenter {
    private Game1Interactor game1Interactor = new Game1Interactor();



    public Game1Presenter(){


    }

    public void ballMove(float touchX, GameManager manager) {

        game1Interactor.ballMove(touchX, manager);


    }


    public void update(GameManager manager) {
        game1Interactor.update(manager);

    }

    public boolean draw(Canvas canvas, GameManager gameManager) {
       game1Interactor.draw(canvas, gameManager);
        return game1Interactor.checker;
    }

}

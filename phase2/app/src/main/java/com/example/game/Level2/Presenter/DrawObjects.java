package com.example.game.Level2.Presenter;


import android.graphics.Canvas;

import com.example.game.Level2.Model.Drawable;
import com.example.game.Level2.Model.MakeObjects;

/**
 * The type Draw objects.
 */
public class DrawObjects {
    /**
     * draws all the objects on canvas
     *
     * @param canvas      the canvas
     * @param makeObjects the make objects
     */
    public void draw(Canvas canvas, MakeObjects makeObjects) {
        for (Drawable allObjects : makeObjects.getAllObjects()) {
            allObjects.draw(canvas);
        }
    }

    /**
     * draws the undo button.
     *
     * @param canvas      the canvas
     * @param makeObjects the make objects
     */
    void undoButton(Canvas canvas, MakeObjects makeObjects) {
        for (Drawable undo : makeObjects.getUndoObject()) {
            undo.draw(canvas);
        }
    }
}

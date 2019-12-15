package com.example.game.Game1.Entities;

import java.util.ArrayList;

/**
 * Creates the white barrier which the user has to avoid.
 */
public class GameMode1 implements Algorithms {

    /**
     * creates 4 barriers for the user and adds it to the list which the user has sent. The sent
     * list is mutated.
     */
    @Override
    public void createBarriers(ArrayList<Barrier> items) {
            Barrier b1 = new Barrier(0, "WB");
            items.add(b1);
            Barrier b2 = new Barrier(10, "WB");
            items.add(b2);
            Barrier b3 = new Barrier(20, "WB");
            items.add(b3);
            Barrier b4 = new Barrier(30, "WB");
            items.add(b4);
    }

    /**
     * Creates a single barrier which is specified by the user.
     * @param newBarrierHeight      the height at which the barrier is supposed to be created.
     * @return      Creates a white barrier at the height of newBarrierHeight.
     */
    @Override
    public Barrier addBarrierAtTop(float newBarrierHeight) {
        Barrier b = new Barrier(newBarrierHeight,"WB");
        return b;
    }
}

package com.example.game.Game1.Entities;

import java.util.ArrayList;

public class GameMode3 implements Algorithms {

    /**
     * creates 4 barriers for the user and adds it to the list which the user has sent. The sent
     * list is mutated.
     */
    @Override
    public void createBarriers(ArrayList<Barrier> items) {
        Barrier b1 = new Barrier(0, "BB");
            items.add(b1);
            Barrier b2 = new Barrier(10, "BB");
            items.add(b2);
            Barrier b3 = new Barrier(20, "BB");
            items.add(b3);
            Barrier b4 = new Barrier(30, "BB");
            items.add(b4);
    }

    /**
     * Creates a single barrier which is specified by the user.
     * @param newBarrierHeight      the height at which the barrier is supposed to be created.
     * @return      Creates a blue barrier at the height of newBarrierHeight.
     */
    @Override
    public Barrier addBarrierAtTop(float newBarrierHeight) {
        Barrier b = new Barrier(newBarrierHeight, "BB");
        return b;
    }
}

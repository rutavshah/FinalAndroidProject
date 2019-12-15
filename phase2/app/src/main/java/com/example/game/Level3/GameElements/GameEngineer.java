package com.example.game.Level3.GameElements;

/**
 * The type Game engineer.
 */
public class GameEngineer {
    private GameBuilder gameBuilder;

    /**
     * Instantiates a new Game engineer.
     *
     * @param gameBuilder the game builder
     */
    public GameEngineer(GameBuilder gameBuilder){
        this.gameBuilder = gameBuilder;  // Dependency Injection
    }

    /**
     * Get game elements game elements.
     *
     * @return the game elements
     */
    public GameElements getGameElements(){
        return this.gameBuilder.getGameElements();
    }

    /**
     * Construct game.
     */
    public void constructGame(){
        this.gameBuilder.buildBalls();
        this.gameBuilder.buildMemoryBall();
        this.gameBuilder.buildBitmapColours();

        this.gameBuilder.buildGameRefreshElements();

        this.gameBuilder.buildScore();
        this.gameBuilder.buildLives();
        this.gameBuilder.buildHeart();
        this.gameBuilder.buildLevel();

        this.gameBuilder.buildTime();
    }
}

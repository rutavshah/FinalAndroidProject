package com.example.game.Level3.Sound;

/**
 * The type Sound engineer.
 */
public class SoundEngineer {
    private SoundBuilder soundBuilder;

    /**
     * Instantiates a new Sound engineer.
     *
     * @param soundBuilder the sound builder
     */
    public SoundEngineer(SoundBuilder soundBuilder) {
        this.soundBuilder = soundBuilder;
    }

    /**
     * Construct sound.
     */
    public void constructSound() {
        soundBuilder.buildSuccessSound();
        soundBuilder.buildFailureSound();
        soundBuilder.buildWhooshSound();
        soundBuilder.buildBoostSound();
    }

    /**
     * Gets sound facade.
     *
     * @return the sound facade
     */
    public SoundFacade getSoundFacade() {
        return this.soundBuilder.getSoundFacade();
    }
}

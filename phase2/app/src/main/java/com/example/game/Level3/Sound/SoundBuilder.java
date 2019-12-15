package com.example.game.Level3.Sound;

import android.media.MediaPlayer;

/**
 * The Sound builder.
 */
public class SoundBuilder {
    private SoundFacade soundFacade;
    private MediaPlayer success;
    private MediaPlayer failure;
    private MediaPlayer whooshSound;
    private MediaPlayer boost;

    /**
     * Instantiates a new Sound builder.
     *
     * @param success the success sound
     * @param failure the failure sound
     * @param whoosh  the whoosh sound
     * @param boost   the boost sound
     */
    public SoundBuilder(MediaPlayer success, MediaPlayer failure, MediaPlayer whoosh, MediaPlayer boost) {
        this.success = success;
        this.failure = failure;
        this.whooshSound = whoosh;
        this.boost = boost;
        this.soundFacade = new SoundFacade();
    }

    /**
     * Build whoosh sound.
     */
    void buildWhooshSound() {
        soundFacade.setWhooshSound(this.whooshSound);
    }

    /**
     * Build failure sound.
     */
    void buildFailureSound() {
        soundFacade.setFailure(this.failure);
    }

    /**
     * Build success sound.
     */
    void buildSuccessSound() {
        soundFacade.setSuccess(this.success);
    }

    /**
     * Build boost sound.
     */
    void buildBoostSound(){
        soundFacade.setBoostSound(this.boost);
    }

    /**
     * Gets sound facade.
     *
     * @return the sound facade
     */
    SoundFacade getSoundFacade() {
        return this.soundFacade;
    }
}

package com.example.game.Level3.Sound;

import android.media.MediaPlayer;

/**
 * The type Sound facade.
 */
public class SoundFacade {
    /**
     * The Success sound.
     */
    public MediaPlayer success;
    /**
     * The Failure sound.
     */
    public MediaPlayer failure;
    /**
     * The Whoosh sound.
     */
    public MediaPlayer whooshSound;
    /**
     * The Boost sound.
     */
    public MediaPlayer boost;

    /**
     * Sets failure sound.
     *
     * @param failure the failure sound
     */
    void setFailure(MediaPlayer failure) {
        this.failure = failure;
    }

    /**
     * Sets whoosh sound.
     *
     * @param whooshSound the whoosh sound
     */
    void setWhooshSound(MediaPlayer whooshSound) {
        this.whooshSound = whooshSound;
    }

    /**
     * Sets success sound.
     *
     * @param success the success sound
     */
    void setSuccess(MediaPlayer success) {
        this.success = success;
    }

    /**
     * Sets boost sound.
     *
     * @param boost the boost sound
     */
    void setBoostSound(MediaPlayer boost) {
        this.boost = boost;
    }
}

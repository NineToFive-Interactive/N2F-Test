package com.libgdx.atb.test.rythm;

import com.libgdx.atb.test.BaseGame;


public class RhythmGame extends BaseGame {
    public void create() {
        super.create();
        setActiveScreen( new RhythmScreen() );
    }
}

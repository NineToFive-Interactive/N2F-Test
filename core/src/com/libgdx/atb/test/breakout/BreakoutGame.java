package com.libgdx.atb.test.breakout;

import com.libgdx.atb.test.BaseGame;

public class BreakoutGame extends BaseGame {

    public void create() {
        super.create();
        setActiveScreen( new LevelScreen() );
    }

}

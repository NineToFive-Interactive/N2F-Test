package com.libgdx.atb.test.turtle;

import com.libgdx.atb.test.BaseGame;

public class TurtleGameV7 extends BaseGame {
    @Override
    public void create() {
        super.create();

        setActiveScreen( new MenuScreen());
    }
}
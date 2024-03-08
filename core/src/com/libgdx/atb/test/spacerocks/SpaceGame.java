package com.libgdx.atb.test.spacerocks;

import com.libgdx.atb.test.BaseGame;

public class SpaceGame extends BaseGame {
    public void create(){
        super.create();
        setActiveScreen(new LevelScreen());
    }
}

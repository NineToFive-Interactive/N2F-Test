package com.libgdx.atb.test.dodger;

import com.libgdx.atb.test.BaseGame;

public class DodgerGame extends BaseGame {

    public void create() {
        super.create();
        setActiveScreen( new LevelScreen() );
    }

}

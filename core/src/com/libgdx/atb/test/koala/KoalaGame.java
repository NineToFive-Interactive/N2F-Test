package com.libgdx.atb.test.koala;

import com.libgdx.atb.test.BaseGame;

public class KoalaGame extends BaseGame {

    public void create() {
        super.create();
        setActiveScreen( new LevelScreen() );
    }

}

package com.libgdx.atb.test.puzzle;

import com.libgdx.atb.test.BaseGame;

public class PuzzleGame extends BaseGame {

    public void create() {
        super.create();
        setActiveScreen( new PuzzleLevelScreen() );
    }

}

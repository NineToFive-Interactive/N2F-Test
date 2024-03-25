package com.libgdx.atb.test.neondungeons;

import com.libgdx.atb.test.BaseGame;

public class DungeonGame extends BaseGame {
    public void create() {
        super.create();
        setActiveScreen( new NeonLevelScreen() );
    }
}

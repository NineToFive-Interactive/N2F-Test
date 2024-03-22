package com.libgdx.atb.test.quest;

import com.libgdx.atb.test.BaseGame;
public class QuestGame extends BaseGame {

    public void create() {
        super.create();
        setActiveScreen( new LevelScreen() );
    }

}

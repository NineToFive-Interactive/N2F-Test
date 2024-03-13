package com.libgdx.atb.test.homework;

import com.libgdx.atb.test.BaseGame;

public class HomeworkGame extends BaseGame {
    public void create() {
        super.create();
        setActiveScreen( new MenuScreen() );
    }
}

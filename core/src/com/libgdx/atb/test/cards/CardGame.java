package com.libgdx.atb.test.cards;

import com.libgdx.atb.test.BaseGame;

public class CardGame extends BaseGame {

    public void create() {
        super.create();
        setActiveScreen( new CardsLevelScreen() );
    }
}

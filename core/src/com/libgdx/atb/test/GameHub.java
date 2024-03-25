package com.libgdx.atb.test;


public class GameHub extends BaseGame {

    public void create() {
        super.create();
        setActiveScreen( new HubScreen() );
    }
}

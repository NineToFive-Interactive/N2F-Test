package com.libgdx.atb.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

public abstract class BaseGame extends Game {

    private static BaseGame game;

    public BaseGame() {
        game = this;
    }

    public void create() {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public static void setActiveScreen(BaseScreen baseScreen) {
        game.setScreen(baseScreen);
    }
}

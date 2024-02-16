package com.libgdx.atb.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DropletGame extends Game {

    public SpriteBatch batch;
    public BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(); // uses libGDX's default Arial font
        this.setScreen(new DropletMainMenuScreen(this));
    }
    public void render() {
        super.render(); // important!
    }
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}

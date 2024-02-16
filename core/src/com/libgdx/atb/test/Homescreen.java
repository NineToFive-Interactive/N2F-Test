package com.libgdx.atb.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Homescreen extends BaseGame{

    SpriteBatch batch;
    Texture img;

    public void create () {
        batch = new SpriteBatch();
        img = new Texture("NINE-TO-FIFE Interactive Logo.png");
    }

    public void render () {
        int windowWidth = Gdx.graphics.getWidth();
        int windowHeight = Gdx.graphics.getHeight();

        int imgWidth = img.getWidth();
        int imgHeigth = img.getHeight();

        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();
        batch.draw(img,  windowWidth/2f - imgWidth/2f, windowHeight/2f - imgHeigth/2f);
        batch.end();
    }

    public void dispose () {
        batch.dispose();
        img.dispose();
    }
}

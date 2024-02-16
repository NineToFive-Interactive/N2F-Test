package com.libgdx.atb.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

public class DropletMainMenuScreen implements Screen{

    final DropletGame game;
    OrthographicCamera camera;
    private Sprite logo;

    public DropletMainMenuScreen(final DropletGame game) {
        this.game = game;

        logo = new Sprite(new Texture(Gdx.files.internal("logo.png")));
        logo.setScale(0.2f);
        logo.setPosition((800 - logo.getWidth())/2, (480 - logo.getHeight())/2.2f);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        logo.draw(game.batch);
        game.font.draw(game.batch, "Welcome to DropletGame! ", 310, 150);
        game.font.draw(game.batch, "Tap anywhere to begin!", 320, 130);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new DropletGameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        logo.getTexture().dispose();
    }
}

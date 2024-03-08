package com.libgdx.atb.test;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.InputMultiplexer;

public abstract class BaseScreen implements Screen, InputProcessor {
    protected Stage mainStage;
    protected Stage uiStage;

    public BaseScreen() {
        mainStage = new Stage();
        uiStage = new Stage();

        initialize();
    }

    public abstract void initialize();
    public abstract void update(float deltaTime);
    public void render(float deltaTime) {
        uiStage.act(deltaTime);
        mainStage.act(deltaTime);

        update(deltaTime);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainStage.draw();
        uiStage.draw();
    }

    public void resize(int width, int height) {}
    public void pause() {}
    public void resume() {}
    public void dispose() {}
    public void show() {
        InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(uiStage);
        inputMultiplexer.addProcessor(mainStage);
    }
    public void hide() {
        InputMultiplexer inputMultiplexer = (InputMultiplexer)Gdx.input.getInputProcessor();
        inputMultiplexer.removeProcessor(this);
        inputMultiplexer.removeProcessor(uiStage);
        inputMultiplexer.removeProcessor(mainStage);
    }
    public boolean keyDown(int keycode)
    { return false; }
    public boolean keyUp(int keycode)
    { return false; }
    public boolean keyTyped(char c)
    { return false; }
    public boolean mouseMoved(int screenX, int screenY)
    { return false; }
    public boolean scrolled(float amountX, float amountY)
    { return false; }
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    { return false; }
    public boolean touchDragged(int screenX, int screenY, int pointer)
    { return false; }
    public boolean touchCancelled(int screenX, int screenY, int pointer, int moin)
    { return false; }
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    { return false; }
}

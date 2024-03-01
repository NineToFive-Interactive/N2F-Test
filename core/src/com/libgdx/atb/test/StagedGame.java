package com.libgdx.atb.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class StagedGame extends Game {
    protected Stage mainStage;

    public abstract void initialize();
    public abstract void update(float delta);

    @Override
    public void create() {
        mainStage = new Stage();
        initialize();
    }

    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        mainStage.act(delta);
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainStage.draw();
    }
}

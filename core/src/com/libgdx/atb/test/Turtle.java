package com.libgdx.atb.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Turtle extends GraphicActor {

    public Turtle() {
        super();
    }

    public void act(float delta) {
        super.act(delta);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.moveBy(-1,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.moveBy(1,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            this.moveBy(0,1);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            this.moveBy(0,-1);
        }
    }
}

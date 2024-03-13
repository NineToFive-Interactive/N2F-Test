package com.libgdx.atb.test.rythm;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;

public class FallingBox extends BaseActor {
    public FallingBox(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadTexture("RythmTapper/V1/box.png");
        setScale(0.75f, 0.75f);
    }

    public void act(float deltaTime) {
        super.act(deltaTime);
        applyPhysics(deltaTime);
    }
}

package com.libgdx.atb.test.spacerocks;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.libgdx.atb.test.BaseActor;

public class Laser extends BaseActor {
    public Laser(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadTexture("SpaceRocks/V1/laser.png");

        addAction(Actions.delay(1));
        addAction(Actions.after(Actions.fadeOut(0.5f)));
        addAction(Actions.after(Actions.removeActor()));

        setSpeed(400);
        setMaxSpeed(400);
        setDeceleration(0);
    }

    public void act(float deltaTime) {
        super.act(deltaTime);

        applyPhysics(deltaTime);
    }
}

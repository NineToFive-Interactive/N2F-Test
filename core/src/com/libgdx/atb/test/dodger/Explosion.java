package com.libgdx.atb.test.dodger;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;

public class Explosion extends BaseActor {
    public Explosion(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadAnimationFromSheet("PlaneDodger/V1/explosion.png", 6,6, 0.02f, false);
    }

    public void act(float deltaTime) {
        super.act(deltaTime);

        if (isAnimationFinished()) remove();
    }
}

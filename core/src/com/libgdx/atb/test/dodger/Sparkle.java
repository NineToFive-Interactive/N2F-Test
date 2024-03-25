package com.libgdx.atb.test.dodger;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;

public class Sparkle extends BaseActor {
    public Sparkle(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadAnimationFromSheet("PlaneDodger/V1/sparkle.png", 8,8, 0.02f, false);
    }

    public void act(float deltaTime) {
        super.act(deltaTime);

        if (isAnimationFinished()) remove();
    }
}

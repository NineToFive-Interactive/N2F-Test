package com.libgdx.atb.test.dodger;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;

public class Sky extends BaseActor {
    public Sky(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadTexture("PlaneDodger/V1/sky.png");

        setSpeed(25);
        setMotionAngle(180);
    }

    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);

        // if moved completely past left edge of screen:
        // shift right, past other instance.
        if ( getX() + getWidth() < 0 ) {
            moveBy( 2 * getWidth(), 0 );
        }
    }
}

package com.libgdx.atb.test.dodger;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.libgdx.atb.test.BaseActor;

public class Star extends BaseActor {
    public Star(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadTexture("PlaneDodger/V1/star.png");

        Action pulse = Actions.sequence(
                Actions.scaleTo(1.2f,1.2f, 0.5f),
                Actions.scaleTo(1.0f,1.0f, 0.5f) );
        addAction( Actions.forever(pulse) );

        setSpeed(100);
        setMotionAngle(180);
    }

    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);

        // remove after moving past left edge of screen
        if ( getX() + getWidth() < 0 ) remove();
    }
}

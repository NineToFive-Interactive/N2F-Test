package com.libgdx.atb.test.dodger;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;

public class Enemy extends BaseActor {
    public Enemy(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        String[] filenames = {
                "PlaneDodger/V1/planeRed0.png",
                "PlaneDodger/V1/planeRed1.png",
                "PlaneDodger/V1/planeRed2.png",
                "PlaneDodger/V1/planeRed1.png"
        };

        loadAnimationFromFiles(filenames, 0.1f, true);

        setSpeed(100);
        setMotionAngle(180);
        setBoundaryPolygon(8);
    }

    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);
    }
}

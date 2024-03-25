package com.libgdx.atb.test.dodger;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;

public class Plane extends BaseActor {
    public Plane(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        String[] filenames = {
                "PlaneDodger/V1/planeGreen0.png",
                "PlaneDodger/V1/planeGreen1.png",
                "PlaneDodger/V1/planeGreen2.png",
                "PlaneDodger/V1/planeGreen1.png"
        };

        loadAnimationFromFiles(filenames, 0.1f, true);

        setMaxSpeed(800);
        setBoundaryPolygon(8);
    }

    public void act(float dt) {
        super.act(dt);

        // simulate force of gravity
        setAcceleration(800);
        accelerateAtAngle(270);
        applyPhysics(dt);

        // stop plane from passing through the ground
        for (BaseActor ground : BaseActor.getList(this.getStage(), Ground.class)) {
            if ( this.overlaps(ground) ) {
                setSpeed(0);
                preventOverlap(ground);
            }
        }

        // stop plane from moving past top of screen
        if ( getY() + getHeight() > getWorldBounds().height ) {
            setSpeed(0);
            boundToWorld();
        }
    }

    public void boost() {
        setSpeed(300);
        setMotionAngle(90);
    }
}

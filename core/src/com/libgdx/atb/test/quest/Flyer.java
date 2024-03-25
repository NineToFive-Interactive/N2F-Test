package com.libgdx.atb.test.quest;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;
import com.badlogic.gdx.math.MathUtils;

public class Flyer extends BaseActor {
    public Flyer(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadAnimationFromSheet( "TreasureQuest/V1/enemy-flyer.png", 1, 4, 0.05f, true);
        setSize(48,48);
        setBoundaryPolygon(6);

        setSpeed( MathUtils.random(50,80) );
        setMotionAngle( MathUtils.random(0,360) );
    }

    public void act(float dt) {
        super.act(dt);

        if ( MathUtils.random(1,120) == 1 ) setMotionAngle( MathUtils.random(0,360) );

        applyPhysics(dt);
        boundToWorld();
    }
}

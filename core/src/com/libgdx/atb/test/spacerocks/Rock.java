package com.libgdx.atb.test.spacerocks;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AddAction;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.libgdx.atb.test.BaseActor;

public class Rock extends BaseActor {
    public Rock(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadTexture("SpaceRocks/V1/rock.png");

        float random = MathUtils.random(30);

        addAction(Actions.forever(Actions.rotateBy(30+random, 1)));

        setSpeed(50+random);
        setMaxSpeed(50+random);
        setDeceleration(0);

        setMotionAngle(MathUtils.random(360));
    }

    public void act(float deltaTime) {
        super.act(deltaTime);
        applyPhysics(deltaTime);
        wrapAroundWorld();
    }
}

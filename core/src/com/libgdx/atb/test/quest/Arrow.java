package com.libgdx.atb.test.quest;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;

public class Arrow extends BaseActor {
    public Arrow(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadTexture("TreasureQuest/V1/arrow.png");
        setSpeed(400);
    }

    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);
    }
}

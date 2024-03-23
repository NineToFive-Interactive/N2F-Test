package com.libgdx.atb.test.quest;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Smoke extends BaseActor {
    public Smoke(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadTexture("TreasureQuest/V1/smoke.png");
        addAction( Actions.fadeOut(0.5f) );
        addAction( Actions.after( Actions.removeActor() ) );
    }
}

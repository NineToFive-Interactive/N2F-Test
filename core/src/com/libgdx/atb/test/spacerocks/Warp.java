package com.libgdx.atb.test.spacerocks;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.libgdx.atb.test.BaseActor;

public class Warp extends BaseActor {
    public Warp(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadAnimationFromSheet("SpaceRocks/V1/warp.png",4,8,0.05f,true);

        addAction(Actions.delay(1));
        addAction(Actions.after(Actions.fadeOut(0.0f)));
        addAction(Actions.after(Actions.removeActor()));
    }
}

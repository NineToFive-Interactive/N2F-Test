package com.libgdx.atb.test.spacerocks;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.libgdx.atb.test.BaseActor;

public class Shield extends BaseActor {
    public Shield(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadTexture("SpaceRocks/V1/shields.png");

        Action pulse = Actions.sequence(
                Actions.scaleTo(1.05f,1.05f,1),
                Actions.scaleTo(0.95f,0.95f,1)
        );

        addAction(Actions.forever(pulse));
    }
}

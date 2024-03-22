package com.libgdx.atb.test.koala;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Key extends BaseActor {
    public Key(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadTexture("JumpingJack/V1/items/key.png");

        rotateBy(10);

        Action tilt = Actions.sequence(
                Actions.rotateBy(-20, 0.5f),
                Actions.rotateBy(20, 0.5f)
        );

        addAction( Actions.forever(tilt) );
    }
}

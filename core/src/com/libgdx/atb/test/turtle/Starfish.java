package com.libgdx.atb.test.turtle;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.libgdx.atb.test.BaseActor;

public class Starfish extends BaseActor {
    public boolean collected;


    public Starfish(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadTexture("TurtleGame/Selfmade Textures/Starfish.png");

        setBoundaryPolygon(8);

        Action scaleUp = Actions.scaleTo(1.1f,1.1f,1);
        Action scaleDown = Actions.scaleTo(1,1,1);
        Action scalingEffect = Actions.sequence(scaleUp, scaleDown);

        this.addAction(Actions.forever(scalingEffect));

        collected = false;
    }
}

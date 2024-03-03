package com.libgdx.atb.test.turtle;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.libgdx.atb.test.BaseActor;

public class Starfish extends BaseActor {
    public Starfish(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadTexture("TurtleGame/Selfmade Textures/Starfish.png");

        Action spin = Actions.rotateBy(30,1);
        this.addAction(Actions.forever(spin));
    }
}

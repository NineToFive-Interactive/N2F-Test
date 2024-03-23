package com.libgdx.atb.test.quest;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;

public class Solid extends BaseActor {
    public Solid(float posX, float posY, float width, float height, Stage stage) {
        super(posX, posY, stage);

        setSize(width, height);
        setBoundaryRectangle();
    }
}

package com.libgdx.atb.test;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class DropTargetActor extends BaseActor{

    private boolean targetable;

    public DropTargetActor(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        targetable = true;
    }

    public void setTargetable(boolean t) {
        targetable = t;
    }

    public boolean isTargetable() {
        return targetable;
    }
}

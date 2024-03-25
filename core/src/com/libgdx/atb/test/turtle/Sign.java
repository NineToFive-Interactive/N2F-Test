package com.libgdx.atb.test.turtle;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;

public class Sign extends BaseActor {

    private String text;
    private boolean viewing;

    public Sign(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadTexture("TurtleGame/V5/sign.png");
        text = " ";
        viewing = false;
    }

    public void setText(String t) {
        text = t;
    }

    public String getText() {
        return text;
    }

    public void setViewing(boolean v) {
        viewing = v;
    }

    public boolean isViewing() {
        return viewing;
    }
}

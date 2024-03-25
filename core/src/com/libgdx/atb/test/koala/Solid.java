package com.libgdx.atb.test.koala;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;

public class Solid extends BaseActor {

    private boolean enabled;

    public Solid(float posX, float posY, float width, float height, Stage stage) {
        super(posX, posY, stage);

        setSize(width, height);
        setBoundaryRectangle();
        enabled = true;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

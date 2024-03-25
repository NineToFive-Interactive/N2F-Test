package com.libgdx.atb.test.turtle;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;

public class Rock extends BaseActor {

    public Rock(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadTexture("TurtleGame/V2/rock.png");

        setBoundaryPolygon(8);
    }
}

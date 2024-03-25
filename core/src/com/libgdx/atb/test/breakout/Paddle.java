package com.libgdx.atb.test.breakout;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;

public class Paddle extends BaseActor {
    public Paddle(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadTexture("RectangleDestroyer/V1/paddle.png");
    }
}

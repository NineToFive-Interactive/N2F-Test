package com.libgdx.atb.test.breakout;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.Color;
import com.libgdx.atb.test.BaseActor;

public class Wall extends BaseActor {
    public Wall(float posX, float posY, Stage stage, float width, float height) {
        super(posX, posY, stage);

        loadTexture("RectangleDestroyer/V1/white-square.png");
        setSize(width, height);
        setColor(Color.GRAY);
        setBoundaryRectangle();
    }
}

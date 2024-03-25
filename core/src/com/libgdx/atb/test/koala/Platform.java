package com.libgdx.atb.test.koala;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Platform extends Solid{
    public Platform(float posX, float posY,Stage stage) {
        super(posX, posY, 32, 16, stage);

        loadTexture("JumpingJack/V1/items/platform.png");
    }
}

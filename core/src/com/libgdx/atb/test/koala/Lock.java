package com.libgdx.atb.test.koala;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Lock extends Solid{
    public Lock(float posX, float posY, Stage stage) {
        super(posX, posY, 32,32, stage);

        loadTexture("JumpingJack/V1/items/lock.png");
    }
}

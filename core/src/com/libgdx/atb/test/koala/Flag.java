package com.libgdx.atb.test.koala;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;

public class Flag extends BaseActor {
    public Flag(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadAnimationFromSheet("JumpingJack/V1/items/flag.png", 1, 2, 0.2f, true);
    }
}

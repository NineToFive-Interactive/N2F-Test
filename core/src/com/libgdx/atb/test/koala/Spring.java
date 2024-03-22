package com.libgdx.atb.test.koala;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;

public class Spring extends BaseActor {
    public Spring(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadAnimationFromSheet("JumpingJack/V1/items/springboard.png", 1, 3, 0.2f, true);
    }
}

package com.libgdx.atb.test.koala;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;

public class Coin extends BaseActor {
    public Coin(float posX, float posY, Stage stage) {
        super(posX, posY, stage);
        loadAnimationFromSheet("JumpingJack/V1/items/coin.png",1,6,0.1f,true);
    }
}

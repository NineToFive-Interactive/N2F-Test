package com.libgdx.atb.test.turtle;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;

public class SwirlEffect extends BaseActor {
    public SwirlEffect(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadAnimationFromSheet("TurtleGame/V2/whirlpool.png", 2,5,0.1f,false);
    }

    public void act(float dt) {
        super.act(dt);

        if ( isAnimationFinished() )
            remove();
    }
}

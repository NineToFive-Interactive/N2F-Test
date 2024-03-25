package com.libgdx.atb.test.homework;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Kelsoe extends BaseActor {
    public Animation normal;
    public Animation sad;
    public Animation lookLeft;
    public Animation lookRight;
    public Kelsoe(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        normal = loadTexture("MissingHomework/V1/kelsoe-normal.png");
        sad = loadTexture("MissingHomework/V1/kelsoe-sad.png");
        lookLeft = loadTexture("MissingHomework/V1/kelsoe-look-left.png");
        lookRight = loadTexture("MissingHomework/V1/kelsoe-look-right.png");
    }
}

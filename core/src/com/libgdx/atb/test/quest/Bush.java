package com.libgdx.atb.test.quest;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Bush extends Solid{
    public Bush(float posX, float posY, Stage stage) {
        super(posX, posY, 32, 32, stage);

        loadTexture("TreasureQuest/V1/bush.png");
        setBoundaryPolygon(8);
    }
}

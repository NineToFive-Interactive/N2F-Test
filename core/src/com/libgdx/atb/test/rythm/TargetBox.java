package com.libgdx.atb.test.rythm;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.libgdx.atb.test.BaseGame;

public class TargetBox extends BaseActor {
    public TargetBox(float posX, float posY, Stage stage, String letter, Color color) {
        super(posX, posY, stage);

        loadTexture("RythmTapper/V1/box.png");
        setSize(64,64);

        // add a centered label containing letter with given color
        Label letterLabel = new Label(letter, BaseGame.labelStyle);
        letterLabel.setSize(64,64);
        letterLabel.setAlignment(Align.center);
        letterLabel.setColor( color );

        this.addActor(letterLabel);
    }
}

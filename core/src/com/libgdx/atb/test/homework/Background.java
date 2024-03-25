package com.libgdx.atb.test.homework;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;

public class Background extends BaseActor {
    public Animation hallway;
    public Animation classroom;
    public Animation scienceLab;
    public Animation library;
    public Background(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        hallway = loadTexture("MissingHomework/V1/bg-hallway.jpg");
        classroom = loadTexture("MissingHomework/V1/bg-classroom.jpg");
        scienceLab = loadTexture("MissingHomework/V1/bg-science-lab.jpg");
        library = loadTexture("MissingHomework/V1/bg-library.jpg");

        setSize(800,600);
    }
}

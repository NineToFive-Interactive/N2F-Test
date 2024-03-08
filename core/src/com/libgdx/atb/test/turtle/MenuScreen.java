package com.libgdx.atb.test.turtle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.libgdx.atb.test.BaseActor;
import com.libgdx.atb.test.BaseScreen;

public class MenuScreen extends BaseScreen {
    public MenuScreen() {
        super();
    }
    @Override
    public void initialize() {
        BaseActor ocean = new BaseActor(0,0, mainStage);
        ocean.loadTexture("TurtleGame/V2/water.jpg");
        ocean.setSize(512,512);

        BaseActor title = new BaseActor(0,0, mainStage);
        title.loadTexture("TurtleGame/V2/starfish-collector.png");
        title.centerAtPosition(256,310);

        BaseActor start = new BaseActor(0,0, mainStage);
        start.loadTexture("TurtleGame/V2/message-start.png");
        start.centerAtPosition(256,200);
    }

    @Override
    public void update(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            TurtleGameV4.setActiveScreen( new TurtleLevelScreen() );
    }
}

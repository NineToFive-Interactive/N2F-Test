package com.libgdx.atb.test.turtle;

import com.libgdx.atb.test.BaseActor;
import com.libgdx.atb.test.StagedGame;

public class TurtleGameV3 extends StagedGame {
    private Turtle turtle;
    private Starfish starfish;
    private BaseActor ocean;
    public void initialize() {
        ocean = new BaseActor(0,0, mainStage);
        ocean.loadTexture( "TurtleGame/Selfmade Textures/OceanTexture.png" );
        ocean.setSize(1024,1024);
        starfish = new Starfish(380,380, mainStage);
        turtle = new Turtle(20,20, mainStage);
    }
    public void update(float dt) {

    }
}

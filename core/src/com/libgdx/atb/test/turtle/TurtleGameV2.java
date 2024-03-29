package com.libgdx.atb.test.turtle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.libgdx.atb.test.GraphicActor;
import com.libgdx.atb.test.StagedGame;

public class TurtleGameV2 extends StagedGame {

    private Turtle turtle;
    private GraphicActor starfish;
    private GraphicActor ocean;
    private GraphicActor winMessage;

    private boolean win;

    @Override
    public void initialize() {
        ocean = new GraphicActor();
        ocean.setTexture( new Texture( Gdx.files.internal("TurtleGame/Selfmade Textures/OceanTexture.png") ) );
        mainStage.addActor(ocean);

        starfish = new GraphicActor();
        starfish.setTexture( new Texture(Gdx.files.internal("TurtleGame/Selfmade Textures/StarfishTexture.png")) );
        starfish.setPosition( 380,380 );
        mainStage.addActor( starfish );

        turtle = new Turtle(20, 20, mainStage);
        turtle.setPosition( 20,20 );
        mainStage.addActor( turtle );

        winMessage = new GraphicActor();
        winMessage.setTexture( new Texture(Gdx.files.internal("TurtleGame/Selfmade Textures/WinTexture.png")) );
        winMessage.setVisible( false );
        mainStage.addActor( winMessage );

        win = false;
    }

    @Override
    public void update(float delta) {

    }
}

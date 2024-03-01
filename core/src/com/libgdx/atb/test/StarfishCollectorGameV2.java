package com.libgdx.atb.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class StarfishCollectorGameV2 extends StagedGame {

    private Turtle turtle;
    private GraphicActor starfish;
    private GraphicActor ocean;
    private GraphicActor winMessage;

    private boolean win;

    @Override
    public void initialize() {
        ocean = new GraphicActor();
        ocean.setTexture( new Texture( Gdx.files.internal("TurtleGame/OceanTexture.jpg") ) );
        mainStage.addActor(ocean);

        starfish = new GraphicActor();
        starfish.setTexture( new Texture(Gdx.files.internal("TurtleGame/StarfishTexture.png")) );
        starfish.setPosition( 380,380 );
        mainStage.addActor( starfish );

        turtle = new Turtle();
        turtle.setTexture( new Texture(Gdx.files.internal("TurtleGame/TurtleTexture.png")) );
        turtle.setPosition( 20,20 );
        mainStage.addActor( turtle );

        winMessage = new GraphicActor();
        winMessage.setTexture( new Texture(Gdx.files.internal("TurtleGame/WinTexture.png")) );
        winMessage.setVisible( false );
        mainStage.addActor( winMessage );

        win = false;
    }

    @Override
    public void update(float delta) {
        if (turtle.overlaps(starfish))
        {
            starfish.remove();
            winMessage.setVisible(true);
        }
    }
}

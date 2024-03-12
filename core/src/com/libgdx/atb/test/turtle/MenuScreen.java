package com.libgdx.atb.test.turtle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.libgdx.atb.test.BaseActor;
import com.libgdx.atb.test.BaseGame;
import com.libgdx.atb.test.BaseScreen;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.libgdx.atb.test.spacerocks.LevelScreen;

public class MenuScreen extends BaseScreen {
    public MenuScreen() {
        super();
    }
    @Override
    public void initialize() {
        BaseActor ocean = new BaseActor(0,0, mainStage);
        ocean.loadTexture("TurtleGame/V2/water.jpg");
        ocean.setSize(800,600);

        BaseActor title = new BaseActor(0,0, mainStage);
        title.loadTexture("TurtleGame/V2/starfish-collector.png");
        title.centerAtPosition(400,360);

        TextButton startButton = new TextButton( "Start", BaseGame.textButtonStyle );
        startButton.setPosition(150,150);
        uiStage.addActor(startButton);
        startButton.addListener(
                (Event e) -> {
                    if ( !(e instanceof InputEvent) ||
                            !((InputEvent)e).getType().equals(Type.touchDown) )
                        return false;
                    TurtleGameV5.setActiveScreen( new TurtleLevelScreen() );
                    return false;
                }
        );

        TextButton quitButton = new TextButton( "Quit", BaseGame.textButtonStyle );
        quitButton.setPosition(500,150);
        uiStage.addActor(quitButton);
        quitButton.addListener(
                (Event e) -> {
                    if ( !(e instanceof InputEvent) ||
                            !((InputEvent)e).getType().equals(Type.touchDown) )
                        return false;
                    Gdx.app.exit();
                    return false;
                }
        );
    }

    @Override
    public void update(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            TurtleGameV5.setActiveScreen( new TurtleLevelScreen() );
    }

    public boolean keyDown(int keyCode)
    {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER))
            TurtleGameV5.setActiveScreen( new TurtleLevelScreen() );
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
        return false;
    }
}

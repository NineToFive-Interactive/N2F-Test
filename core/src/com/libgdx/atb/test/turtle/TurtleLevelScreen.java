package com.libgdx.atb.test.turtle;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.libgdx.atb.test.BaseActor;
import com.libgdx.atb.test.BaseGame;
import com.libgdx.atb.test.BaseScreen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.libgdx.atb.test.spacerocks.LevelScreen;

public class TurtleLevelScreen extends BaseScreen {

    private Turtle turtle;
    private boolean win;
    private Label starfishLabel;

    public TurtleLevelScreen() {
        super();
    }

    @Override
    public void initialize() {
        BaseActor ocean = new BaseActor(0,0, mainStage);
        ocean.loadTexture( "TurtleGame/V5/water-border.jpg" );
        ocean.setSize(1024,1024);
        BaseActor.setWorldBounds(ocean);

        new Starfish(300,100, mainStage);
        new Starfish(250,600, mainStage);
        new Starfish(800,800, mainStage);
        new Starfish(450,450, mainStage);

        new Rock(150, 600, mainStage);
        new Rock(350, 300, mainStage);
        new Rock(550, 700, mainStage);
        new Rock(700, 300, mainStage);

        turtle = new Turtle(20,20, mainStage);

        win = false;

        starfishLabel = new Label("Starfish Left:", BaseGame.labelStyle);
        starfishLabel.setColor( Color.CYAN );
        starfishLabel.setPosition( 20, 520 );
        uiStage.addActor(starfishLabel);

        ButtonStyle buttonStyle = new ButtonStyle();

        Texture buttonTexture = new Texture( Gdx.files.internal("TurtleGame/V5/undo.png") );
        TextureRegion buttonRegion = new TextureRegion(buttonTexture);
        buttonStyle.up = new TextureRegionDrawable( buttonRegion );

        Button restartButton = new Button( buttonStyle );
        restartButton.setColor( Color.CYAN );
        restartButton.setPosition(720,520);
        uiStage.addActor(restartButton);

        restartButton.addListener(
                (Event e) -> {
                    if ( !(e instanceof InputEvent) ||
                            !((InputEvent)e).getType().equals(Type.touchDown) )
                        return false;
                    TurtleGameV5.setActiveScreen( new TurtleLevelScreen() );
                    return false;
                }
        );
    }

    @Override
    public void update(float deltaTime) {
        for (BaseActor rockActor : BaseActor.getList(mainStage, Rock.class)) {
            turtle.preventOverlap(rockActor);
        }

        for (Starfish starfishActor : BaseActor.getList(mainStage, Starfish.class)) {
            if ( turtle.overlaps(starfishActor) && !starfishActor.collected ) {
                starfishActor.collected = true;

                starfishActor.clearActions();
                starfishActor.addAction( Actions.fadeOut(1) );
                starfishActor.addAction( Actions.after( Actions.removeActor() ) );

                SwirlEffect whirl = new SwirlEffect(0,0, mainStage);
                whirl.centerAtActor(starfishActor);
                whirl.setOpacity(0.25f);
            }
        }

        if ( BaseActor.count(mainStage, Starfish.class) == 0 && !win ) {
            win = true;
            BaseActor youWinMessage = new BaseActor(0,0,uiStage);
            youWinMessage.loadTexture("TurtleGame/V5/you-win.png");
            // youWinMessage.setSize(800,600);
            youWinMessage.centerAtPosition(400,300);
            youWinMessage.setOpacity(0);
            youWinMessage.addAction( Actions.delay(0) );
            youWinMessage.addAction( Actions.after( Actions.fadeIn(1) ) );
        }

        starfishLabel.setText("Starfish Left: " + BaseActor.count(mainStage, Starfish.class));
    }
}

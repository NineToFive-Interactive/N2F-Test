package com.libgdx.atb.test.turtle;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.libgdx.atb.test.BaseActor;
import com.libgdx.atb.test.BaseScreen;

public class TurtleLevelScreen extends BaseScreen {

    private Turtle turtle;
    private boolean win;

    public TurtleLevelScreen() {
        super();
    }

    @Override
    public void initialize() {
        BaseActor ocean = new BaseActor(0,0, mainStage);
        ocean.loadTexture( "TurtleGame/V2/water-border.jpg" );
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
            youWinMessage.loadTexture("TurtleGame/Selfmade Textures/WinTexture.png");
            youWinMessage.setSize(512,512);
            youWinMessage.centerAtPosition(256,256);
            youWinMessage.setOpacity(0);
            youWinMessage.addAction( Actions.delay(0) );
            youWinMessage.addAction( Actions.after( Actions.fadeIn(1) ) );
        }
    }
}

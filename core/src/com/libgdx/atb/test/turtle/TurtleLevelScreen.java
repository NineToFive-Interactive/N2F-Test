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
import com.libgdx.atb.test.DialogBox;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.libgdx.atb.test.spacerocks.LevelScreen;

public class TurtleLevelScreen extends BaseScreen {

    private Turtle turtle;
    private boolean win;
    private Label starfishLabel;
    private DialogBox dialogBox;
    private float audioVolume;
    private Sound waterDrop;
    private Music instrumental;
    private Music oceanSurf;

    public TurtleLevelScreen(float audioVolume) {
        super();
        this.audioVolume = audioVolume;
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

        ButtonStyle buttonStyle = new ButtonStyle();
        Texture buttonTexture = new Texture( Gdx.files.internal("TurtleGame/V5/undo.png") );
        TextureRegion buttonRegion = new TextureRegion(buttonTexture);
        buttonStyle.up = new TextureRegionDrawable( buttonRegion );
        Button restartButton = new Button( buttonStyle );
        restartButton.setColor( Color.CYAN );

        restartButton.addListener(
                (Event e) -> {
                    if ( !isTouchDownEvent(e) )
                        return false;

                    instrumental.dispose();
                    oceanSurf.dispose();

                    TurtleGameV6.setActiveScreen( new TurtleLevelScreen(audioVolume) );

                    return true;
                }
        );

        ButtonStyle buttonStyle2 = new ButtonStyle();

        Texture buttonTex2 = new Texture( Gdx.files.internal("TurtleGame/V4/audio.png") );
        TextureRegion buttonRegion2 = new TextureRegion(buttonTex2);
        buttonStyle2.up = new TextureRegionDrawable( buttonRegion2 );

        Button muteButton = new Button( buttonStyle2 );
        muteButton.setColor( Color.CYAN );

        muteButton.addListener(
                (Event e) -> {
                    if ( !isTouchDownEvent(e) )
                        return false;

                    audioVolume = 1 - audioVolume;

                    instrumental.setVolume( audioVolume );
                    oceanSurf.setVolume( audioVolume );

                    return true;
                }
        );

        Sign sign1 = new Sign(20,400, mainStage);
        sign1.setText("West Starfish Bay");

        Sign sign2 = new Sign(600,300, mainStage);
        sign2.setText("East Starfish Bay");

        dialogBox = new DialogBox(0,0, uiStage);
        dialogBox.setBackgroundColor( Color.TAN );
        dialogBox.setFontColor( Color.BROWN );
        dialogBox.setDialogSize(600, 100);
        dialogBox.setFontScale(0.80f);
        dialogBox.alignCenter();
        dialogBox.setVisible(false);

        uiTable.pad(20);
        uiTable.add(starfishLabel).top();
        uiTable.add().expandX().expandY();
        uiTable.add(muteButton).top();
        uiTable.add(restartButton).top();
        uiTable.row();
        uiTable.add(dialogBox).colspan(4);

        waterDrop = Gdx.audio.newSound(Gdx.files.internal("TurtleGame/V4/Water_Drop.ogg"));
        instrumental = Gdx.audio.newMusic(Gdx.files.internal("TurtleGame/V4/Master_of_the_Feast.ogg"));
        oceanSurf = Gdx.audio.newMusic(Gdx.files.internal("TurtleGame/V4/Ocean_Waves.ogg"));

        instrumental.setLooping(true);
        instrumental.setVolume(audioVolume);
        instrumental.play();

        oceanSurf.setLooping(true);
        oceanSurf.setVolume(audioVolume);
        oceanSurf.play();
    }

    @Override
    public void update(float deltaTime) {
        for (BaseActor rockActor : BaseActor.getList(mainStage, Rock.class)) {
            turtle.preventOverlap(rockActor);
        }

        for (Starfish starfishActor : BaseActor.getList(mainStage, Starfish.class)) {
            if ( turtle.overlaps(starfishActor) && !starfishActor.collected ) {
                starfishActor.collected = true;
                waterDrop.play(audioVolume);

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

        for ( BaseActor signActor : BaseActor.getList(mainStage, Sign.class) ) {
            Sign sign = (Sign) signActor;

            turtle.preventOverlap(sign);

            boolean nearby = turtle.isWithinDistance(4, sign);

            if ( nearby && !sign.isViewing() ) {
                dialogBox.setText( sign.getText() );
                dialogBox.setVisible( true );
                sign.setViewing( true );
            }

            if (sign.isViewing() && !nearby) {
                dialogBox.setText( " " );
                dialogBox.setVisible( false );
                sign.setViewing( false );
            }
        }
    }
}

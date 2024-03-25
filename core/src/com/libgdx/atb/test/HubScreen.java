package com.libgdx.atb.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.libgdx.atb.test.breakout.BreakoutGame;
import com.libgdx.atb.test.breakout.BreakoutLevelScreen;
import com.libgdx.atb.test.cards.CardGame;
import com.libgdx.atb.test.cards.CardsLevelScreen;
import com.libgdx.atb.test.dodger.DodgerGame;
import com.libgdx.atb.test.dodger.DodgerLevelScreen;
import com.libgdx.atb.test.droplet.DropletGame;
import com.libgdx.atb.test.homework.HomeworkGame;
import com.libgdx.atb.test.homework.HomeworkMenuScreen;
import com.libgdx.atb.test.koala.KoalaGame;
import com.libgdx.atb.test.koala.KoalaLevelScreen;
import com.libgdx.atb.test.quest.QuestGame;
import com.libgdx.atb.test.quest.QuestLevelScreen;
import com.libgdx.atb.test.spacerocks.SpaceGame;
import com.libgdx.atb.test.spacerocks.SpaceLevelScreen;
import com.libgdx.atb.test.turtle.TurtleStoryScreen;
import com.libgdx.atb.test.turtle.TurtleGameV7;

public class HubScreen extends BaseScreen{

    public HubScreen() {
        super();
    }

    @Override
    public void initialize() {
        BaseActor ocean = new BaseActor(0,0, mainStage);
        ocean.loadTexture("TurtleGame/V2/water.jpg");
        ocean.setSize(800,600);

        TextButton turtleButton = new TextButton( "Turtle Game", BaseGame.textButtonStyle );
        uiStage.addActor(turtleButton);
        turtleButton.addListener(
                (Event e) -> {
                    if ( !(e instanceof InputEvent) ||
                            !((InputEvent)e).getType().equals(InputEvent.Type.touchDown) )
                        return false;
                    TurtleGameV7.setActiveScreen( new TurtleStoryScreen() );
                    return false;
                }
        );

        TextButton breakoutButton = new TextButton( "Breakout Game", BaseGame.textButtonStyle );
        uiStage.addActor(breakoutButton);
        breakoutButton.addListener(
                (Event e) -> {
                    if ( !(e instanceof InputEvent) ||
                            !((InputEvent)e).getType().equals(InputEvent.Type.touchDown) )
                        return false;
                    BreakoutGame.setActiveScreen( new BreakoutLevelScreen() );
                    return false;
                }
        );

        TextButton cardsButton = new TextButton( "Cards Game", BaseGame.textButtonStyle );
        uiStage.addActor(cardsButton);
        cardsButton.addListener(
                (Event e) -> {
                    if ( !(e instanceof InputEvent) ||
                            !((InputEvent)e).getType().equals(InputEvent.Type.touchDown) )
                        return false;
                    CardGame.setActiveScreen( new CardsLevelScreen() );
                    return false;
                }
        );

        TextButton dodgerButton = new TextButton( "Plane Dodger Game", BaseGame.textButtonStyle );
        uiStage.addActor(dodgerButton);
        dodgerButton.addListener(
                (Event e) -> {
                    if ( !(e instanceof InputEvent) ||
                            !((InputEvent)e).getType().equals(InputEvent.Type.touchDown) )
                        return false;
                    DodgerGame.setActiveScreen( new DodgerLevelScreen() );
                    return false;
                }
        );

        TextButton homeworkButton = new TextButton( "Homework Game", BaseGame.textButtonStyle );
        uiStage.addActor(homeworkButton);
        homeworkButton.addListener(
                (Event e) -> {
                    if ( !(e instanceof InputEvent) ||
                            !((InputEvent)e).getType().equals(InputEvent.Type.touchDown) )
                        return false;
                    HomeworkGame.setActiveScreen( new HomeworkMenuScreen() );
                    return false;
                }
        );

        TextButton koalaButton = new TextButton( "Koala Game", BaseGame.textButtonStyle );
        uiStage.addActor(koalaButton);
        koalaButton.addListener(
                (Event e) -> {
                    if ( !(e instanceof InputEvent) ||
                            !((InputEvent)e).getType().equals(InputEvent.Type.touchDown) )
                        return false;
                    KoalaGame.setActiveScreen( new KoalaLevelScreen() );
                    return false;
                }
        );

        TextButton questButton = new TextButton( "Quest Game", BaseGame.textButtonStyle );
        uiStage.addActor(questButton);
        questButton.addListener(
                (Event e) -> {
                    if ( !(e instanceof InputEvent) ||
                            !((InputEvent)e).getType().equals(InputEvent.Type.touchDown) )
                        return false;
                    QuestGame.setActiveScreen( new QuestLevelScreen() );
                    return false;
                }
        );

        TextButton spaceButton = new TextButton( "Space Game", BaseGame.textButtonStyle );
        uiStage.addActor(spaceButton);
        spaceButton.addListener(
                (Event e) -> {
                    if ( !(e instanceof InputEvent) ||
                            !((InputEvent)e).getType().equals(InputEvent.Type.touchDown) )
                        return false;
                    SpaceGame.setActiveScreen( new SpaceLevelScreen() );
                    return false;
                }
        );

        TextButton quitButton = new TextButton( "Quit", BaseGame.textButtonStyle );
        uiStage.addActor(quitButton);
        quitButton.addListener(
                (Event e) -> {
                    if ( !(e instanceof InputEvent) ||
                            !((InputEvent)e).getType().equals(InputEvent.Type.touchDown) )
                        return false;
                    Gdx.app.exit();
                    return false;
                }
        );

        uiTable.add().colspan(2);
        uiTable.row();
        uiTable.add(turtleButton);
        uiTable.add(breakoutButton);
        uiTable.row();
        uiTable.add(cardsButton);
        uiTable.add(dodgerButton);
        uiTable.row();
        uiTable.add(homeworkButton);
        uiTable.add(koalaButton);
        uiTable.row();
        uiTable.add(spaceButton);
        uiTable.add(questButton);
        uiTable.row();
        uiTable.add(quitButton).colspan(2);
    }

    @Override
    public void update(float deltaTime) {

    }
}

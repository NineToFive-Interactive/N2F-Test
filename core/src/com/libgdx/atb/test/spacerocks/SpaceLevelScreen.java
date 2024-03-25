package com.libgdx.atb.test.spacerocks;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.libgdx.atb.test.BaseActor;
import com.libgdx.atb.test.BaseScreen;

public class SpaceLevelScreen extends BaseScreen {
    private Spaceship spaceship;
    private boolean gameOver;

    @Override
    public void initialize() {
        BaseActor space = new BaseActor(0,0,mainStage);
        space.loadTexture("SpaceRocks/V1/space.png");
        space.setSize(800, 600);
        BaseActor.setWorldBounds(space);

        spaceship = new Spaceship(400,300, mainStage);

        gameOver = false;

        new Rock(600,500, mainStage);
        new Rock(600,300, mainStage);
        new Rock(600,100, mainStage);
        new Rock(400,100, mainStage);
        new Rock(200,100, mainStage);
        new Rock(200,300, mainStage);
        new Rock(200,500, mainStage);
        new Rock(400,500, mainStage);
        new Rock(100,200,mainStage);
        new Rock(100,200,mainStage);
        new Rock(100,200,mainStage);
        new Rock(100,200,mainStage);
        new Rock(100,200,mainStage);
        new Rock(100,200,mainStage);
        new Rock(100,200,mainStage);


    }

    @Override
    public void update(float deltaTime) {
        for ( BaseActor rock : BaseActor.getList(mainStage, Rock.class) ) {
            if (rock.overlaps(spaceship)) {
                if (spaceship.getShieldPower() <= 0) {

                    Explosion boom = new Explosion(0,0, mainStage);
                    boom.centerAtActor(spaceship);
                    spaceship.remove();
                    spaceship.setPosition(-1000,-1000);

                    BaseActor messageLose = new BaseActor(0,0, uiStage);
                    messageLose.loadTexture("SpaceRocks/V1/message-lose.png");
                    messageLose.centerAtPosition(400,300);
                    messageLose.setOpacity(0);
                    messageLose.addAction( Actions.fadeIn(1) );
                    gameOver = true;

                } else {

                    spaceship.setShieldPower(spaceship.getShieldPower()-34);
                    Explosion boom = new Explosion(0,0, mainStage);
                    boom.centerAtActor(rock);
                    rock.remove();

                }
            }

            for ( BaseActor laserActor : BaseActor.getList(mainStage, Laser.class) ) {
                if (laserActor.overlaps(rock)) {

                    Explosion boom = new Explosion(0,0, mainStage);
                    boom.centerAtActor(rock);
                    laserActor.remove();
                    rock.remove();
                }
            }
        }

        if ( !gameOver && BaseActor.count(mainStage, Rock.class) == 0 ) {

            BaseActor messageWin = new BaseActor(0,0, uiStage);

            messageWin.loadTexture("SpaceRocks/V1/message-win.png");
            messageWin.centerAtPosition(400,300);
            messageWin.setOpacity(0);
            messageWin.addAction( Actions.fadeIn(1) );

            gameOver = true;
        }
    }
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.X) spaceship.warp();
        if (keycode == Input.Keys.SPACE) spaceship.shoot();
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.RIGHT) spaceship.warp();
        if (button == Input.Buttons.LEFT) spaceship.shoot();
        return false;
    }
}

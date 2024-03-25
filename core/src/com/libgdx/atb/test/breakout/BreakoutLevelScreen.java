package com.libgdx.atb.test.breakout;

import com.libgdx.atb.test.BaseActor;
import com.libgdx.atb.test.BaseGame;
import com.libgdx.atb.test.BaseScreen;
import com.libgdx.atb.test.TilemapActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;

public class BreakoutLevelScreen extends BaseScreen {

    Paddle paddle;
    Ball ball;
    int score;
    int balls;
    Label scoreLabel;
    Label ballsLabel;
    Label messageLabel;
    Sound bounceSound;
    Sound brickBumpSound;
    Sound wallBumpSound;
    Sound itemAppearSound;
    Sound itemCollectSound;
    Music backgroundMusic;
    @Override
    public void initialize() {

        TilemapActor tma = new TilemapActor("RectangleDestroyer/V2/map.tmx", mainStage);

        for (MapObject obj : tma.getTileList("Wall") ) {
            MapProperties props = obj.getProperties();
            new Wall( (float)props.get("x"), (float)props.get("y"), mainStage,
                    (float)props.get("width"),(float)props.get("height"));
        }

        for (MapObject obj : tma.getTileList("Brick") ) {

            MapProperties props = obj.getProperties();

            Brick b = new Brick( (float)props.get("x"), (float)props.get("y"), mainStage );
            b.setSize( (float)props.get("width"), (float)props.get("height") );
            b.setBoundaryRectangle();

            String colorName = (String)props.get("color");

            if ( colorName.equals("red") )
                b.setColor(Color.RED);
            else if ( colorName.equals("orange") )
                b.setColor(Color.ORANGE);
            else if ( colorName.equals("yellow") )
                b.setColor(Color.YELLOW);
            else if ( colorName.equals("green") )
                b.setColor(Color.GREEN);
            else if ( colorName.equals("blue") )
                b.setColor(Color.BLUE);
            else if ( colorName.equals("purple") )
                b.setColor(Color.PURPLE);
            else if ( colorName.equals("white") )
                b.setColor(Color.WHITE);
            else if ( colorName.equals("gray") )
                b.setColor(Color.GRAY);
        }

        MapObject startPoint = tma.getRectangleList("Start").get(0);
        MapProperties props = startPoint.getProperties();

        paddle = new Paddle( (float)props.get("x"), (float)props.get("y"), mainStage);

        ball = new Ball(0,0,mainStage);

        score = 0;
        balls = 3;

        scoreLabel = new Label( "Score: " + score, BaseGame.labelStyle );
        ballsLabel = new Label( "Balls: " + balls, BaseGame.labelStyle );
        messageLabel = new Label("click to start", BaseGame.labelStyle );

        messageLabel.setColor( Color.CYAN );

        uiTable.pad(5);
        uiTable.add(scoreLabel);
        uiTable.add().expandX();
        uiTable.add(ballsLabel);
        uiTable.row();
        uiTable.add(messageLabel).colspan(3).expandY();

        bounceSound = Gdx.audio.newSound(Gdx.files.internal("RectangleDestroyer/V1/boing.wav"));
        brickBumpSound = Gdx.audio.newSound(Gdx.files.internal("RectangleDestroyer/V1/bump.wav"));
        wallBumpSound = Gdx.audio.newSound(Gdx.files.internal("RectangleDestroyer/V1/bump-low.wav"));
        itemAppearSound = Gdx.audio.newSound(Gdx.files.internal("RectangleDestroyer/V1/swoosh.wav"));
        itemCollectSound = Gdx.audio.newSound(Gdx.files.internal("RectangleDestroyer/V1/pop.wav"));

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("RectangleDestroyer/V1/Rollin-at-5.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.50f);
        backgroundMusic.play();
    }

    @Override
    public void update(float deltaTime) {

        float mouseX = Gdx.input.getX();
        paddle.setX( mouseX - paddle.getWidth()/2 );
        paddle.boundToWorld();

        if ( ball.isPaused() ) {
            ball.setX( paddle.getX() + paddle.getWidth()/2 - ball.getWidth()/2 );
            ball.setY( paddle.getY() + paddle.getHeight()/2 + ball.getHeight()/2 );
        }

        for (BaseActor wall : BaseActor.getList(mainStage, Wall.class)) {
            if ( ball.overlaps(wall) ) {
                ball.bounceOff(wall);
                wallBumpSound.play();
            }
        }

        for (BaseActor brick : BaseActor.getList(mainStage, Brick.class)) {
            if ( ball.overlaps(brick) ) {
                ball.bounceOff(brick);
                brickBumpSound.play();

                score += 100;
                scoreLabel.setText("Score: " + score);

                float spawnProbability = 20;

                if ( MathUtils.random(0, 100) < spawnProbability ) {
                    Item i = new Item(0,0,mainStage);
                    itemAppearSound.play();
                    i.centerAtActor(brick);
                }

                brick.remove();
            }
        }

        for (BaseActor item : BaseActor.getList(mainStage, Item.class)) {
            if ( paddle.overlaps(item) ) {
                Item realItem = (Item)item;

                if (realItem.getType() == Item.Type.PADDLE_EXPAND)
                    paddle.setWidth( paddle.getWidth() * 1.25f );
                else if (realItem.getType() == Item.Type.PADDLE_SHRINK)
                    paddle.setWidth( paddle.getWidth() * 0.80f );
                else if (realItem.getType() == Item.Type.BALL_SPEED_UP)
                    ball.setSpeed( ball.getSpeed() * 1.50f );
                else if (realItem.getType() == Item.Type.BALL_SPEED_DOWN)
                    ball.setSpeed( ball.getSpeed() * 0.90f );

                paddle.setBoundaryRectangle();
                item.remove();
                itemCollectSound.play();
            }
        }

        if ( ball.overlaps(paddle) ) {
            float ballCenterX = ball.getX() + ball.getWidth()/2;
            float paddlePercentHit = (ballCenterX - paddle.getX()) / paddle.getWidth();
            float bounceAngle = MathUtils.lerp( 150, 30, paddlePercentHit );

            ball.setMotionAngle( bounceAngle );
            bounceSound.play();
        }

        if ( BaseActor.count(mainStage, Brick.class) == 0) {
            messageLabel.setText("You win!");
            messageLabel.setColor( Color.LIME );
            messageLabel.setVisible(true);
        }

        if ( ball.getY() < -50 && BaseActor.count(mainStage, Brick.class) > 0 ) {

            ball.remove();

            if (balls > 0) {

                balls -= 1;
                ballsLabel.setText("Balls: " + balls);
                ball = new Ball(0,0,mainStage);

                messageLabel.setText("Click to start");
                messageLabel.setColor( Color.CYAN );
                messageLabel.setVisible(true);
            } else {

                messageLabel.setText("Game Over");
                messageLabel.setColor( Color.RED );
                messageLabel.setVisible(true);
            }
        }
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if ( ball.isPaused() ) {
            ball.setPaused(false);
            messageLabel.setVisible(false);
        }

        return false;
    }
}

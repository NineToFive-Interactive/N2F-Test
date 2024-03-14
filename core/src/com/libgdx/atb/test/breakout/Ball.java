package com.libgdx.atb.test.breakout;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;
import com.badlogic.gdx.math.Vector2;

public class Ball extends BaseActor {

    boolean paused;

    public Ball(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadTexture("RectangleDestroyer/V1/ball.png");

        setSpeed(400);
        setMotionAngle(90);
        setBoundaryPolygon(12);
        setPaused(true);
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void act(float dt) {

        super.act(dt);

        if ( !isPaused() ) {
            // simulate gravity
            setAcceleration(10);
            accelerateAtAngle(270);
            applyPhysics(dt);
        }
    }

    public void bounceOff(BaseActor other) {

        Vector2 vector2 = this.preventOverlap(other);

        if ( Math.abs(vector2.x) >= Math.abs(vector2.y) ) // horizontal bounce
            this.velocityVector.x *= -1;
        else // vertical bounce
            this.velocityVector.y *= -1;
    }
}

package com.libgdx.atb.test.koala;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;

import com.libgdx.atb.test.BaseActor;

public class Koala extends BaseActor {

    private Animation stand;
    private Animation walk;
    private Animation jump;
    private float walkAcceleration;
    private float walkDeceleration;
    private float maxHorizontalSpeed;
    private float gravity;
    private float maxVerticalSpeed;
    private float jumpSpeed;
    private BaseActor belowSensor;

    public Koala(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        stand = loadTexture("JumpingJack/V1/koala/stand.png");

        String[] walkFileNames = {
                "JumpingJack/V1/koala/walk-1.png",
                "JumpingJack/V1/koala/walk-2.png",
                "JumpingJack/V1/koala/walk-3.png",
                "JumpingJack/V1/koala/walk-2.png"
        };

        walk = loadAnimationFromFiles(walkFileNames, 0.2f, true);

        jump = loadTexture( "JumpingJack/V1/koala/jump.png");

        maxHorizontalSpeed = 100;
        walkAcceleration = 200;
        walkDeceleration = 200;
        gravity = 700;
        maxVerticalSpeed = 1000;
        jumpSpeed = 450;

        setBoundaryRectangle();

        belowSensor = new BaseActor(0,0, stage);
        belowSensor.loadTexture("JumpingJack/V1/white.png");
        belowSensor.setSize( this.getWidth() - 8, 8 );
        belowSensor.setBoundaryRectangle();
        belowSensor.setVisible(false);
    }

    public void act(float dt) {

        super.act( dt );

        if (Gdx.input.isKeyPressed(Keys.LEFT))
            accelerationVector.add( -walkAcceleration, 0 );

        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            accelerationVector.add( walkAcceleration, 0 );

        accelerationVector.add(0, -gravity);

        velocityVector.add( accelerationVector.x * dt, accelerationVector.y * dt );

        if ( !Gdx.input.isKeyPressed(Keys.RIGHT) && !Gdx.input.isKeyPressed(Keys.LEFT) ) {

            float decelerationAmount = walkDeceleration * dt;
            float walkDirection;

            if (velocityVector.x > 0)
                walkDirection = 1;
            else
                walkDirection = -1;

            float walkSpeed = Math.abs(velocityVector.x);

            walkSpeed -= decelerationAmount;

            if (walkSpeed < 0)
                walkSpeed = 0;

            velocityVector.x = walkSpeed * walkDirection;
        }

        velocityVector.x = MathUtils.clamp( velocityVector.x, -maxHorizontalSpeed, maxHorizontalSpeed );
        velocityVector.y = MathUtils.clamp( velocityVector.y, -maxVerticalSpeed, maxVerticalSpeed );

        moveBy( velocityVector.x * dt, velocityVector.y * dt );

        accelerationVector.set(0,0);

        belowSensor.setPosition( getX() + 4, getY() - 8 );

        alignCamera();
        boundToWorld();

        if ( this.isOnSolid() ) {
            belowSensor.setColor( Color.GREEN );

            if ( velocityVector.x == 0 )
                setAnimation(stand);
            else
                setAnimation(walk);

        } else {
            belowSensor.setColor( Color.RED );
            setAnimation(jump);
        }

        if ( velocityVector.x > 0 ) // face right
                setScaleX(1);
        if ( velocityVector.x < 0 ) // face left
                setScaleX(-1);
    }

    public boolean belowOverlaps(BaseActor actor) {
        return belowSensor.overlaps(actor);
    }

    public boolean isOnSolid() {
        for (BaseActor actor : BaseActor.getList( getStage(), Solid.class )) {
            Solid solid = (Solid)actor;

            if ( belowOverlaps(solid) && solid.isEnabled() )
                return true;
        }
        return false;
    }

    public void jump() {
        velocityVector.y = jumpSpeed;
    }

    public boolean isJumping() {
        return (velocityVector.y > 0);
    }

    public boolean isFalling() {
        return (velocityVector.y < 0);
    }

    public void spring() {
        velocityVector.y = 1.5f * jumpSpeed;
    }
}

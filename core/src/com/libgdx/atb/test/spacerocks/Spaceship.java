package com.libgdx.atb.test.spacerocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;
import com.badlogic.gdx.math.MathUtils;

public class Spaceship extends BaseActor {
    private Thrusters thrusters;
    private Shield shield;
    private int shieldPower;

    public Spaceship(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        loadTexture("SpaceRocks/V1/spaceship.png");
        setBoundaryPolygon(8);

        setAcceleration(250);
        setMaxSpeed(140);
        setDeceleration(10);

        thrusters = new Thrusters(0,0,stage);
        addActor(thrusters);
        thrusters.setPosition(-thrusters.getWidth(), getHeight()/2 - thrusters.getHeight()/2);

        shield = new Shield(0,0, stage);
        shield.setSize(getWidth()+50, getHeight()+50);
        addActor(shield);
        shield.centerAtPosition( getWidth()/2, getHeight()/2 );
        shieldPower = 100;
    }

    public void act(float deltaTime) {
        super.act(deltaTime);

        float degreesPerSecond = 160;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))
            rotateBy(degreesPerSecond * deltaTime);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))
            rotateBy(-degreesPerSecond * deltaTime);

        if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            accelerateAtAngle(getRotation());
            thrusters.setVisible(true);
        } else {
            thrusters.setVisible(false);
        }

        shield.setOpacity(shieldPower / 100f);
        if (shieldPower <= 0)
            shield.setVisible(false);

        applyPhysics(deltaTime);
        wrapAroundWorld();
    }

    public void warp() {
        if ( getStage() == null) return;

        Warp warp1 = new Warp(0,0, this.getStage());
        warp1.centerAtActor(this);

        setPosition(MathUtils.random(800), MathUtils.random(600));

        Warp warp2 = new Warp(0,0, this.getStage());
        warp2.centerAtActor(this);
    }

    public void shoot() {
        if ( getStage() == null ) return;

        Laser laser = new Laser(0,0, this.getStage());
        laser.centerAtActor(this);
        laser.setRotation( this.getRotation() );
        laser.setMotionAngle( this.getRotation() );
    }

    public int getShieldPower() {
        return shieldPower;
    }

    public void setShieldPower(int shieldPower) {
        this.shieldPower = shieldPower;
    }
}

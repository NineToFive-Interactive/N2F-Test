package com.libgdx.atb.test.turtle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;
import com.libgdx.atb.test.GraphicActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Turtle extends BaseActor {

    public Turtle(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        String[] filenames =
                {"TurtleGame/Selfmade Textures/TurtleSwimming1.png",
                 "TurtleGame/Selfmade Textures/TurtleSwimming2.png",
                 "TurtleGame/Selfmade Textures/TurtleSwimming3.png",
                 "TurtleGame/Selfmade Textures/TurtleSwimming4.png",
                 "TurtleGame/Selfmade Textures/TurtleSwimming5.png",
                 "TurtleGame/Selfmade Textures/TurtleSwimming6.png",
                 "TurtleGame/Selfmade Textures/TurtleSwimming7.png" };

        loadAnimationFromFiles(filenames, 0.1f, true);

        setBoundaryPolygon(8);

        setAcceleration(200);
        setMaxSpeed(200);
        setDeceleration(200);
    }

    public void act(float deltaTime) {
        super.act(deltaTime);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            accelerateAtAngle(180);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            accelerateAtAngle(0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            accelerateAtAngle(90);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            accelerateAtAngle(270);
        }

        applyPhysics(deltaTime);

        setAnimationPaused( !isMoving() );

        if ( getSpeed() > 0 )
            setRotation( getMotionAngle() );

        boundToWorld();
        alignCamera();
    }
}

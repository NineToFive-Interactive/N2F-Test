package com.libgdx.atb.test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;

public class BaseActor extends Actor {
    private Animation<TextureRegion> animation;
    private float elapsedTime;
    private boolean animationPaused;
    private Vector2 velocityVector;
    private Vector2 accelerationVector;
    private float acceleration;
    private float maxSpeed;
    private float deceleration;


    public BaseActor(float posX, float posY, Stage stage){
        super();
        setPosition(posX,posY);
        stage.addActor(this);
        animation = null;
        elapsedTime = 0;
        animationPaused = false;
        velocityVector = new Vector2(0,0);
        accelerationVector = new Vector2(0,0);
        acceleration = 0;
        maxSpeed = 1000;
        deceleration = 0;
    }

    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
        TextureRegion textureRegion = this.animation.getKeyFrame(0);
        float width = textureRegion.getRegionWidth();
        float height = textureRegion.getRegionHeight();
        setSize( width, height );
        setOrigin( width/2, height/2 );
    }

    public void setAnimationPaused(boolean pause) {
        animationPaused = pause;
    }

    public void act(float delta)
    {
        super.act( delta );
        if (!animationPaused)
            elapsedTime += delta;
    }

    public void draw(Batch batch, float parentAlpha)
    {
        super.draw( batch, parentAlpha );

        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);

        if ( animation != null && isVisible() )
            batch.draw( animation.getKeyFrame(elapsedTime),
                    getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation() );
    }

    public Animation<TextureRegion> loadAnimationFromFiles(String[] fileNames, float frameDuration, boolean loop) {
        int fileCount = fileNames.length;
        Array<TextureRegion> textureArray = new Array<TextureRegion>();

        for (String fileName : fileNames) {
            Texture texture = new Texture(Gdx.files.internal(fileName));
            texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
            textureArray.add(new TextureRegion(texture));
        }

        Animation<TextureRegion> animation = new Animation<TextureRegion>(frameDuration, textureArray);

        if(loop) animation.setPlayMode(PlayMode.LOOP);
        else animation.setPlayMode(PlayMode.NORMAL);

        if(this.animation == null) setAnimation(animation);

        return animation;
    }

    public Animation<TextureRegion> loadAnimationFromSheet(String fileName, int rows, int cols, float frameDuration, boolean loop) {
        Texture texture = new Texture(Gdx.files.internal(fileName), true);
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        int frameWidth = texture.getWidth() / cols;
        int frameHeight = texture.getHeight() / rows;

        TextureRegion[][] temp = TextureRegion.split(texture, frameWidth, frameHeight);
        Array<TextureRegion> textureArray = new Array<TextureRegion>();

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                textureArray.add( temp[i][j] );

        Animation<TextureRegion> animation = new Animation<TextureRegion>(frameDuration, textureArray);

        if (loop) animation.setPlayMode(Animation.PlayMode.LOOP);
        else animation.setPlayMode(Animation.PlayMode.NORMAL);

        if (this.animation == null) setAnimation(animation);

        return animation;
    }

    public Animation<TextureRegion> loadTexture(String fileName) {
        String[] fileNames = new String[1];
        fileNames[0] = fileName;
        return loadAnimationFromFiles(fileNames, 1, true);
    }

    public boolean isAnimationFinished()
    {
        return animation.isAnimationFinished(elapsedTime);
    }

    public void setSpeed(float speed) {
        if (velocityVector.len() == 0)
            velocityVector.set(speed, 0);
        else
            velocityVector.setLength(speed);
    }
    public float getSpeed() {
        return velocityVector.len();
    }
    public void setMotionAngle(float angle) {
        velocityVector.setAngle(angle);
    }
    public float getMotionAngle() {
        return velocityVector.angle();
    }
    public boolean isMoving() {
        return (getSpeed() > 0);
    }
    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }
    public void accelerateAtAngle(float angle) {
        accelerationVector.add( new Vector2(acceleration, 0).setAngle(angle) );
    }
    public void accelerateForward() {
        accelerateAtAngle( getRotation() );
    }
    public void setMaxSpeed(float ms) {
        maxSpeed = ms;
    }
    public void setDeceleration(float dec) {
        deceleration = dec;
    }
}

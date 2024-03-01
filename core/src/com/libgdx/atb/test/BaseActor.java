package com.libgdx.atb.test;

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

    public BaseActor(float posX, float posY, Stage stage){
        super();
        setPosition(posX,posY);
        stage.addActor(this);
        animation = null;
        elapsedTime = 0;
        animationPaused = false;
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
}

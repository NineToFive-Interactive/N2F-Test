package com.libgdx.atb.test;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

public class GraphicActor extends Actor {

    private TextureRegion textureRegion;
    private Rectangle hitbox;

    public GraphicActor() {
        super();
        textureRegion = new TextureRegion();
        hitbox = new Rectangle();
    }

    public void setTexture(Texture texture) {
        textureRegion.setRegion(texture);
        setSize(texture.getWidth(), texture.getHeight());
        hitbox.setSize(texture.getWidth(), texture.getHeight());
    }

    public Rectangle getHitbox(){
        hitbox.setPosition(this.getX(), this.getY());
        return hitbox;
    }

    public boolean overlaps(GraphicActor otherActor) {
        return this.getHitbox().overlaps(otherActor.getHitbox());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw( batch, parentAlpha );
        Color c = getColor(); // used to apply tint color effect
        batch.setColor(c.r, c.g, c.b, c.a);
        if ( isVisible() )
            batch.draw( textureRegion,
                    getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation() );
    }
}

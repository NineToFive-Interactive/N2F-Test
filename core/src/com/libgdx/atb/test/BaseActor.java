package com.libgdx.atb.test;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
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
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Intersector.MinimumTranslationVector;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;

public class BaseActor extends Group {
    private Animation<TextureRegion> animation;
    private float elapsedTime;
    private boolean animationPaused;
    private Vector2 velocityVector;
    private Vector2 accelerationVector;
    private float acceleration;
    private float maxSpeed;
    private float deceleration;
    private Polygon boundaryPolygon;
    private static Rectangle worldBounds;


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

        if (boundaryPolygon == null) setBoundaryRectangle();
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
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);

        if ( animation != null && isVisible() )
            batch.draw( animation.getKeyFrame(elapsedTime),
                    getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation() );

        super.draw( batch, parentAlpha );
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
    public void applyPhysics(float deltaTime) {
        // apply acceleration
        velocityVector.add( accelerationVector.x * deltaTime, accelerationVector.y * deltaTime );
        float speed = getSpeed();

        // decrease speed (decelerate) when not accelerating
        if (accelerationVector.len() == 0) speed -= deceleration * deltaTime;

        // keep speed within set bounds
        speed = MathUtils.clamp(speed, 0, maxSpeed);

        // update velocity
        setSpeed(speed);

        // apply velocity
        moveBy( velocityVector.x * deltaTime, velocityVector.y * deltaTime );

        // reset acceleration
        accelerationVector.set(0,0);
    }

    public void setBoundaryRectangle() {
        float w = getWidth();
        float h = getHeight();
        float[] vertices = {0,0, w,0, w,h, 0,h};
        boundaryPolygon = new Polygon(vertices);
    }

    public void setBoundaryPolygon(int numSides) {
        float w = getWidth();
        float h = getHeight();
        float[] vertices = new float[2*numSides];

        for (int i = 0; i < numSides; i++) {
            float angle = i * 6.28f / numSides;

            // x-coordinate
            vertices[2*i] = w/2 * MathUtils.cos(angle) + w/2;

            // y-coordinate
            vertices[2*i+1] = h/2 * MathUtils.sin(angle) + h/2;
        }
        boundaryPolygon = new Polygon(vertices);
    }

    public Polygon getBoundaryPolygon() {
        boundaryPolygon.setPosition( getX(), getY() );
        boundaryPolygon.setOrigin( getOriginX(), getOriginY() );
        boundaryPolygon.setRotation ( getRotation() );
        boundaryPolygon.setScale( getScaleX(), getScaleY() );

        return boundaryPolygon;
    }

    public boolean overlaps(BaseActor other) {
        Polygon poly1 = this.getBoundaryPolygon();
        Polygon poly2 = other.getBoundaryPolygon();

        // initial test to improve performance
        if ( !poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()) ) return false;

        return Intersector.overlapConvexPolygons( poly1, poly2 );
    }

    public void centerAtPosition(float x, float y) {
        setPosition( x - getWidth()/2 , y - getHeight()/2 );
    }

    public void centerAtActor(BaseActor other) {
        centerAtPosition( other.getX() + other.getWidth()/2 , other.getY() + other.getHeight()/2 );
    }

    public void setOpacity(float opacity) {
        this.getColor().a = opacity;
    }

    public Vector2 preventOverlap(BaseActor other) {
        Polygon poly1 = this.getBoundaryPolygon();
        Polygon poly2 = other.getBoundaryPolygon();

        // initial test to improve performance
        if ( !poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()) ) return null;

        MinimumTranslationVector mtv = new MinimumTranslationVector();

        boolean polygonOverlap = Intersector.overlapConvexPolygons(poly1, poly2, mtv);

        if ( !polygonOverlap ) return null;

        this.moveBy( mtv.normal.x * mtv.depth, mtv.normal.y * mtv.depth );

        return mtv.normal;
    }

    public static <T> ArrayList<T> getList(Stage stage, Class<T> type) {
        ArrayList<T> list = new ArrayList<T>();
        for (Actor a : stage.getActors()) {
            if (type.isInstance(a))
                list.add((T) a);
        }
        return list;
    }

    public static <T> int count(Stage stage, Class<T> type) {
        return getList(stage, type).size();
    }

    public static void setWorldBounds(float width, float height) {
        worldBounds = new Rectangle(0,0,width,height);
    }

    public static void setWorldBounds(BaseActor baseActor) {
        setWorldBounds(baseActor.getWidth(), baseActor.getHeight());
    }

    public void boundToWorld()
    {
        // check left edge
        if (getX() < 0)
            setX(0);

        // check right edge
        if (getX() + getWidth() > worldBounds.width)
            setX(worldBounds.width - getWidth());

        // check bottom edge
        if (getY() < 0)
            setY(0);

        // check top edge
        if (getY() + getHeight() > worldBounds.height)
            setY(worldBounds.height - getHeight());
    }

    public void alignCamera()
    {
        Camera camera = this.getStage().getCamera();
        Viewport viewport = this.getStage().getViewport();

        // center camera on actor
        camera.position.set( this.getX() + this.getOriginX(), this.getY() + this.getOriginY(), 0 );

        // bound camera to layout
        camera.position.x = MathUtils.clamp(camera.position.x,
                camera.viewportWidth/2, worldBounds.width - camera.viewportWidth/2);
        camera.position.y = MathUtils.clamp(camera.position.y,
                camera.viewportHeight/2, worldBounds.height - camera.viewportHeight/2);

        camera.update();
    }

    public void wrapAroundWorld() {
        if (getX() + getWidth() < 0)
            setX( worldBounds.width );

        if (getX() > worldBounds.width)
            setX( -getWidth());

        if (getY() + getHeight() < 0)
            setY( worldBounds.height );

        if (getY() > worldBounds.height)
            setY( -getHeight() );
    }
}

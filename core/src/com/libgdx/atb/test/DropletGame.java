package com.libgdx.atb.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class DropletGame extends BaseGame{
    private Texture dropImage;
    private Texture bucketImage;
    private Texture mageImage;
    private Texture fireballImage;
    private Sound dropSound;
    private Music rainMusic;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Rectangle bucket;
    private Rectangle mage;
    private Array<Rectangle> fireballs;
    private Array<Rectangle> raindrops;
    private long lastDropTime;
    private long lastFireballTime;

    @Override
    void create() {
        batch = new SpriteBatch();

        dropImage = new Texture(Gdx.files.internal("droplet.png"));
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));
        mageImage = new Texture(Gdx.files.internal("minimage.png"));
        fireballImage = new Texture(Gdx.files.internal("fireball.png"));

        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        rainMusic.setLooping(true);
        rainMusic.play();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        mage = new Rectangle();
        mage.width = 64;
        mage.height = 64;
        mage.x = (float) 800  - mage.width;
        mage.y = 20;

        bucket = new Rectangle();
        bucket.width = 64;
        bucket.height = 64;
        bucket.x = (float) 800 / 2 - bucket.width / 2;
        bucket.y = 20;

        raindrops = new Array<>();
        spawnRaindrop();

        fireballs = new Array<>();
        spawnFireball();
    }


    @Override
    void render() {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        /* Optional to control the bucket with the mouse. (Wie Marcel sagen würde: ABSOLUT GIGA BROKEN!!)
        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.x = touchPos.x - 64 / 2;
        }
        */

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();
        if(bucket.x < 0) bucket.x = 0;
        if(bucket.x > 800 - 64) bucket.x = 800 - 64;

        if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();
        if(TimeUtils.nanoTime() - lastFireballTime > 5000000000L) spawnFireball();

        for (Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext(); ) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if(raindrop.y + 64 < 0) iter.remove();
            if(raindrop.overlaps(bucket)) {
                dropSound.play();
                iter.remove();
            }
        }

        for (Iterator<Rectangle> iter = fireballs.iterator(); iter.hasNext(); ) {
            Rectangle fireball = iter.next();
            fireball.x -= 400 * Gdx.graphics.getDeltaTime();
            if(fireball.x < 0) iter.remove();
            if(fireball.overlaps(bucket)) iter.remove();
        }

        batch.begin();
        batch.draw(mageImage, mage.x, mage.y);
        batch.draw(bucketImage, bucket.x, bucket.y);
        for(Rectangle raindrop: raindrops) {
            batch.draw(dropImage, raindrop.x, raindrop.y);
        }
        for(Rectangle fireball: fireballs) {
            batch.draw(fireballImage, fireball.x, fireball.y);
        }
        batch.end();
    }

    @Override
    void dispose() {
        dropImage.dispose();
        bucketImage.dispose();
        mageImage.dispose();
        fireballImage.dispose();
        dropSound.dispose();
        rainMusic.dispose();
        batch.dispose();
    }

    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800-64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    private void spawnFireball() {
        Rectangle fireball = new Rectangle();
        fireball.x = 672;
        fireball.y = 10;
        fireball.width = 64;
        fireball.height = 64;
        fireballs.add(fireball);
        lastFireballTime = TimeUtils.nanoTime();
    }

}

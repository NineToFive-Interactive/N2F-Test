package com.libgdx.atb.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class DropletGameScreen implements Screen {

    final DropletGame game;
    private Texture dropImage;
    private Sprite tankImage;
    private Texture mageImage;
    private Texture fireballImage;
    private Sound dropSound;
    private Music rainMusic;
    private OrthographicCamera camera;
    private Rectangle bucket;
    private Rectangle mage;
    private Array<Rectangle> fireballs;
    private Array<Rectangle> raindrops;
    private long lastDropTime;
    private long lastFireballTime;
    private int dropsGathered;
    private int fireballsCaught;
    private boolean isPaused = true;

    public DropletGameScreen(final DropletGame game) {
        this.game = game;

        dropImage = new Texture(Gdx.files.internal("DropletGame/droplet.png"));
        tankImage = new Sprite(new Texture(Gdx.files.internal("DropletGame/shooter.png")));
        tankImage.setScale(0.05f);
        mageImage = new Texture(Gdx.files.internal("DropletGame/minimage.png"));
        fireballImage = new Texture(Gdx.files.internal("DropletGame/fireball.png"));

        dropSound = Gdx.audio.newSound(Gdx.files.internal("DropletGame/drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("DropletGame/rain.mp3"));
        rainMusic.setLooping(true);

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
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        /* Optional to control the bucket with the mouse. (Wie Marcel sagen würde: ABSOLUT GIGA BROKEN!!)
        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.x = touchPos.x - 64 / 2;
        }
        */

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) bucket.x += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new DropletMainMenuScreen(game));
            dispose();
        }

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
                dropsGathered += 1;
            }
        }

        for (Iterator<Rectangle> iter = fireballs.iterator(); iter.hasNext(); ) {
            Rectangle fireball = iter.next();
            fireball.x -= 400 * Gdx.graphics.getDeltaTime();
            if(fireball.x < 0) iter.remove();
            if(fireball.overlaps(bucket)) {
                iter.remove();
                fireballsCaught += 1;
            }
        }

        game.batch.begin();

        game.font.draw(game.batch, "Wasser eingefangen: " + dropsGathered, 0, 480);
        game.font.draw(game.batch, "Feuer eingefangen: " + fireballsCaught, 0, 460);

        game.batch.draw(tankImage, bucket.x, bucket.y, bucket.width, bucket.height);
        game.batch.draw(mageImage, mage.x, mage.y, mage.width, mage.height);

        for(Rectangle raindrop: raindrops) {
            game.batch.draw(dropImage, raindrop.x, raindrop.y);
        }
        for(Rectangle fireball: fireballs) {
            game.batch.draw(fireballImage, fireball.x, fireball.y);
        }

        game.batch.end();

    }

    @Override
    public void show() {
        rainMusic.play();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        dropImage.dispose();
        tankImage.getTexture().dispose();
        mageImage.dispose();
        fireballImage.dispose();
        dropSound.dispose();
        rainMusic.dispose();
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

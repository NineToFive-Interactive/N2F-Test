package com.libgdx.atb.test.koala;

import com.libgdx.atb.test.BaseScreen;
import com.libgdx.atb.test.TilemapActor;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;

public class LevelScreen extends BaseScreen {
    @Override
    public void initialize() {
        TilemapActor tma = new TilemapActor("JumpingJack/V1/map.tmx", mainStage);

        for (MapObject obj : tma.getRectangleList("Solid") ) {

            MapProperties props = obj.getProperties();

            new Solid( (float)props.get("x"), (float)props.get("y"),
                    (float)props.get("width"), (float)props.get("height"),
                    mainStage );
        }
    }

    @Override
    public void update(float deltaTime) {

    }
}

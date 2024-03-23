package com.libgdx.atb.test.neondungeons;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.libgdx.atb.test.BaseScreen;
import com.libgdx.atb.test.TilemapActor;
import com.libgdx.atb.test.quest.Hero;
import com.libgdx.atb.test.quest.Solid;

public class LevelScreen extends BaseScreen {

    Hero hero;

    @Override
    public void initialize() {
        TilemapActor tma = new TilemapActor("NeonDungeon/Maps/test-map.tmx", mainStage);

        for (MapObject obj : tma.getRectangleList("Solid") ) {
            MapProperties props = obj.getProperties();
            new Solid( (float)props.get("x"), (float)props.get("y"),
                    (float)props.get("width"), (float)props.get("height"),
                    mainStage );
        }

        MapObject startPoint = tma.getRectangleList("Start").get(0);
        MapProperties startProps = startPoint.getProperties();
        hero = new Hero( (float)startProps.get("x"), (float)startProps.get("y"), mainStage);
    }

    @Override
    public void update(float deltaTime) {

    }
}

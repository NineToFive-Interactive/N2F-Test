package com.libgdx.atb.test.rythm;

import com.libgdx.atb.test.BaseActor;
import com.libgdx.atb.test.BaseScreen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import java.util.ArrayList;
import java.util.Collections;

public class RhythmScreen extends BaseScreen {

    private ArrayList<String> keyList;
    private ArrayList<Color> colorList;
    private ArrayList<TargetBox> targetList;
    private ArrayList<ArrayList<FallingBox>> fallingLists;

    @Override
    public void initialize() {
        BaseActor background = new BaseActor(0,0, mainStage);
        background.loadTexture( "RythmTapper/V1/space.png" );
        background.setSize(800,600);
        BaseActor.setWorldBounds(background);

        keyList = new ArrayList<String>();
        String[] keyArray = {"F", "G", "H", "J"};
        Collections.addAll(keyList, keyArray);

        colorList = new ArrayList<Color>();
        Color[] colorArray = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};
        Collections.addAll(colorList, colorArray);

        Table targetTable = new Table();
        targetTable.setFillParent(true);
        targetTable.add().colspan(4).expandY();
        targetTable.row();
        mainStage.addActor(targetTable);

        targetList = new ArrayList<TargetBox>();

        for (int i = 0; i < 4; i++) {
            TargetBox targetBox = new TargetBox(0,0,mainStage, keyList.get(i), colorList.get(i));
            targetList.add(targetBox);
            targetTable.add(targetBox).pad(32);
        }

        fallingLists = new ArrayList< ArrayList<FallingBox> >();

        for (int i = 0; i < 4; i++) {
            fallingLists.add( new ArrayList<FallingBox>() );
        }
    }

    @Override
    public void update(float deltaTime) {

    }

    public boolean keyDown(int keycode) {
        return false;
    }

}

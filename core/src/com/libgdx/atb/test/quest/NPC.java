package com.libgdx.atb.test.quest;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;

public class NPC extends BaseActor {

    private String text;
    private boolean viewing;
    private String ID;

    public NPC(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        text = " ";
        viewing = false;
    }

    public void setText(String t) {
        text = t;
    }

    public String getText() {
        return text;
    }

    public void setViewing(boolean v) {
        viewing = v;
    }

    public boolean isViewing() {
        return viewing;
    }

    public void setID(String id) {

        ID = id;

        if ( ID.equals("Gatekeeper") ) loadTexture("TreasureQuest/V1/npc-1.png");
        else if (ID.equals("Shopkeeper")) loadTexture("TreasureQuest/V1/npc-2.png");
        else loadTexture("TreasureQuest/V1/npc-3.png");
    }

    public String getID() {
        return ID;
    }
}

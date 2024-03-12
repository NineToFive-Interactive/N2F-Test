package com.libgdx.atb.test;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;

public class SetTextAction extends Action {
    protected String textToDisplay;

    public SetTextAction(String t) {
        textToDisplay = t;
    }
    @Override
    public boolean act(float v) {
        DialogBox db = (DialogBox)target;
        db.setText( textToDisplay );
        return true;
    }
}

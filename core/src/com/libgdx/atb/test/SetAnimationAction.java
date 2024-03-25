package com.libgdx.atb.test;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.libgdx.atb.test.BaseActor;

public class SetAnimationAction extends Action {
    protected Animation animationToDisplay;

    public SetAnimationAction(Animation a) {
        animationToDisplay = a;
    }

    @Override
    public boolean act(float v) {
        BaseActor ba = (BaseActor) target;
        ba.setAnimation( animationToDisplay );

        return true;
    }
}

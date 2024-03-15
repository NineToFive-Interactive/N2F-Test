package com.libgdx.atb.test.cards;

import com.libgdx.atb.test.BaseActor;
import com.libgdx.atb.test.BaseGame;
import com.libgdx.atb.test.BaseScreen;
import com.badlogic.gdx.math.MathUtils;
import java.util.ArrayList;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.Color;

public class LevelScreen extends BaseScreen {

    private ArrayList<Pile> pileList;
    private Label messageLabel;

    @Override
    public void initialize() {

        BaseActor background = new BaseActor(0,0, mainStage);
        background.loadTexture("52CardGame/V1/felt.jpg");
        BaseActor.setWorldBounds(background);

        for (int r = 0; r < Card.rankNames.length; r++) {
            for (int s = 0; s < Card.suitNames.length; s++) {

                int x = MathUtils.random(0,800);
                int y = MathUtils.random(0,300);

                Card c = new Card(x,y, mainStage);
                c.setRankSuitValues(r,s);

                c.toBack();
            }
        }

        background.toBack();

        pileList = new ArrayList<Pile>();

        for (int i = 0; i < 4; i++) {
            int pileX = 120 + 150 * i;
            int pileY = 450;

            Pile pile = new Pile(pileX, pileY, mainStage);
            pileList.add(pile);
        }

        for ( BaseActor actor : BaseActor.getList(mainStage, Card.class) ) {

            Card card = (Card)actor;

            if (card.getRankValue() == 0) {
                Pile pile = pileList.get(card.getSuitValue());

                card.toFront();
                card.moveToActor(pile);
                pile.addCard(card);

                card.setDraggable(false);
            }
        }

        messageLabel = new Label("...", BaseGame.labelStyle);
        messageLabel.setColor( Color.CYAN );

        uiTable.add(messageLabel).expandX().expandY().bottom().pad(50);
        messageLabel.setVisible(false);
    }

    @Override
    public void update(float deltaTime) {
        boolean complete = true;

        for (Pile pile : pileList) {
            if ( pile.getSize() < 13 )
                complete = false;
        }

        if (complete) {
            messageLabel.setText("You win!");
            messageLabel.setVisible(true);
        }
    }
}

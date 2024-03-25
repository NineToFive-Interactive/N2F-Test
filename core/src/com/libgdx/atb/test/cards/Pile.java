package com.libgdx.atb.test.cards;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.DropTargetActor;

import java.util.ArrayList;

public class Pile extends DropTargetActor {

    private ArrayList<Card> cardList;

    public Pile(float posX, float posY, Stage stage) {
        super(posX, posY, stage);

        cardList = new ArrayList<Card>();

        loadTexture("52CardGame/V1/pile.png");
        setSize(100,120);
        setBoundaryRectangle();
    }

    public void addCard(Card c) {
        cardList.add(0, c);
    }

    public Card getTopCard() {
        return cardList.get(0);
    }

    public int getSize() {
        return cardList.size();
    }
}

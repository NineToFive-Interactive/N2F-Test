package com.libgdx.atb.test.puzzle;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.DropTargetActor;

public class PuzzleArea extends DropTargetActor {

    int row;
    int col;

    public PuzzleArea(float posX, float posY, Stage stage) {
        super(posX, posY, stage);
        loadTexture("JigsawPuzzle/V1/border.jpg");
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}

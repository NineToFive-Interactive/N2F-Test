package com.libgdx.atb.test.puzzle;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.DragAndDropActor;

public class PuzzlePiece extends DragAndDropActor {

    private int row;
    private int col;
    private PuzzleArea puzzleArea;

    public PuzzlePiece(float posX, float posY, Stage stage) {
        super(posX, posY, stage);
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

    public PuzzleArea getPuzzleArea() {
        return puzzleArea;
    }

    public void setPuzzleArea(PuzzleArea puzzleArea) {
        this.puzzleArea = puzzleArea;
    }

    public void clearPuzzleArea() {
        puzzleArea = null;
    }

    public boolean hasPuzzleArea() {
        return puzzleArea != null;
    }

    public boolean isCorrectlyPlaced() {
        return hasPuzzleArea()
                && this.getRow() == puzzleArea.getRow()
                && this.getCol() == puzzleArea.getCol();
    }

    @Override
    public void onDragStart() {
        if ( hasPuzzleArea() ) {
            PuzzleArea pa = getPuzzleArea();
            pa.setTargetable(true);
            clearPuzzleArea();
        }
    }

    @Override
    public void onDrop() {
        if ( hasDropTarget() ) {
            PuzzleArea pa = (PuzzleArea)getDropTarget();
            moveToActor(pa);
            setPuzzleArea(pa);
            pa.setTargetable(false);
        }
    }
}

package actions.base;

import logic.VectorDrawing;

public abstract class BaseDrawingAction implements DrawAction {

    protected VectorDrawing drawing;

    protected BaseDrawingAction(VectorDrawing drawing) {
        this.drawing = drawing;
    }

}

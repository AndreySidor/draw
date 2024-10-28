package actions.base;

import logic.Drawing;

public abstract class BaseDrawingAction implements DrawAction {

    protected Drawing drawing;

    protected BaseDrawingAction(Drawing drawing) {
        this.drawing = drawing;
    }

}

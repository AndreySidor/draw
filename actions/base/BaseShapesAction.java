package actions.base;

import logic.Drawing;

public abstract class BaseShapesAction {

    protected Drawing drawing;

    protected BaseShapesAction(Drawing drawing) {
        this.drawing = drawing;
    }

}

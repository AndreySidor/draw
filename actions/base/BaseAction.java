package actions.base;

import logic.Selection;
import logic.VectorDrawing;

public abstract class BaseAction implements DrawAction {

    protected VectorDrawing drawing;

    protected Selection selection;

    protected BaseAction(VectorDrawing drawing, Selection selection) {
        this.drawing = drawing;
        this.selection = selection;
    }

}

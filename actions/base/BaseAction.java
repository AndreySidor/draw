package actions.base;

import logic.Drawing;
import logic.Selection;

public abstract class BaseAction implements DrawAction {

    protected Drawing drawing;

    protected Selection selection;

    protected BaseAction(Drawing drawing, Selection selection) {
        this.drawing = drawing;
        this.selection = selection;
    }

}

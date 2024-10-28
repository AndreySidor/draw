package actions.base;

import logic.Selection;

public abstract class BaseSelectionsAction implements DrawAction {

    protected Selection selection;

    protected BaseSelectionsAction(Selection selection) {
        this.selection = selection;
    }

}

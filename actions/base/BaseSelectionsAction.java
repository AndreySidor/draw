package actions.base;

import logic.Selection;

public abstract class BaseSelectionsAction {

    protected Selection selection;

    protected BaseSelectionsAction(Selection selection) {
        this.selection = selection;
    }

}

package actions;

import actions.base.BaseSelectionsAction;
import logic.Selection;

/**
 * Заливка выбранных фигур
 */
public class FillAction extends BaseSelectionsAction {

	/**
	 * Конструктор
	 * @param selection - выбранные фигуры
	 */
	public FillAction(Selection selection) {
		super(selection.clone());
	}

	public Boolean execute() {
		Boolean checkForExecution = selection != null && !selection.isEmpty();
		if (checkForExecution) {
			selection.changeFilling();
		}
		return checkForExecution;
	}

	public void redo() {
		execute();
	}

	public void undo() {
		execute();
	}

	public String getDescription() {
		return null;
	}

}

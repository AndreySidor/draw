package actions;

import actions.base.BaseSelectionsAction;
import actions.base.DrawAction;
import logic.Selection;
import shapes.FillableShape;
import shapes.Shape;

/**
 * Заливка выбранных фигур
 */
public class FillAction extends BaseSelectionsAction implements DrawAction {

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
			for (Shape s : selection) {
				if (s instanceof FillableShape) {
					FillableShape fs = (FillableShape) s;
					fs.setFilled(!(fs).getFilled());
				}
			}
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

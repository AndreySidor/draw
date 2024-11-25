package actions;

import actions.base.BaseAction;
import logic.Selection;
import logic.VectorDrawing;

/**
 * Удаление выбранных фигур с рисунка
 */
public class DeleteAction extends BaseAction {

	/**
	 * Конструктор
	 * @param drawing - полотно
	 * @param selection - выбранные фигуры
	 */
	public DeleteAction(VectorDrawing drawing, Selection selection) {
		super(drawing, selection.clone());
	}

	public Boolean execute() {
		Boolean checkForExecution = selection != null && !selection.isEmpty() && drawing != null;
		if (checkForExecution) {
			drawing.removeSelected();
		}
		return checkForExecution;
	}

	public void redo() {
		execute();
	}

	public void undo() {
		drawing.insertShapes(selection.toArrayList());
	}

	public String getDescription() {
		return null;
	}

}

package actions;

import actions.base.BaseAction;
import logic.Drawing;
import logic.Selection;
import shapes.Shape;

/**
 * Удаление выбранных фигур с рисунка
 */
public class DeleteAction extends BaseAction {

	/**
	 * Конструктор
	 * @param drawing - полотно
	 * @param selection - выбранные фигуры
	 */
	public DeleteAction(Drawing drawing, Selection selection) {
		super(drawing, selection.clone());
	}

	public Boolean execute() {
		Boolean checkForExecution = selection != null && !selection.isEmpty() && drawing != null;
		if (checkForExecution) {
			for (Shape s : selection) {
				drawing.removeShape(s);
			}
		}
		return checkForExecution;
	}

	public void redo() {
		execute();
	}

	public void undo() {
		for (Shape s : selection) {
			drawing.insertShape(s);
		}
	}

	public String getDescription() {
		return null;
	}

}

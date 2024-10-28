package actions;

import actions.base.BaseAction;
import actions.base.BaseShapesAction;
import actions.base.DrawAction;
import logic.Drawing;
import logic.Selection;
import shapes.Shape;

/**
 * Удаление выбранных фигур с рисунка
 */
public class DeleteAction extends BaseAction implements DrawAction {

	/**
	 * Конструктор
	 * @param drawing - полотно
	 * @param selection - выбранные фигуры
	 */
	public DeleteAction(Drawing drawing, Selection selection) {
		super(drawing, selection.clone());
	}

	public void execute() {
		for (Shape s : selection) {
			drawing.removeShape(s);
		}
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

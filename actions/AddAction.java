package actions;

import actions.base.BaseShapesAction;
import actions.base.DrawAction;
import logic.Drawing;
import shapes.Shape;

/**
 * Добавление фигуры на рисунок
 */
public class AddAction extends BaseShapesAction implements DrawAction {

	Shape shape;

	/**
	 * Конструктор
	 * @param drawing - полотно
	 * @param shape - фигура
	 */
	public AddAction(Drawing drawing, Shape shape) {
		super(drawing);
		this.shape = shape;
	}

	public Boolean execute() {
		Boolean checkForExecution = shape != null && drawing != null;
		if (checkForExecution) {
			drawing.insertShape(shape);
		}
		return checkForExecution;
	}

	public void redo() {
		this.execute();
	}

	public void undo() {
		drawing.removeShape(shape);
	}

	public String getDescription() {
		return null;
	}

}

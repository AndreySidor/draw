package actions;

import java.awt.Point;

import actions.base.BaseSelectionsAction;
import actions.base.DrawAction;
import logic.Selection;
import shapes.Shape;

/**
 * Перемещение выбранных фигур
 */
public class MoveAction extends BaseSelectionsAction implements DrawAction {

	Point movement;

	/**
	 * Конструктор
	 * @param selection - выбранные фигуры
	 * @param movement - смещение (x, y)
	 */
	public MoveAction(Selection selection, Point movement) {
		super(selection.clone());
		this.movement = movement;
	}

	public void execute() {
		for (Shape s : selection) {
			s.move(movement.x, movement.y);
		}
	}

	public void redo() {
		execute();
	}

	public void undo() {
		for (Shape s : selection) {
			s.move(-movement.x, -movement.y);
		}
	}

	public String getDescription() {
		return null;
	}

}

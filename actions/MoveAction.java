package actions;

import actions.base.BaseSelectionsAction;
import logic.Selection;
import shapes.Shape;

import java.awt.*;

/**
 * Перемещение выбранных фигур
 */
public class MoveAction extends BaseSelectionsAction implements MergeableAction {

	Point movement;

	Boolean canMerge = true;

	/**
	 * Конструктор
	 * @param selection - выбранные фигуры
	 * @param movement - смещение (x, y)
	 */
	public MoveAction(Selection selection, Point movement) {
		super(selection.clone());
		this.movement = movement;
	}

	public Boolean execute() {
		Boolean checkForExecution = selection != null && !selection.isEmpty() && movement != null
				&& (movement.x != 0 || movement.y != 0);
		if (checkForExecution) {
			for (Shape s : selection) {
				s.move(movement.x, movement.y);
			}
		}
		return checkForExecution;
	}

	@Override
	public Boolean merge(MergeableAction action) {
		Boolean checkForMerge = action instanceof MoveAction && canMerge;
		if (checkForMerge) {
			this.movement.x += ((MoveAction) action).movement.x;
			this.movement.y += ((MoveAction) action).movement.y;
		}
		return checkForMerge;
	}

	@Override
	public void stopMerge() {
		canMerge = false;
	}

	@Override
	public Boolean canMerge() {
		return canMerge;
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

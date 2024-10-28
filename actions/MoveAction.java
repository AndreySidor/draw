package actions;

import actions.base.BaseSelectionsAction;
import logic.Selection;

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
			selection.move(movement);
		}
		return checkForExecution;
	}

	@Override
	public Boolean merge(MergeableAction action) {
		Boolean checkForMerge = action instanceof MoveAction && canMerge;
		if (checkForMerge) {
			movement.translate(((MoveAction) action).movement.x, ((MoveAction) action).movement.y);
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
		selection.move(new Point(-movement.x, -movement.y));
	}

	public String getDescription() {
		return null;
	}

}

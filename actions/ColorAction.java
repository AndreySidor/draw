package actions;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import actions.base.BaseSelectionsAction;
import actions.base.DrawAction;
import logic.Selection;
import shapes.Shape;

/**
 * Меняет цвет выбранных фигур
 */
public class ColorAction extends BaseSelectionsAction implements DrawAction {

	private Map<Shape, Color> oldColors = new HashMap<>();

	private Color newColor;

	/**
	 * Конструктор
	 * @param selection - выбранные фигуры
	 * @param newColor - новый цвет
	 */
	public ColorAction(Selection selection, Color newColor) {
		super(selection.clone());
		this.selection.forEach(item -> {
			this.oldColors.put(item, item.getColor());
		});
		this.newColor = newColor;
	}

	public Boolean execute() {
		Boolean checkForExecution = selection != null && !selection.isEmpty() && newColor != null;
		if (checkForExecution) {
			if (checkForExecution) {
				this.selection.forEach(item -> {
					item.setColor(newColor);
				});
			}
		}
		return checkForExecution;
	}

	public void redo() {
		this.execute();
	}

	public void undo() {
		this.selection.forEach(item -> {
			item.setColor(this.oldColors.get(item));
		});
	}

	public String getDescription() {
		return null;
	}

}

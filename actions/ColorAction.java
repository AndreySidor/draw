package actions;

import actions.base.BaseSelectionsAction;
import logic.Selection;
import shapes.Shape;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Меняет цвет выбранных фигур
 */
public class ColorAction extends BaseSelectionsAction {

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
			this.selection.paint(newColor);
		}
		return checkForExecution;
	}

	public void redo() {
		this.execute();
	}

	public void undo() {
		oldColors.forEach(Shape::setColor);
	}

	public String getDescription() {
		return null;
	}

}

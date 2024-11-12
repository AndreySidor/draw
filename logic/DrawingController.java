package logic;

import actions.*;
import actions.base.DrawAction;
import gui.DrawGUI;
import shapes.Shape;

import java.awt.*;

public class DrawingController {

	private Drawing drawing;
	private UndoManager undoManager;
	public DrawGUI gui;
	private Tool tool;

	public DrawingController(DrawGUI g) {
		drawing = null;
		undoManager = new UndoManager();
		gui = g;
		tool = Tool.LINE;
	}

	public void addShape(Shape s) {
		DrawAction action = new AddAction(drawing, s);
		if (action.execute()) {
			undoManager.addAction(action);
		}
	}

	public void colorSelectedShapes(Color c) {
		ColorAction action = new ColorAction(drawing.getSelection(), c);
		if (action.execute()) {
			undoManager.addAction(action);
		}
	}

	public void deleteSelectedShapes() {
		DrawAction action = new DeleteAction(drawing, drawing.getSelection());
		if (action.execute()) {
			undoManager.addAction(action);
			gui.drawingContainer.repaint();
		}
	}

	public void moveSelectedShapes(Point movement) {
		DrawAction action = new MoveAction(drawing.getSelection(), movement);
		if (action.execute()) {
			undoManager.addAction(action);
		}
	}

	public void toggleFilled() {
		DrawAction action = new FillAction(drawing.getSelection());
		if (action.execute()) {
			undoManager.addAction(action);
		}
	}

	public void selectAll() {
		drawing.selectAll();
		gui.drawingContainer.repaint();
	}

	public void select(Shape shape) {
		drawing.select(shape);
	}

	public void clearSelection() {
		drawing.clearSelection();
	}

	public Boolean hasSelections() {
		return drawing.hasSelections();
	}

	public Boolean selectionContains(Shape shape) {
		return drawing.selectionContains(shape);
	}

	public Drawing getDrawing() {
		return drawing;
	}

	public Tool getTool() {
		return tool;
	}

	public void endOfActionRecording() {
		undoManager.endOfActionRecording();
	}

	public void newDrawing(Dimension size) {
		drawing = new Drawing(size);
		if (gui != null) {
			gui.updateDrawing();
		}
	}

	public void setTool(Tool t) {
		this.tool = t;
	}

	public void redo() {
		if (this.undoManager.canRedo()) {
			this.undoManager.redo();
		}
		gui.drawingContainer.repaint();
	}

	public void undo() {
		if (this.undoManager.canUndo()) {
			this.undoManager.undo();
		}
		gui.drawingContainer.repaint();
	}
}

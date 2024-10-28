package logic;

import actions.*;
import actions.base.DrawAction;
import gui.DrawGUI;
import shapes.Shape;

import java.awt.*;

public class DrawingController {

	private Drawing drawing;
	private UndoManager undoManager;
	private Selection selection;
	private DrawGUI gui;
	private Tool tool;

	public DrawingController(DrawGUI g) {
		drawing = null;
		undoManager = new UndoManager();
		selection = new Selection();
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
		ColorAction action = new ColorAction(selection, c);
		if (action.execute()) {
			undoManager.addAction(action);
		}
	}

	public void deleteSelectedShapes() {
		DrawAction action = new DeleteAction(drawing, selection);
		if (action.execute()) {
			undoManager.addAction(action);
			drawing.repaint();
		}
	}

	public Drawing getDrawing() {
		return drawing;
	}

	public Selection getSelection() {
		return selection;
	}

	public Tool getTool() {
		return tool;
	}

	public void moveSelectedShapes(Point movement) {
		DrawAction action = new MoveAction(selection, movement);
		if (action.execute()) {
			undoManager.addAction(action);
		}
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

	public void redo() {
		if (this.undoManager.canRedo()) {
			this.undoManager.redo();
		}
		drawing.repaint();
	}

	public void selectAll() {
		selection.empty();
		for (Shape sh : drawing) {
			selection.add(sh);
		}
		drawing.repaint();

	}

	public void setTool(Tool t) {
		this.tool = t;
	}

	public void toggleFilled() {
		DrawAction action = new FillAction(selection);
		if (action.execute()) {
			undoManager.addAction(action);
		}
	}

	public void undo() {
		if (this.undoManager.canUndo()) {
			this.undoManager.undo();
		}
		drawing.repaint();
	}
}

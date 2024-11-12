package logic;

import actions.*;
import actions.base.DrawAction;
import gui.CanRepaintComponent;
import gui.ChangingPanel;
import gui.DrawGUI;
import shapes.Shape;

import java.awt.*;

public class DrawingController implements ChangingPanel {

	private CanRepaintComponent changeablePanel;
	private VectorDrawing vectorDrawing;
	private UndoManager undoManager;
	private DrawGUI gui;
	private Tool tool;

	public DrawingController(DrawGUI g) {
		vectorDrawing = null;
		undoManager = new UndoManager();
		gui = g;
		tool = Tool.LINE;
	}

	public void addShape(Shape s) {
		DrawAction action = new AddAction(vectorDrawing, s);
		if (action.execute()) {
			undoManager.addAction(action);
		}
	}

	public void colorSelectedShapes(Color c) {
		ColorAction action = new ColorAction(vectorDrawing.getSelection(), c);
		if (action.execute()) {
			undoManager.addAction(action);
		}
	}

	public void deleteSelectedShapes() {
		DrawAction action = new DeleteAction(vectorDrawing, vectorDrawing.getSelection());
		if (action.execute()) {
			undoManager.addAction(action);
			fireChangingPanel();
		}
	}

	public void moveSelectedShapes(Point movement) {
		DrawAction action = new MoveAction(vectorDrawing.getSelection(), movement);
		if (action.execute()) {
			undoManager.addAction(action);
		}
	}

	public void toggleFilled() {
		DrawAction action = new FillAction(vectorDrawing.getSelection());
		if (action.execute()) {
			undoManager.addAction(action);
		}
	}

	public void selectAll() {
		vectorDrawing.selectAll();
		fireChangingPanel();
	}

	public void select(Shape shape) {
		vectorDrawing.select(shape);
	}

	public void clearSelection() {
		vectorDrawing.clearSelection();
	}

	public Boolean hasSelections() {
		return vectorDrawing.hasSelections();
	}

	public Boolean selectionContains(Shape shape) {
		return vectorDrawing.selectionContains(shape);
	}

	public VectorDrawing getVectorDrawing() {
		return vectorDrawing;
	}

	public Tool getTool() {
		return tool;
	}

	public void endOfActionRecording() {
		undoManager.endOfActionRecording();
	}

	public void newDrawing(Dimension size) {
		vectorDrawing = new VectorDrawing(size);
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
		fireChangingPanel();
	}

	public void undo() {
		if (this.undoManager.canUndo()) {
			this.undoManager.undo();
		}
		fireChangingPanel();
	}

	@Override
	public void fireChangingPanel() {
		if (changeablePanel != null) {
			changeablePanel.repaintComponent();
		}
	}

	@Override
	public void registerChangeablePanel(CanRepaintComponent observer) {
		changeablePanel = observer;
	}

	@Override
	public void removeChangeablePanel(CanRepaintComponent observer) {
		changeablePanel = null;
	}
}

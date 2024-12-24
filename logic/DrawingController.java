package logic;

import actions.*;
import actions.base.DrawAction;
import gui.*;
import shapes.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class DrawingController implements CanDrawingChange, OnUndoManagerChangedListener {

	private VectorDrawing vectorDrawing;
	private UndoManager undoManager;
	private DrawGUI gui;
	private Tool tool;

	public DrawingController(DrawGUI g) {
		vectorDrawing = null;
		undoManager = new UndoManager();
		undoManager.addUndoManagerChangedListener(this);
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
			drawingChanged();
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
		drawingChanged();
	}

	public void select(Shape shape) {
		vectorDrawing.select(shape);
	}

	public void clearSelection() {
		vectorDrawing.clearSelection();
		drawingChanged();
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
		if (gui != null) {
			vectorDrawing = new VectorDrawing(size);
			gui.updateDrawing();
			undoManager.clear();
		}
	}

	public void setTool(Tool t) {
		this.tool = t;
	}

	public void redo() {
		if (this.undoManager.canRedo()) {
			this.undoManager.redo();
		}
		drawingChanged();
	}

	public void undo() {
		if (this.undoManager.canUndo()) {
			this.undoManager.undo();
		}
		drawingChanged();
	}

	@Override
	public void drawingChanged() {
		if (drawingChangedListener != null) {
			drawingChangedListener.onDrawingChanged();
		}
	}

	private OnDrawingChangedListener drawingChangedListener;

	private ArrayList<OnUndoEnabledListener> undoEnabledListeners = new ArrayList<>();
	private ArrayList<OnRedoEnabledListener> redoEnabledListeners = new ArrayList<>();

	@Override
	public void addDrawingChangeListener(OnDrawingChangedListener listener) {
		drawingChangedListener = listener;
	}

	@Override
	public void removeDrawingChangeListener(OnDrawingChangedListener listener) {
		drawingChangedListener = null;
	}

	public void addUndoEnabledListener(OnUndoEnabledListener listener) {
		undoEnabledListeners.add(listener);
	}

	public void removeUndoEnabledListener(OnUndoEnabledListener listener) {
		undoEnabledListeners.remove(listener);
	}

	public void addRedoEnabledListener(OnRedoEnabledListener listener) {
		redoEnabledListeners.add(listener);
	}

	public void removeRedoEnabledListener(OnRedoEnabledListener listener) {
		redoEnabledListeners.remove(listener);
	}

	@Override
	public void onUndoManagerChanged(Stack<DrawAction> undo, Stack<DrawAction> redo) {
		undoEnabledListeners.forEach(l -> {
			l.onUndoEnabledListener(!undo.isEmpty());
		});
		redoEnabledListeners.forEach(l -> {
			l.onRedoEnabledListener(!redo.isEmpty());
		});
	}
}

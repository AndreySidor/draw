package actions;

import actions.base.DrawAction;
import logic.OnUndoManagerChangedListener;

import java.util.ArrayList;
import java.util.Stack;

/**
 * UndoManager is a simplistic reusable component to support an undo-redo
 * mechanism. UndoableActions can be added in the manager, which gives a
 * centered interface for performing their undo and redo actions.
 * 
 * 
 * !!!!!!!!!!!!!!! Mostly copied from exercise 2.2 & 2.3
 * http://www.cs.hut.fi/Opinnot/T-106.1240/2010_external/tehtavat_2010.shtml
 * !!!!!!!!!!!!!!!!
 * 
 */
public class UndoManager implements UndoManagerChangeEvent {

	// Undo and redo stacks which contain the UndoableAction objects
	// When a new action is made it is put in the undo stack. When an operation
	// is undone, it is places in the redo stack.

	private Stack<DrawAction> undoStack;
	private Stack<DrawAction> redoStack;

	/**
	 * Constructs a empty Undo Manager.
	 */
	public UndoManager() {
		this.undoStack = new Stack<DrawAction>();
		this.redoStack = new Stack<DrawAction>();
	}

	/**
	 * Adds a new undoable action into this Undo Manager.
	 * 
	 * @param action
	 *            the UndoableAction to be added.
	 */
	public void addAction(DrawAction action) {
		this.redoStack.clear();
		DrawAction lastAction = null;
		if (!undoStack.isEmpty()) {
			lastAction = undoStack.peek();
		}
		if (lastAction instanceof MergeableAction && action instanceof MergeableAction) {
			if (((MergeableAction) lastAction).merge(((MergeableAction) action))) {
				return;
			}
		}
		this.undoStack.push(action);
		undoManagerChanged();
	}

	/**
	 * Помечает последние действие, как завершенное (недоступное для слияния)
	 */
	public void endOfActionRecording() {
		DrawAction action = undoStack.peek();
		if (action instanceof MergeableAction) {
			((MergeableAction) action).stopMerge();
		}
	}

	/**
	 * Tests if an redo operation can be performed at this moment.
	 * 
	 * @return boolean value telling if a redo is possible to perform.
	 */

	public boolean canRedo() {
		return !this.redoStack.isEmpty();
	}

	/**
	 * Tests if an undo operation can be performed at this moment.
	 * 
	 * @return boolean value telling if an undo is possible to perform.
	 */
	public boolean canUndo() {
		return !this.undoStack.isEmpty();
	}

	/**
	 * Redoes one action from the redo stack. The redone action then is moved to
	 * the undo stack.
	 */
	public void redo() {
		DrawAction action = this.redoStack.pop();
		action.redo();
		this.undoStack.push(action);
		undoManagerChanged();
	}

	/**
	 * Undoes one action from the undo stack. The undone action then is moved to
	 * the undo stack.
	 */
	public void undo() {
		DrawAction action = this.undoStack.pop();
		action.undo();
		this.redoStack.push(action);
		undoManagerChanged();
	}

	public void clear() {
		undoStack.clear();
		redoStack.clear();
		undoManagerChanged();
	}

	private ArrayList<OnUndoManagerChangedListener> listeners = new ArrayList<>();

	@Override
	public void undoManagerChanged() {
		listeners.forEach(l -> {
			l.onUndoManagerChanged(undoStack, redoStack);
		});
	}

	@Override
	public void addUndoManagerChangedListener(OnUndoManagerChangedListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeUndoManagerChangedListener(OnUndoManagerChangedListener listener) {
		listeners.remove(listener);
	}
}

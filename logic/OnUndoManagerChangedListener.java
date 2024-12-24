package logic;

import actions.base.DrawAction;

import java.util.EventListener;
import java.util.Stack;

public interface OnUndoManagerChangedListener extends EventListener {

    void onUndoManagerChanged(Stack<DrawAction> undo, Stack<DrawAction> redo);

}

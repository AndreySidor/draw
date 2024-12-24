package actions;

import logic.OnUndoManagerChangedListener;

public interface UndoManagerChangeEvent {

    void undoManagerChanged();

    void addUndoManagerChangedListener(OnUndoManagerChangedListener listener);

    void removeUndoManagerChangedListener(OnUndoManagerChangedListener listener);

}

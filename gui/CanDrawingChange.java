package gui;

public interface CanDrawingChange {

    void drawingChanged();

    void addDrawingChangeListener(OnDrawingChangedListener listener);

    void removeDrawingChangeListener(OnDrawingChangedListener listener);

}

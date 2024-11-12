package gui;

public interface ChangingPanel {

    void fireChangingPanel();

    void registerChangeablePanel(CanRepaintComponent observer);

    void removeChangeablePanel(CanRepaintComponent observer);

}

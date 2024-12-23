package gui.dialogs;

import logic.DrawIO;
import logic.DrawingController;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class OpenFileDialog extends JFileChooser implements Dialog {

    private DrawIO drawIO;
    private DrawingController controller;

    public OpenFileDialog(DrawIO drawIO, DrawingController controller) {
        this.drawIO = drawIO;
        this.controller = controller;
    }

    @Override
    public void showDialog() {
        this.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileFilter filter = new FileNameExtensionFilter("Draw files", "draw");
        this.addChoosableFileFilter(filter);
        this.setFileFilter(filter);
        this.showOpenDialog(null);
        File f = this.getSelectedFile();
        if (f != null) {
            try {
                if (!controller.getVectorDrawing().isEmpty()) {
                    (new SaveAsDialog(drawIO)).showDialogWithAsk();
                }
                drawIO.open(f);
            } catch (DrawIO.DrawIOException e) {
                JOptionPane.showMessageDialog(
                        null,
                        e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}

package gui.dialogs;

import logic.DrawIO;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class SaveAsDialog extends JFileChooser implements Dialog {

    private DrawIO drawIO;

    public SaveAsDialog(DrawIO drawIO) {
        this.drawIO = drawIO;
    }

    public void showDialogWithAsk() {
        String message = "Есть несохраненные данные. Вы хотите сохранить?";
        String title = "Сохранить?";

        int response = JOptionPane.showConfirmDialog(
                null,
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (response == JOptionPane.YES_OPTION) {
            (new SaveAsDialog(drawIO)).showDialog();
        }
    }

    @Override
    public void showDialog() {
        this.setFileSelectionMode(JFileChooser.FILES_ONLY);

        this.setSelectedFile(new File("new.draw"));
        FileFilter filter = new FileNameExtensionFilter("Draw files",
                "draw");
        this.addChoosableFileFilter(filter);
        this.setFileFilter(filter);
        this.showSaveDialog(null);
        File f = this.getSelectedFile();
        if (f != null) {
            try {
                drawIO.save(f);
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

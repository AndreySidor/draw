package gui.dialogs;

import logic.DrawIO;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class ExportPNGDialog extends JFileChooser {
    public ExportPNGDialog(DrawIO drawIO) {
        this.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.setDialogType(JFileChooser.CUSTOM_DIALOG);
        FileFilter filter = new FileNameExtensionFilter("Portable Network Graphics", "png");
        this.addChoosableFileFilter(filter);

        this.setSelectedFile(new File("out.png"));
        this.showSaveDialog(null);

        File f = this.getSelectedFile();
        if (f != null) {
            try {
                drawIO.exportPNG(f);
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

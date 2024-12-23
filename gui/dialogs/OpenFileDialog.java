package gui.dialogs;

import logic.DrawIO;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class OpenFileDialog extends JFileChooser {
    public OpenFileDialog(DrawIO drawIO) {
        this.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileFilter filter = new FileNameExtensionFilter("Draw files", "draw");
        this.addChoosableFileFilter(filter);
        this.setFileFilter(filter);

        this.showOpenDialog(null);
        File f = this.getSelectedFile();
        if (f != null) {
            drawIO.open(f);
        }
    }
}

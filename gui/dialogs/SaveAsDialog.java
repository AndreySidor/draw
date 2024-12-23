package gui.dialogs;

import logic.DrawIO;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class SaveAsDialog extends JFileChooser {
    public SaveAsDialog(DrawIO drawIO) {
        this.setFileSelectionMode(JFileChooser.FILES_ONLY);

        this.setSelectedFile(new File("new.draw"));
        FileFilter filter = new FileNameExtensionFilter("Draw files",
                "draw");
        this.addChoosableFileFilter(filter);
        this.setFileFilter(filter);

        this.showSaveDialog(null);

        File f = this.getSelectedFile();
        if (f != null) {
            drawIO.save(f);
        }
    }
}

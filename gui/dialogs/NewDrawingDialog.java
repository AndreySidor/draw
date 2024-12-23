package gui.dialogs;

import logic.DrawingController;

import javax.swing.*;
import java.awt.*;

public class NewDrawingDialog extends JDialog {

    public NewDrawingDialog(DrawingController controller) {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setTitle("Drawing size");

        JPanel jp;
        jp = new JPanel();
        jp.add(new JLabel("Width"));
        JSpinner widthSpinner = new JSpinner(new SpinnerNumberModel(600, 10, 4096, 1));
        jp.add(widthSpinner);
        this.getContentPane().add(jp);

        jp = new JPanel();
        jp.add(new JLabel("Height"));
        JSpinner heightSpinner = new JSpinner(new SpinnerNumberModel(450, 10, 4096, 1));
        jp.add(heightSpinner);
        this.getContentPane().add(jp);

        jp = new JPanel();
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        jp.add(ok);
        jp.add(cancel);
        this.getContentPane().add(jp);

        ok.addActionListener(e -> {
            controller.newDrawing(
                    new Dimension((Integer) widthSpinner.getValue(), (Integer) heightSpinner.getValue())
            );
            setVisible(false);
        });

        cancel.addActionListener(e -> setVisible(false));

        this.setModal(true);
        this.pack();
        setVisible(true);
    }
}
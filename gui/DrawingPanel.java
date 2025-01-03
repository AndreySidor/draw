package gui;

import logic.VectorDrawing;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel implements OnDrawingChangedListener {

    private VectorDrawing vectorDrawing;

    public DrawingPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.WHITE);
    }

    public void setVectorDrawing(VectorDrawing vectorDrawing) {
        this.removeAll();
        this.vectorDrawing = vectorDrawing;
        this.setPreferredSize(vectorDrawing.getDimension());
        if (this.getParent() != null) {
            this.getParent().setPreferredSize(this.getPreferredSize());
        }
    }

    public BufferedImage snapshot() {
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        print(bi.createGraphics());
        return bi;
    }

    @Override
    public void removeAll() {
        super.removeAll();
        vectorDrawing = null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        vectorDrawing.draw(g);
    }

    @Override
    public void onDrawingChanged() {
        repaint();
    }
}

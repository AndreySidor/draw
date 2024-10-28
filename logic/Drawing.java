package logic;

import shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Drawing extends JPanel implements Iterable<Shape> {

	private static final long serialVersionUID = 0;

	private ArrayList<Shape> shapes = new ArrayList<>();

	private Selection selection = new Selection();

	public Drawing(Dimension size) {
		this.setPreferredSize(size);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.WHITE);
	}

	public BufferedImage getImage() {

		BufferedImage bi = new BufferedImage(getPreferredSize().width,
				getPreferredSize().height, BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.createGraphics();
		this.print(g);
		return bi;
	}

	public Shape getShapeAt(Point p) {
		int index = shapes.size() - 1;
		while (index >= 0) {

			if (shapes.get(index).includes(p)) {
				return shapes.get(index);
			}
			index--;
		}
		return null;
	}

	public Selection getSelection() {
		return selection;
	}

	public void selectAll() {
		selection.empty();
		shapes.forEach(item -> {
			selection.add(item);
		});
	}

	public void select(Shape shape) {
		selection.add(shape);
	}

	public void clearSelection() {
		selection.empty();
	}

	public Boolean selectionContains(Shape shape) {
		return selection.contains(shape);
	}

	public Boolean hasSelections() {
		return selection.isEmpty();
	}

	public void insertShape(Shape s) {
		shapes.add(s);
	}

	public void removeSelected() {
		selection.forEach(this::removeShape);
	}

	@Override
	public Iterator<Shape> iterator() {
		return shapes.iterator();
	}

	public void listShapes() {
		System.out.println("---");
		for (Shape s : shapes) {
			System.out.println(s);
		}
		System.out.println("---");
	}

	public void lower(Shape s) {
		int index = shapes.indexOf(s);
		if (index < shapes.size() - 1) {
			shapes.remove(s);
			shapes.add(index, s);
		}
	}

	public int nShapes() {
		return shapes.size();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		for (Shape s : shapes) {
			s.draw(g);
		}
	}

	public void raise(Shape s) {
		int index = shapes.indexOf(s);
		if (index > 0) {
			shapes.remove(s);
			shapes.add(--index, s);
		}
	}

	public void removeShape(Shape s) {
		shapes.remove(s);
	}

}

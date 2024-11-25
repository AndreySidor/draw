package logic;

import shapes.FillableShape;
import shapes.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Selection implements Iterable<Shape> {

	private ArrayList<Shape> selected;

	public Selection() {
		selected = new ArrayList<Shape>(0);
	}

	public Selection(ArrayList<Shape> list) {
		selected = list;
	}

	public void add(Shape s) {
		if (!selected.contains(s)) {
			selected.add(s);
			s.setSelected(true);
		}
	}

	@SuppressWarnings("unchecked")
	public Selection clone() {
		ArrayList<Shape> clone = (ArrayList<Shape>) selected.clone();
		return new Selection(clone);
	}

	public boolean contains(Shape s) {
		return selected.contains(s);
	}

	public void empty() {
		for (Shape s : selected) {
			s.setSelected(false);
		}
		selected.clear();
	}

	public boolean isEmpty() {
		return selected.isEmpty();
	}

	public Iterator<Shape> iterator() {
		return selected.iterator();
	}

	public ArrayList<Shape> toArrayList() {
		return new ArrayList<>(selected);
	}

	public int nShapes() {
		return selected.size();
	}

	public void paint(Color color) {
		selected.forEach(item -> item.setColor(color));
	}

	public void changeFilling() {
		selected.forEach(item -> {
			if (item instanceof FillableShape) {
				FillableShape fs = (FillableShape) item;
				fs.setFilled(!(fs).getFilled());
			}
		});
	}

	public void move(Point movement) {
		selected.forEach(item -> item.move(movement.x, movement.y));
	}

	public String toString() {
		String str;
		str = "Selection contents:\n";
		for (Shape s : selected) {
			str += s.toString() + "\n";
		}
		str += "\n";
		return str;
	}
}

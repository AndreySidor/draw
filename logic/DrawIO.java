package logic;

import gui.DrawingPanel;
import shapes.Rectangle;
import shapes.Shape;
import shapes.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class DrawIO {

	DrawingController controller;
	DrawingPanel panel;

	public DrawIO(DrawingController controller, DrawingPanel panel) {
		this.controller = controller;
		this.panel = panel;
	}

	public void exportPNG(File file) {
		try {
			controller.clearSelection();
			BufferedImage bi = panel.snapshot();
			ImageIO.write(bi, "png", file);
		}
		catch (IOException e) {
		}
	}

	public Point getPoint(String str) {
		String[] p = str.split(",");

		return new Point(Integer.parseInt(p[0].trim()), Integer.parseInt(p[1]
				.trim()));

	}

	public void open(File f) {
		int lineNumber = 1;
		try {
			BufferedReader in = new BufferedReader(new FileReader(f));
			String str;

			Point p = getPoint(in.readLine());
			controller.newDrawing(new Dimension(p.x, p.y));

			while ((str = in.readLine()) != null) {
				try {
					lineNumber++;
					if (str.length() == 0) {
						continue;
					}

					String[] parts = str.split(";");

					Point p1 = getPoint(parts[1]);
					Point p2 = getPoint(parts[2]);
					Shape sh = null;
					parts[0] = parts[0].trim();

					if (parts[0].equals("rect")) {
						boolean fill = Integer.parseInt(parts[4].trim()) == 0 ? false
								: true;
						sh = new Rectangle(p1.x, p1.y, fill);
					}
					else if (parts[0].equals("circ")) {
						boolean fill = Integer.parseInt(parts[4].trim()) == 0 ? false
								: true;
						sh = new Circle(p1.x, p1.y, fill);
					}
					else if (parts[0].equals("line")) {
						sh = new Line(p1.x, p1.y);
					}
					else if (parts[0].equals("text")) {
						int fontSize = Integer.parseInt(parts[4].trim());
						sh = new Text(p1.x, p1.y, fontSize, parts[5]);
					}
					else {
						throw new ArrayIndexOutOfBoundsException();
					}

					if (sh != null) {
						sh.setPoint2(p2);
						sh
								.setColor(new Color(Integer.parseInt(parts[3]
										.trim())));
						controller.getVectorDrawing().insertShape(sh);
					}
				}
				catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Could not read line " + lineNumber
							+ " in file \"" + f + "\"");
				}
				catch (NumberFormatException e) {
					System.out.println("Could not read line " + lineNumber
							+ " in file \"" + f + "\"");
				}

			}

			in.close();
		}
		catch (IOException e) {
			e.printStackTrace(System.out);
		}
	}

	public void save(File f) {
		VectorDrawing d = controller.getVectorDrawing();

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(f));

			out.write(d.getDimension().width + ","
					+ d.getDimension().height + "\n");

			for (Shape s : controller.getVectorDrawing()) {
				out.write(s.toString() + "\n");
			}
			out.close();

		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Could not save the drawing.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

	}
}

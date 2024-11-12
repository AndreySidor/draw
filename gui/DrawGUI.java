package gui;

import logic.Drawing;
import logic.DrawingController;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Graphical user interface for the Drawing editor "Draw"
 * 
 * @author Alex Lagerstedt
 * 
 */

public class DrawGUI extends JFrame {

	/**
	 * A simple container that contains a Drawing instance and keeps it
	 * centered.
	 * 
	 * @author Alex Lagerstedt
	 * 
	 */
	public class DrawingContainer extends JPanel {

		private static final long serialVersionUID = 0;

		private Drawing drawingPanel;

		public DrawingContainer() {
			setBackground(Color.WHITE);
		}

		public void setDrawing(Drawing drawing) {
			removeAll();
			drawingPanel = drawing;
			setPreferredSize(drawing.getDimension());
			pack();
		}

		public BufferedImage getImage() {
			BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			print(bi.createGraphics());
			return bi;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (drawingPanel != null) {
				drawingPanel.draw(g);
			}
		}
	}

	public class StatusBar extends JLabel {

		private static final long serialVersionUID = 0;

		public StatusBar() {
			super();
			super.setPreferredSize(new Dimension(100, 16));
			setMessage("Ready");
		}

		public void setMessage(String message) {
			setText(" " + message);
		}
	}

	private DrawingController controller;
	public DrawingContainer drawingContainer;
	private MouseListener mouse;
	private ToolBox tools;
	private JScrollPane scrollpane;

	// private StatusBar statusBar;

	private static final long serialVersionUID = 0;

	public static void main(String[] args) {

		new DrawGUI();

	}

	/**
	 * Constructs a new graphical user interface for the program and shows it.
	 */
	public DrawGUI() {

		this.setTitle("Draw 0.2");
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawingContainer = new DrawingContainer();
		scrollpane = new JScrollPane(drawingContainer);

		controller = new DrawingController(this);
		tools = new ToolBox(controller);
		controller.newDrawing(new Dimension(500, 380));

		mouse = new MouseListener(controller, tools);
		drawingContainer.addMouseListener(mouse);
		drawingContainer.addMouseMotionListener(mouse);

		// statusBar = new StatusBar();

		getContentPane().add(tools, BorderLayout.WEST);
		getContentPane().add(scrollpane, BorderLayout.CENTER);
		// getContentPane().add(statusBar, BorderLayout.SOUTH);

		MenuListener mainMenuListener = new MenuListener(controller);
		JMenuBar mainMenu = new MainMenu(mainMenuListener);
		this.setJMenuBar(mainMenu);

		pack();
		setVisible(true);

	}

	/**
	 * Updates the GUI to show the Drawing instance that is currently controlled
	 * by the DrawingController.
	 */
	public void updateDrawing() {

		drawingContainer.setDrawing(controller.getDrawing());
		scrollpane.setPreferredSize(new Dimension(drawingContainer
				.getPreferredSize().width + 100, drawingContainer
				.getPreferredSize().height + 100));
		pack();
		repaint();
	}
}

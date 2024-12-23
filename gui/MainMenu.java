package gui;

import gui.dialogs.ExportPNGDialog;
import gui.dialogs.NewDrawingDialog;
import gui.dialogs.OpenFileDialog;
import gui.dialogs.SaveAsDialog;
import logic.DrawIO;
import logic.DrawingController;

import javax.swing.*;

/**
 * 
 * Represents a main menu for a DrawGUI
 * 
 * @author Alex Lagerstedt
 * 
 */
public class MainMenu extends JMenuBar {

	private DrawingController controller;
	private DrawingPanel panel;

	public MainMenu(DrawingController controller, DrawingPanel panel) {
		// Установка контроллера
		this.controller = controller;

		// Установка панели отрисовки
		this.panel = panel;

		// Работа с файлами
		DrawIO drawIO = new DrawIO(controller, panel);

		// Работа с файлом (панель рисования)
		JMenu fileMenu = new JMenu("File");
		JMenuItem newDrawing = new JMenuItem("New", new ImageIcon("img/document-new.png"));
		JMenuItem open = new JMenuItem("Open", new ImageIcon("img/document-open.png"));
		JMenuItem saveAs = new JMenuItem("Save as", new ImageIcon("img/document-save-as.png"));
		JMenuItem export = new JMenuItem("Export PNG", new ImageIcon("img/document-save-as.png"));
		JMenuItem quit = new JMenuItem("Quit", new ImageIcon("img/system-log-out.png"));

		// Undo / Redo
		JMenu editMenu = new JMenu("Edit");
		JMenuItem undo = new JMenuItem("Undo", new ImageIcon("img/edit-undo.png"));
		JMenuItem redo = new JMenuItem("Redo", new ImageIcon("img/edit-redo.png"));

		// Работа с выбранными элементами
		JMenu selectionMenu = new JMenu("Selection");
		JMenuItem selectAll = new JMenuItem("Select selectAll", new ImageIcon("img/edit-select-all.png"));
		JMenuItem clearSelection = new JMenuItem("Clear selection", new ImageIcon("img/edit-clear.png"));
		JMenuItem deleteSelectionItems = new JMenuItem("Delete", new ImageIcon("img/edit-delete.png"));

		// Справка по программе
		final JMenu helpMenu = new JMenu("Help");
		final JMenuItem about = new JMenuItem("About", new ImageIcon("img/dialog-information.png"));

		// Установка hotkeys на ивенты
		redo.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.Event.CTRL_MASK));
		open.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.Event.CTRL_MASK));
		newDrawing.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.Event.CTRL_MASK));
		undo.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.Event.CTRL_MASK));
		quit.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.Event.CTRL_MASK));
		export.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.Event.CTRL_MASK));
		saveAs.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.Event.CTRL_MASK));
		clearSelection.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.Event.CTRL_MASK));
		selectAll.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.Event.CTRL_MASK));
		deleteSelectionItems.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));

		// Установка действий на кнопки
		quit.addActionListener(e -> {
			if (!controller.getVectorDrawing().isEmpty()) {
				(new SaveAsDialog(drawIO)).showDialogWithAsk();
			}
			System.exit(0);
		});
		newDrawing.addActionListener(e -> (new NewDrawingDialog(drawIO, controller)).showDialog());
		open.addActionListener(e -> (new OpenFileDialog(drawIO, controller)).showDialog());
		saveAs.addActionListener(e -> (new SaveAsDialog(drawIO)).showDialog());
		export.addActionListener(e -> (new ExportPNGDialog(drawIO)).showDialog());
		undo.addActionListener(e -> controller.undo());
		redo.addActionListener(e -> controller.redo());
		selectAll.addActionListener(e -> controller.selectAll());
		clearSelection.addActionListener(e -> controller.clearSelection());
		deleteSelectionItems.addActionListener(e -> controller.deleteSelectedShapes());

		// Установка элементов в свои позиции
		fileMenu.add(newDrawing);
		fileMenu.add(open);
		fileMenu.addSeparator();
		fileMenu.add(saveAs);
		fileMenu.add(export);
		fileMenu.addSeparator();
		fileMenu.add(quit);

		editMenu.add(undo);
		editMenu.add(redo);

		selectionMenu.add(selectAll);
		selectionMenu.add(clearSelection);
		selectionMenu.add(deleteSelectionItems);

		helpMenu.add(about);

		// Сборка меню программы
		this.add(fileMenu);
		this.add(editMenu);
		this.add(selectionMenu);
		this.add(helpMenu);
	}
}

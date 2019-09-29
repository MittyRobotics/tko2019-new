package com.amhsrobotics.autonomous.graph.rewrite;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JMenuBar{
	private static MenuBar ourInstance = new MenuBar();

	public static MenuBar getInstance() {
		return ourInstance;
	}

	private MenuBar() {
		super();
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu viewMenu = new JMenu("View");

		fileMenu.setForeground(WindowColors.TEXT_COLOR);
		editMenu.setForeground(WindowColors.TEXT_COLOR);
		viewMenu.setForeground(WindowColors.TEXT_COLOR);

		add(fileMenu);
		add(editMenu);
		add(viewMenu);
		setBackground(WindowColors.BACKGROUND_COLOR);

		setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));

	}

	public void init(){

	}
}

package com.amhsrobotics.autonomous.graph;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JMenuBar {
	public MenuBar(){
		super();
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu viewMenu = new JMenu("View");

		fileMenu.setForeground(new Color(158, 159, 157));
		editMenu.setForeground(new Color(158, 159, 157));
		viewMenu.setForeground(new Color(158, 159, 157));

		add(fileMenu);
		add(editMenu);
		add(viewMenu);
		setBackground(new Color(71, 71, 71));

		setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));

	}
}

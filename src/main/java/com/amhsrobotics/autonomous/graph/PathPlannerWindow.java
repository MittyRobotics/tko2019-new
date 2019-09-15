package com.amhsrobotics.autonomous.graph;

import javax.swing.*;
import java.awt.*;

public class PathPlannerWindow extends JFrame{

	public PathPlannerWindow(){
		super("Auton Path Planner");

		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);


		setLayout(new BorderLayout());

		getContentPane().setBackground(new Color(77,255, 0));


		MainPanel mainPanel = new MainPanel();

		getContentPane().add(mainPanel, BorderLayout.EAST);

		getContentPane().add(new SideBarPanel(),BorderLayout.WEST) ;

		setJMenuBar(new MenuBar());
		pack();



	}


	public void updateWindow(){
		repaint();
		revalidate();
	}
}

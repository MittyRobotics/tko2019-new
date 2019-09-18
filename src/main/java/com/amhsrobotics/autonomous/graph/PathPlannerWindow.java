package com.amhsrobotics.autonomous.graph;

import com.amhsrobotics.autonomous.constants.AutoPaths;
import com.amhsrobotics.purepursuit.Waypoint;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;

public class PathPlannerWindow extends JFrame{

	static PathPlannerWindow instance = new PathPlannerWindow();

	public static PathPlannerWindow getInstance(){
		return instance;
	}

	public MainPanel mainPanel;

	private PathPlannerWindow(){
		super("Auton Path Planner");

		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);


		setLayout(new BorderLayout());

		getContentPane().setBackground(new Color(77,255, 0));


		MainPanel mainPanel = new MainPanel();
		this.mainPanel = mainPanel;

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

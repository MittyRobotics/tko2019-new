package com.amhsrobotics.autonomous.graph;

import com.amhsrobotics.purepursuit.Path;
import com.amhsrobotics.purepursuit.PathGenerator;
import com.amhsrobotics.purepursuit.Waypoint;
import com.amhsrobotics.purepursuit.enums.PathType;

import javax.swing.*;
import java.awt.*;

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

	public void setGraphType(GraphType type){
		if(type==GraphType.PATH){
			mainPanel.getGraphVelocity().setVisible(false);
			mainPanel.getGraphAutoPath().setVisible(true);
			mainPanel.add(mainPanel.getGraphAutoPath());
		}
		if(type==GraphType.VELOCITY){
			mainPanel.getGraphVelocity().setVisible(true);
			mainPanel.getGraphAutoPath().setVisible(false);
			mainPanel.add(mainPanel.getGraphVelocity());
		}
	}

	public Path createPath(Waypoint[] waypoints){
		return PathGenerator.getInstance().generate(waypoints, PathType.CUBIC_HERMITE_PATH, 5, 5, 40, 20);
	}
}

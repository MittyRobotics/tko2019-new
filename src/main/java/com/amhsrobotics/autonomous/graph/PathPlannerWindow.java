package com.amhsrobotics.autonomous.graph;

import com.amhsrobotics.autonomous.constants.AutoPathSequences;
import com.amhsrobotics.autonomous.movement.PathProperties;
import com.amhsrobotics.autonomous.movement.PathSequence;
import com.amhsrobotics.purepursuit.Path;
import com.amhsrobotics.purepursuit.PathFollower;
import com.amhsrobotics.purepursuit.PathGenerator;
import com.amhsrobotics.purepursuit.Waypoint;
import com.amhsrobotics.purepursuit.enums.PathType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PathPlannerWindow extends JFrame {

	static PathPlannerWindow instance = new PathPlannerWindow();

	public static PathPlannerWindow getInstance() {
		return instance;
	}

	public MainPanel mainPanel;


	private ArrayList<PathSequence> sequences = new ArrayList<>();

	private PathSequence selectedSequence;

	private PathProperties selectedProperties;



	private SideBarPanel sideBarPanel;
	private PathPlannerWindow() {
		super("Auton Path Planner");

		for(int i = 0; i < AutoPathSequences.sequences.length; i++){
			sequences.add(AutoPathSequences.sequences[i]);
		}

		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);

		//Set path follow kCurvature
		PathGenerator.getInstance().setPathKCurvature(2);

		setLayout(new BorderLayout());

		getContentPane().setBackground(new Color(77, 255, 0));


		MainPanel mainPanel = new MainPanel();
		this.mainPanel = mainPanel;

		getContentPane().add(mainPanel, BorderLayout.EAST);

		SideBarPanel sideBarPanel = new SideBarPanel();
		this.sideBarPanel = sideBarPanel;
		sideBarPanel.createInitNodes(sequences);
		getContentPane().add(sideBarPanel, BorderLayout.WEST);

		setJMenuBar(new MenuBar());
		pack();



	}


	public void updateWindow() {
		repaint();
		revalidate();

	}

	public void setGraphType(GraphType type) {
		if (type == GraphType.PATH) {
			mainPanel.getGraphVelocity().setVisible(false);
			mainPanel.getGraphAutoPath().setVisible(true);
			mainPanel.add(mainPanel.getGraphAutoPath());
		}
		if (type == GraphType.VELOCITY) {
			mainPanel.getGraphVelocity().setVisible(true);
			mainPanel.getGraphAutoPath().setVisible(false);
			mainPanel.add(mainPanel.getGraphVelocity());
		}
	}




	public MainPanel getMainPanel() {
		return mainPanel;
	}

	public void addSequence(String name, PathProperties[] properties){
		sequences.add(new PathSequence(name,properties));
	}

	public void setMainPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public PathSequence getSelectedSequence() {
		return selectedSequence;
	}

	public void setSelectedSequence(PathSequence selectedSequence) {
		this.selectedSequence = selectedSequence;
	}

	public PathProperties getSelectedProperties() {
		return selectedProperties;
	}

	public void setSelectedProperties(PathProperties selectedProperties) {
		this.selectedProperties = selectedProperties;
	}


	public ArrayList<PathSequence> getSequences() {
		return sequences;
	}

	public void setSequences(ArrayList<PathSequence> sequences) {
		this.sequences = sequences;
	}

	public SideBarPanel getSideBarPanel() {
		return sideBarPanel;
	}

	public void setSideBarPanel(SideBarPanel sideBarPanel) {
		this.sideBarPanel = sideBarPanel;
	}
}

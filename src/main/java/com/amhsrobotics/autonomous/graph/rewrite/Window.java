package com.amhsrobotics.autonomous.graph.rewrite;

import com.amhsrobotics.autonomous.constants.AutoPaths;
import com.amhsrobotics.autonomous.movement.PathProperties;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Window extends JFrame {
	private static Window ourInstance = new Window();

	public static Window getInstance() {
		return ourInstance;
	}

	private ArrayList<PathProperties> paths = new ArrayList<>();

	private int currentSelectedPathPropertiesIndex;

	private Window() {
		super("Auton Path Planner");

		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setLayout(new BorderLayout());
		getContentPane().setBackground(Color.BLACK);



	}

	public void init(){
		add(GraphPanel.getInstance(), BorderLayout.EAST);
		add(SidebarPanel.getInstance(),BorderLayout.WEST);

		setJMenuBar(MenuBar.getInstance());

		pack();

		for(PathProperties properties : AutoPaths.paths){
			paths.add(properties);
		}
	}



	public void setNewSelectedPath(int index){
		currentSelectedPathPropertiesIndex = index;
	}

	public ArrayList<PathProperties> getPaths() {
		return paths;
	}

	public void setPaths(ArrayList<PathProperties> paths) {
		this.paths = paths;
	}

	public int getCurrentSelectedPathPropertiesIndex() {
		return currentSelectedPathPropertiesIndex;
	}

	public void setCurrentSelectedPathPropertiesIndex(int currentSelectedPathPropertiesIndex) {
		this.currentSelectedPathPropertiesIndex = currentSelectedPathPropertiesIndex;
	}
}

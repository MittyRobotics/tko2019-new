package com.amhsrobotics.autonomous.graph.rewrite;

public class PathPlanner {
	public static void main(String[] args) {
		Window.getInstance();
		GraphPanel.getInstance();
		MenuBar.getInstance();
		PathTree.getInstance();
		SidebarPanel.getInstance();
		GraphAutoPath.getInstance();
		PropertiesPanel.getInstance();
		TextInput.getInstance();
		WaypointPanel.getInstance();

		Window.getInstance().init();
		GraphPanel.getInstance().init();
		MenuBar.getInstance().init();
		PathTree.getInstance().init();
		SidebarPanel.getInstance().init();
		TextInput.getInstance().init();
		PropertiesPanel.getInstance().init();
		WaypointPanel.getInstance().init();
	}
}

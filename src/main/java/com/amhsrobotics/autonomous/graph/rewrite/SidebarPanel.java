package com.amhsrobotics.autonomous.graph.rewrite;

import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends InputJPanel {
	private static SidebarPanel ourInstance = new SidebarPanel();

	public static SidebarPanel getInstance() {
		return ourInstance;
	}

	private SidebarPanel() {
		super();
		setBackground(WindowColors.BACKGROUND_COLOR);
		setPreferredSize(new Dimension(260,500));
		setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));


	}

	public void init(){
		add(PathTree.getInstance());
		add(PropertiesPanel.getInstance());
		add(WaypointPanel.getInstance());
		//hideProperties();
	}

	@Override
	public void inputText(String text){

	}

	public void hideProperties(){
		remove(PropertiesPanel.getInstance());
	}

	public void showProperties(){
		//add(PropertiesPanel.getInstance());
	}
}

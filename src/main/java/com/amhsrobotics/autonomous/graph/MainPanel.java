package com.amhsrobotics.autonomous.graph;

import com.amhsrobotics.autonomous.constants.AutoPaths;
import com.amhsrobotics.purepursuit.Waypoint;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {


	public GraphAutonPoints graphWindow;

	public MainPanel(){

		super();

		GraphAutonPoints graphWindow = new GraphAutonPoints("test");

		this.graphWindow = graphWindow;

		setBackground(new Color(0, 0, 0));


		setLayout(new BorderLayout());
		add(graphWindow, BorderLayout.NORTH);
		add(new TextInput(graphWindow.getPreferredSize()), BorderLayout.SOUTH);

		setBorder(BorderFactory.createEmptyBorder(1,1,2,2));

	}
}

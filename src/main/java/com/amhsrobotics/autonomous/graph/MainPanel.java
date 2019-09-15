package com.amhsrobotics.autonomous.graph;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

	public MainPanel(){

		super();

		GraphAutonPoints graphWindow = new GraphAutonPoints("test");

		setBackground(new Color(0, 0, 0));


		setLayout(new BorderLayout());
		add(graphWindow, BorderLayout.NORTH);
		add(new TextInput(graphWindow.getPreferredSize()), BorderLayout.SOUTH);

		setBorder(BorderFactory.createEmptyBorder(1,1,2,2));

	}
}

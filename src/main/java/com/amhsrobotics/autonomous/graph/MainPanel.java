package com.amhsrobotics.autonomous.graph;

import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {


	public GraphAutoPath graphAutoPath;
	public GraphVelocity graphVelocity;


	private TextInput textInput;

	public MainPanel() {

		super();

//		GraphAutonPoints graphWindow = new GraphAutonPoints("test");

		GraphAutoPath graphAutoPath = new GraphAutoPath("path");
		this.graphAutoPath = graphAutoPath;
		GraphVelocity graphVelocity = new GraphVelocity("velocity");
		this.graphVelocity = graphVelocity;

		setBackground(new Color(0, 0, 0));

		setLayout(new BorderLayout());

		add(graphAutoPath, BorderLayout.NORTH);



		TextInput textInput = new TextInput(graphAutoPath.getPreferredSize());

		this.textInput = textInput;

		add(textInput, BorderLayout.SOUTH);





		setBorder(BorderFactory.createEmptyBorder(1, 1, 2, 2));


	}


	public GraphAutoPath getGraphAutoPath(){
		return graphAutoPath;
	}
	public GraphVelocity getGraphVelocity(){
		return graphVelocity;
	}


	public TextInput getTextInput() {
		return textInput;
	}

	public void setTextInput(TextInput textInput) {
		this.textInput = textInput;
	}
}

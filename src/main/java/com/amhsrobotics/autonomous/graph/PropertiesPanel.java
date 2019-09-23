package com.amhsrobotics.autonomous.graph;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class PropertiesPanel extends JPanel {

	private PropertiesTextField lookaheadText;
	private PropertiesTextField kCurvatureText;
	private PropertiesTextField maxAccelText;
	private PropertiesTextField maxDecelText;
	private PropertiesTextField maxVelText;

	public PropertiesPanel(){
		super();

		setPreferredSize(new Dimension(250,200));
		setSize(new Dimension(250,200));
		setBackground((new Color(80, 80, 80)));
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		lookaheadText = new PropertiesTextField("Lookahead Distance: ", "in", "lookaheadDist = ", 15);
		kCurvatureText = new PropertiesTextField("kCurvature: ", "", "lookaheadDist = ", 0.8);
		maxAccelText = new PropertiesTextField("Max Acceleration: ", "in/s^2", "maxAccel = ", 100);
		maxDecelText = new PropertiesTextField("Max Deceleration: ", "in/s^2", "maxDecel = ", 100);
		maxVelText = new PropertiesTextField("Max Velocity: ", "in/s", "maxVel = ", 200);

		add(lookaheadText);
		add(kCurvatureText);
		add(maxAccelText);
		add(maxDecelText);
		add(maxVelText);
	}
}

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

		lookaheadText = new PropertiesTextField("Lookahead Distance: " + 15 + "in", "lookaheadDist = ");
		kCurvatureText = new PropertiesTextField("kCurvature: " + 0.8, "kCurvature = ");
		maxAccelText = new PropertiesTextField("Max Acceleration: " + 100 + "in/s^2", "maxAcceleration = ");
		maxDecelText = new PropertiesTextField("Max Deceleration: " + 100 + "in/s^2", "maxDeceleration = ");
		maxVelText = new PropertiesTextField("Max Velocity: " + 200 + "in/s", "maxVelocity = ");

		add(lookaheadText);
		add(kCurvatureText);
		add(maxAccelText);
		add(maxDecelText);
		add(maxVelText);
	}
}

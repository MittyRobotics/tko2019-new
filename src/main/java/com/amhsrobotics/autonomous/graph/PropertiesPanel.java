package com.amhsrobotics.autonomous.graph;

import com.amhsrobotics.autonomous.enums.PathPropertyType;

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

		lookaheadText = new PropertiesTextField("Lookahead Distance: ", "in", "lookDist = ", PathPropertyType.LOOKAHEAD);
		kCurvatureText = new PropertiesTextField("kCurvature: ", "", "kCurvature = ", PathPropertyType.KCURVATURE);
		maxAccelText = new PropertiesTextField("Max Acceleration: ", "in/s^2", "maxAccel = ", PathPropertyType.MAXACCEL);
		maxDecelText = new PropertiesTextField("Max Deceleration: ", "in/s^2", "maxDecel = ", PathPropertyType.MAXDECEL);
		maxVelText = new PropertiesTextField("Max Velocity: ", "in/s", "maxVel = ", PathPropertyType.MAXVEL);

		add(lookaheadText);
		add(kCurvatureText);
		add(maxAccelText);
		add(maxDecelText);
		add(maxVelText);
	}

	public void updateSelection(){
		lookaheadText.updateSelection();
		kCurvatureText.updateSelection();
		maxAccelText.updateSelection();
		maxDecelText.updateSelection();
		maxVelText.updateSelection();
	}

	public PropertiesTextField getLookaheadText() {
		return lookaheadText;
	}

	public void setLookaheadText(PropertiesTextField lookaheadText) {
		this.lookaheadText = lookaheadText;
	}

	public PropertiesTextField getkCurvatureText() {
		return kCurvatureText;
	}

	public void setkCurvatureText(PropertiesTextField kCurvatureText) {
		this.kCurvatureText = kCurvatureText;
	}

	public PropertiesTextField getMaxAccelText() {
		return maxAccelText;
	}

	public void setMaxAccelText(PropertiesTextField maxAccelText) {
		this.maxAccelText = maxAccelText;
	}

	public PropertiesTextField getMaxDecelText() {
		return maxDecelText;
	}

	public void setMaxDecelText(PropertiesTextField maxDecelText) {
		this.maxDecelText = maxDecelText;
	}

	public PropertiesTextField getMaxVelText() {
		return maxVelText;
	}

	public void setMaxVelText(PropertiesTextField maxVelText) {
		this.maxVelText = maxVelText;
	}

}

package com.amhsrobotics.autonomous.graph;

import com.amhsrobotics.autonomous.movement.PathProperties;
import com.amhsrobotics.autonomous.enums.PathPropertyType;
import com.amhsrobotics.purepursuit.Path;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PropertiesTextField extends JTextField {


	private String baseText;
	private String units;
	private PathPropertyType propertyType;

	public PropertiesTextField(String baseText, String units, String promptText, PathPropertyType propertyType){
		super(baseText + 0 + units);

		this.propertyType = propertyType;

		this.baseText = baseText;
		this.units = units;

		setEditable(false);
		setPreferredSize(new Dimension(200,20));
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(100, 100, 100));
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

		PropertiesTextField propertiesTextField = this;

		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()==2){
					PathPlannerWindow.getInstance().getMainPanel().getTextInput().setPrompt(promptText,propertiesTextField);
				}
			}
		});
	}

	public void updateSelection(){
		setText(baseText + PathPlannerWindow.getInstance().getSelectedSequence().getPathProperties()[0].getProperty(propertyType) + units);
	}

	public void setProperty(double newProperty){
		setText(baseText + newProperty + units);
		Path[] paths = new Path[ PathPlannerWindow.getInstance().getSelectedSequence().getPathProperties().length];
		for(int i = 0; i < PathPlannerWindow.getInstance().getSelectedSequence().getPathProperties().length; i++){
			PathPlannerWindow.getInstance().getSelectedSequence().getPathProperties()[i].setProperty(propertyType, newProperty);
			PathPlannerWindow.getInstance().getSelectedSequence().getPathProperties()[i].regeneratePath();
			paths[i] = 	PathPlannerWindow.getInstance().getSelectedSequence().getPathProperties()[i].getPath();

		}
		PathPlannerWindow.getInstance().getSelectedSequence().setPaths(paths);
	}


	public String getBaseText() {
		return baseText;
	}


	public void setBaseText(String baseText) {
		this.baseText = baseText;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}



}

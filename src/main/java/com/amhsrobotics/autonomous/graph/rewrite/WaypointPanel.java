package com.amhsrobotics.autonomous.graph.rewrite;

import com.amhsrobotics.autonomous.enums.PathPropertyType;
import com.amhsrobotics.autonomous.graph.rewrite.propfields.PropertiesTextField;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class WaypointPanel extends JPanel {
	private static WaypointPanel ourInstance = new WaypointPanel();

	public static WaypointPanel getInstance() {
		return ourInstance;
	}

	PropertiesTextField[] propertiesTextFields = new PropertiesTextField[4];

	private WaypointPanel() {
		setPreferredSize(new Dimension(250,260));
		setBackground(WindowColors.BACKGROUND_COLOR);
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	}

	public void init(){
		propertiesTextFields[0] = new PropertiesTextField(PathPropertyType.WAYPOINT1);
		propertiesTextFields[1] = new PropertiesTextField(PathPropertyType.WAYPOINT2);
		propertiesTextFields[2] = new PropertiesTextField(PathPropertyType.WAYPOINT1ANGLE);
		propertiesTextFields[3] = new PropertiesTextField(PathPropertyType.WAYPOINT2ANGLE);

		for(int i = 0; i < propertiesTextFields.length; i++){
			add(propertiesTextFields[i]);
		}
	}

	public void updateFields(){
		for(int i = 0; i < propertiesTextFields.length; i++){
			propertiesTextFields[i].updateText();
		}
	}

}

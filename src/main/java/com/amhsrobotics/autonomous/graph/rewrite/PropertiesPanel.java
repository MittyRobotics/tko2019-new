package com.amhsrobotics.autonomous.graph.rewrite;

import com.amhsrobotics.autonomous.enums.PathPropertyType;
import com.amhsrobotics.autonomous.graph.rewrite.propfields.PropertiesTextField;
import com.amhsrobotics.purepursuit.enums.PathType;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

public class PropertiesPanel extends JPanel {
	private static PropertiesPanel ourInstance = new PropertiesPanel();

	public static PropertiesPanel getInstance() {
		return ourInstance;
	}

	PropertiesTextField[] propertiesTextFields = new PropertiesTextField[10];

	private PropertiesPanel() {
		setPreferredSize(new Dimension(250,260));
		setBackground(WindowColors.BACKGROUND_COLOR);
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		propertiesTextFields[0] = new PropertiesTextField(PathPropertyType.LOOKAHEAD);
		propertiesTextFields[1] = new PropertiesTextField(PathPropertyType.KCURVATURE);
		propertiesTextFields[2] = new PropertiesTextField(PathPropertyType.MAXACCEL);
		propertiesTextFields[3] = new PropertiesTextField(PathPropertyType.MAXDECEL);
		propertiesTextFields[4] = new PropertiesTextField(PathPropertyType.MAXVEL);
		propertiesTextFields[5] = new PropertiesTextField(PathPropertyType.STARTVEL);
		propertiesTextFields[6] = new PropertiesTextField(PathPropertyType.ENDVEL);
		propertiesTextFields[7] = new PropertiesTextField(PathPropertyType.REVERSED);
		propertiesTextFields[8] = new PropertiesTextField(PathPropertyType.VISION);
		propertiesTextFields[9] = new PropertiesTextField(PathPropertyType.NAME);

		for(int i = 0; i < propertiesTextFields.length; i++){
			add(propertiesTextFields[i]);
		}
	}

	public void init(){

	}

	public void updateFields(){
		for(int i = 0; i < propertiesTextFields.length; i++){
			propertiesTextFields[i].updateText();
		}
	}


}

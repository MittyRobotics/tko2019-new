package com.amhsrobotics.autonomous.graph;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Properties;

public class PropertiesTextField extends JTextField {

	public PropertiesTextField(String text, String units, String promptText, double defaultValue){
		super(text + defaultValue + units);
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


}

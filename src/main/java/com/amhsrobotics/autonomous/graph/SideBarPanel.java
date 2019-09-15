package com.amhsrobotics.autonomous.graph;

import javax.swing.*;
import java.awt.*;

public class SideBarPanel extends JPanel {
	public SideBarPanel(){
		super();



		setBackground(new Color(71, 71, 71));

		JButton button = new JButton();
		button.setPreferredSize(new Dimension(200,50));
		button.setBackground(new Color(89, 89, 89));
		button.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
		add(button);
		setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));


	}
}

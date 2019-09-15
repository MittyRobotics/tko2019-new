package com.amhsrobotics.autonomous.graph;

import javax.swing.*;
import java.awt.*;

public class TextInput extends JPanel {
	public TextInput(Dimension size){
		super();

		setLayout(new BorderLayout());

		setPreferredSize(new Dimension(size.width,40));

		JTextField inputName = new JTextField();


		inputName.setFont(new Font(Font.DIALOG_INPUT,2,25));

		inputName.setText("Tw = ");



		inputName.setBackground(new Color(102, 102, 102));
		inputName.setForeground(new Color(178, 178, 178));

		inputName.setEditable(false);


		inputName.setBorder(BorderFactory.createEmptyBorder());


		add(inputName, BorderLayout.WEST);



		JTextField inputText = new JTextField();


		inputText.setFont(new Font(Font.DIALOG_INPUT,0,25));
		inputText.setPreferredSize(new Dimension(size.width-(inputName.getText().length() * 14),40));

		inputText.setBackground(new Color(102, 102, 102));
		inputText.setForeground(new Color(178, 178, 178));

		inputText.setBorder(BorderFactory.createEmptyBorder());

		add(inputText, BorderLayout.EAST);

		setBackground(new Color(102, 102, 102));
		setForeground(new Color(178, 178, 178));



		setBorder(BorderFactory.createMatteBorder(1,0,0,0, Color.BLACK));



	}
}

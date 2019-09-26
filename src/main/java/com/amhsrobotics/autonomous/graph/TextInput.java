package com.amhsrobotics.autonomous.graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextInput extends JPanel{

	JTextField inputName;
	JTextField inputText;



	public TextInput(Dimension size){
		super();

		setLayout(new BorderLayout());

		setPreferredSize(new Dimension(size.width,40));

		JTextField inputName = new JTextField();


		inputName.setFont(new Font(Font.DIALOG_INPUT,2,25));

		inputName.setText("");



		inputName.setBackground(new Color(102, 102, 102));
		inputName.setForeground(new Color(178, 178, 178));

		inputName.setEditable(false);


		inputName.setBorder(BorderFactory.createEmptyBorder());

		this.inputName = inputName;

		add(inputName, BorderLayout.WEST);



		JTextField inputText = new JTextField();


		inputText.setFont(new Font(Font.DIALOG_INPUT,0,25));
		inputText.setPreferredSize(new Dimension(size.width-(inputName.getText().length() * 14),40));

		inputText.setBackground(new Color(102, 102, 102));
		inputText.setForeground(new Color(178, 178, 178));

		inputText.setBorder(BorderFactory.createEmptyBorder());


		this.inputText = inputText;

		add(inputText, BorderLayout.EAST);

		setBackground(new Color(102, 102, 102));
		setForeground(new Color(178, 178, 178));



		setBorder(BorderFactory.createMatteBorder(1,0,0,0, Color.BLACK));



	}

	public void setPrompt(String prompt){
		inputName.setText(prompt);
		inputText.setText("" );
		inputText.setPreferredSize(new Dimension(getWidth()-(inputName.getText().length() * 14),40));
		resetActionListeners();
	}


	public void setPrompt(String prompt, SideBarPanel panel){
		setPrompt(prompt);

		inputText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.nameNode(inputText.getText());
				setPrompt("");
			}
		});
	}

	public void setPrompt(String prompt, PropertiesTextField propertiesTextField){
		setPrompt(prompt);

		inputText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try{
					propertiesTextField.setProperty(Double.parseDouble(inputText.getText()));
				}catch (Exception exception){
					System.out.println("You did not input a number");
				}
				setPrompt("");
			}
		});
	}


	public void resetActionListeners(){
		for( ActionListener al : inputText.getActionListeners() ) {
			inputText.removeActionListener( al );
		}

	}


}

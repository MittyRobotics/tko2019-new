package com.amhsrobotics.autonomous.graph.rewrite;

import com.amhsrobotics.autonomous.graph.rewrite.propfields.PropertiesTextField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextInput extends JPanel {
	private static TextInput ourInstance = new TextInput();

	public static TextInput getInstance() {
		return ourInstance;
	}

	JTextField inputName;
	JTextField inputText;



	private TextInput(){
		super();

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension((int)(582*1.5),40));
		setBackground(new Color(102, 102, 102));
		setForeground(new Color(178, 178, 178));
		setBorder(BorderFactory.createMatteBorder(1,0,0,0, Color.BLACK));


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
		inputText.setPreferredSize(new Dimension((int)((582*1.5)-(inputName.getText().length() * 14)),40));
		inputText.setBackground(new Color(102, 102, 102));
		inputText.setForeground(new Color(178, 178, 178));
		inputText.setBorder(BorderFactory.createEmptyBorder());
		inputText.setEditable(true);
		this.inputText = inputText;
		add(inputText, BorderLayout.EAST);


	}

	public void init(){

	}


	public void setPrompt(String prompt){
		inputName.setText(prompt);
		inputText.setText("");
		inputText.setPreferredSize(new Dimension((int)(582*1.5)-(inputName.getText().length() * 14),40));
		revalidate();
		repaint();
		resetActionListeners();
	}

	public void setPrompt(String prompt, PropertiesTextField input){
		setPrompt(prompt);

		inputText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				input.setValue(inputText.getText());
				setPrompt("");
			}
		});
	}

	private double guessX = 0;
	private double guessY = 0;

	public void setWaypointPrompt(PropertiesTextField input){
		setPrompt("guess X = ");
		Document doc = inputText.getDocument();
		DocumentListener documentListener = new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				try {

					GraphAutoPath.getInstance().graphGuessLines(Double.parseDouble(inputText.getText()),-1000);
				}catch (Exception exception){

				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				try {

					GraphAutoPath.getInstance().graphGuessLines(Double.parseDouble(inputText.getText()),-1000);
				}catch (Exception exception){

				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				try {

					GraphAutoPath.getInstance().graphGuessLines(Double.parseDouble(inputText.getText()),-1000);
				}catch (Exception exception){

				}
			}
		};
		doc.addDocumentListener(documentListener);


		inputText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doc.removeDocumentListener(documentListener);
				try {
					guessX = Double.parseDouble(inputText.getText());
					GraphAutoPath.getInstance().graphGuessLines(Double.parseDouble(inputText.getText()),-1000);
				}catch(Exception exception){}

				guessSecondValue(input);
			}
		});
	}

	public void guessSecondValue(PropertiesTextField input){
		setPrompt("guess Y = ");
		Document doc = inputText.getDocument();
		DocumentListener documentListener = new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				try {
					GraphAutoPath.getInstance().graphGuessLines(guessX,Double.parseDouble(inputText.getText()));
					System.out.println("asdfas");
				}catch (Exception exception){

				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				try {
					GraphAutoPath.getInstance().graphGuessLines(guessX,Double.parseDouble(inputText.getText()));
					System.out.println("asdfas");
				}catch (Exception exception){

				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				try {
					GraphAutoPath.getInstance().graphGuessLines(guessX,Double.parseDouble(inputText.getText()));
					System.out.println("asdfas");
				}catch (Exception exception){

				}
			}
		};
		doc.addDocumentListener(documentListener);



		inputText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					doc.removeDocumentListener(documentListener);
					guessY = Double.parseDouble(inputText.getText());


				}catch(Exception exception){}

				input.setValue(guessX + ":" + guessY);
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

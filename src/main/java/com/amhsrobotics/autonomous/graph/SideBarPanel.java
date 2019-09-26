package com.amhsrobotics.autonomous.graph;

import com.amhsrobotics.autonomous.movement.PathProperties;
import com.amhsrobotics.autonomous.movement.PathSequence;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Properties;

public class SideBarPanel extends JPanel {


	private ListNode root;

	private SequenceTree tree;


	private PropertiesPanel propertiesPanel;

	public SideBarPanel(){
		super();


		setBackground(new Color(71, 71, 71));

		setPreferredSize(new Dimension(260,500));


		Button addPathButton = new Button("Add Path");
		addPathButton.setPreferredSize(new Dimension(250,20));
		addPathButton.setBackground(new Color(100, 100, 100));

		addPathButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addPath();
			}
		});


		add(addPathButton);

		//create the root node
		ListNode root = new ListNode("Sequences");
		this.root = root;

		SequenceTree tree = new SequenceTree(root);

		add(tree);

		PropertiesPanel propertiesPanel = new PropertiesPanel();

		add(propertiesPanel);

		this.propertiesPanel = propertiesPanel;

		propertiesPanel.setVisible(false);

		this.tree = tree;



		setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));




	}

	public void createInitNodes(ArrayList<PathSequence> sequences){
		for(int i = 0; i < sequences.size(); i++){
			ListNode node = new ListNode(sequences.get(i).getName());
			ListNode velNode = new ListNode(sequences.get(i).getName() + " Velocity");
			node.add(velNode);
			root.add(node);
		}
		repaint();
		revalidate();
	}

	public void addPath(){

		PathPlannerWindow.getInstance().getMainPanel().getTextInput().setPrompt("Name: ", this);
	}

	public void nameNode(String name){




		ListNode node = new ListNode(name);
		ListNode velNode = new ListNode(name + " Velocity");
		node.add(velNode);
		root.add(node);


		tree = new SequenceTree(root);

		remove(1);
		add(tree, 1);

		repaint();
		revalidate();

		PathPlannerWindow.getInstance().getMainPanel().getTextInput().resetActionListeners();

		PathPlannerWindow.getInstance().addSequence(name, new PathProperties[]{});

	}

	public ListNode getRoot() {
		return root;
	}

	public void setRoot(ListNode root) {
		this.root = root;
	}

	public PropertiesPanel getPropertiesPanel() {
		return propertiesPanel;
	}

	public void setPropertiesPanel(PropertiesPanel propertiesPanel) {
		this.propertiesPanel = propertiesPanel;
	}

}

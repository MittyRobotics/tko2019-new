package com.amhsrobotics.autonomous.graph;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class SideBarPanel extends JPanel {
	public SideBarPanel(){
		super();



		setBackground(new Color(71, 71, 71));


		//create the root node
		ListNode root = new ListNode("Sequences");
		//create the child nodes
		ListNode node1 = new ListNode("RED Front Left Hatch Auton");
		ListNode node2 = new ListNode("RED Front Right Hatch Auton");
		ListNode node3 = new ListNode("BLUE Front Left Hatch Auton");
		ListNode node4 = new ListNode("BLUE Front Right Hatch Auton");

		ListNode node1V = new ListNode("RED Front Left Hatch Auton Velocity");
		ListNode node2V = new ListNode("RED Front Right Hatch Auton Velocity");
		ListNode node3V = new ListNode("BLUE Front Left Hatch Auton Velocity");
		ListNode node4V = new ListNode("BLUE Front Right Hatch Auton Velocity");


		//add the child nodes to the root node
		root.add(node1);
		node1.add(node1V);
		root.add(node2);
		node2.add(node2V);
		root.add(node3);
		node3.add(node3V);
		root.add(node4);
		node4.add(node4V);

		SequenceTree tree = new SequenceTree(root);

		add(tree);

		setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));


	}
}

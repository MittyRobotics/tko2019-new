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
		//add the child nodes to the root node
		root.add(node1);
		root.add(node2);

		root.add(node3);
		root.add(node4);

		SequenceTree tree = new SequenceTree(root);

		add(tree);

		setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));


	}
}

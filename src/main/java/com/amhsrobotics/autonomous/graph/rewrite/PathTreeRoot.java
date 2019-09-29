package com.amhsrobotics.autonomous.graph.rewrite;

import javax.swing.tree.DefaultMutableTreeNode;

public class PathTreeRoot extends DefaultMutableTreeNode {
	private static PathTreeRoot ourInstance = new PathTreeRoot();

	public static PathTreeRoot getInstance() {
		return ourInstance;
	}

	private PathTreeRoot() {

	}
}

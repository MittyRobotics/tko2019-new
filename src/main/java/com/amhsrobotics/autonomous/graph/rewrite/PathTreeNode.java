package com.amhsrobotics.autonomous.graph.rewrite;

import com.amhsrobotics.autonomous.movement.PathProperties;

import javax.swing.tree.DefaultMutableTreeNode;

public class PathTreeNode extends DefaultMutableTreeNode {
	private int attachedIndex;

	public PathTreeNode(String name, int attachedIndex){
		super(name);
		this.attachedIndex = attachedIndex;
	}

	public int getAttachedIndex() {
		return attachedIndex;
	}

	public void setAttachedIndex(int attachedIndex) {
		this.attachedIndex = attachedIndex;
	}
}

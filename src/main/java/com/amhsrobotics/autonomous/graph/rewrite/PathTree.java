package com.amhsrobotics.autonomous.graph.rewrite;

import com.amhsrobotics.purepursuit.Path;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;

public class PathTree extends JTree{
	private static PathTree ourInstance = new PathTree();

	public static PathTree getInstance() {
		return ourInstance;
	}

	private TreePath currentTreePath;

	private PathTree() {
		super(PathTreeRoot.getInstance());

		setPreferredSize(new Dimension(250,200));
		TreeCellRenderer renderer = getCellRenderer();
		DefaultTreeCellRenderer dtcr =
				(DefaultTreeCellRenderer)renderer;
		dtcr.setBackgroundNonSelectionColor(WindowColors.BACKGROUND_COLOR);
		dtcr.setBackgroundSelectionColor(Color.gray);
		dtcr.setTextSelectionColor(Color.white);
		dtcr.setTextNonSelectionColor(new Color(90,199,217));
		setBackground(WindowColors.BACKGROUND_COLOR);
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		setupListeners();

	}

	public void init(){
		refreshList();
	}

	public void setupListeners(){
		getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				currentTreePath = e.getPath();
			}});

		MouseListener ml = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int selRow = getRowForLocation(e.getX(), e.getY());
				TreePath selPath = getPathForLocation(e.getX(), e.getY());
				if(selRow != -1) {
					if(e.getClickCount() == 1) {
						//single click
					}
					else if(e.getClickCount() == 2) {
						//double click
						selection(selPath.getLastPathComponent().toString());

					}
				}
			}
		};

		KeyListener kl = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				//Keycode 13 is enter
				if(e.getKeyCode() == 10){
					selection(currentTreePath.getLastPathComponent().toString());

				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		};



		addMouseListener(ml);
		addKeyListener(kl);
	}

	public void selection(String name){
		for(int i = 0; i < PathTreeRoot.getInstance().getChildCount(); i++){
//			System.out.println(Window.getInstance().getPaths().get(i).getName());
			if(name.equals("VELOCITY_"+Window.getInstance().getPaths().get(i).getName())){
				Window.getInstance().setNewSelectedPath(i);
				GraphPanel.getInstance().switchToVelocityGraph();
				GraphVelocity.getInstance().setVelocityGraph(new Path[]{Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getPath()});
				PropertiesPanel.getInstance().updateFields();
				WaypointPanel.getInstance().updateFields();
			}
			else if(name.equals(Window.getInstance().getPaths().get(i).getName())){
				Window.getInstance().setNewSelectedPath(i);
				GraphPanel.getInstance().switchToPathGraph();
				GraphAutoPath.getInstance().setPathGraph(new Path[]{Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getPath()});
				PropertiesPanel.getInstance().updateFields();
				WaypointPanel.getInstance().updateFields();
			}
		}
	}

	public void refreshList(){

		PathTreeRoot.getInstance().removeAllChildren();
		for(int i = 0; i < Window.getInstance().getPaths().size(); i++){

			PathTreeNode node = new PathTreeNode(Window.getInstance().getPaths().get(i).getName(),i);
			node.add(new PathTreeNode("VELOCITY_"+Window.getInstance().getPaths().get(i).getName(),i));
			PathTreeRoot.getInstance().add(node);
		}
		DefaultTreeModel treeModel = new DefaultTreeModel(PathTreeRoot.getInstance());
		treeModel.reload();
		setModel(treeModel);
	}
}

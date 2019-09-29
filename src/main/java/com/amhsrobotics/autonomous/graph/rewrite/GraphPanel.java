package com.amhsrobotics.autonomous.graph.rewrite;

import com.amhsrobotics.purepursuit.Path;

import javax.swing.*;
import java.awt.*;

public class GraphPanel extends JPanel {
	private static GraphPanel ourInstance = new GraphPanel();

	public static GraphPanel getInstance() {
		return ourInstance;
	}

	private GraphType type;

	private GraphPanel() {
		super();
		setBackground(new Color(0, 0, 0));
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(1, 1, 2, 2));
		add(GraphAutoPath.getInstance(), BorderLayout.NORTH);
		add(TextInput.getInstance(), BorderLayout.SOUTH);
		switchToPathGraph();
	}

	public void init(){

	}

	public void updateGraphs(){
		for(int i = 0; i < Window.getInstance().getPaths().size(); i++){
			Window.getInstance().getPaths().get(i).regeneratePath();
		}
		if(type == GraphType.VELOCITY){
			GraphVelocity.getInstance().setVelocityGraph(new Path[]{Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getPath()});
		}
		if(type == GraphType.PATH){
			GraphAutoPath.getInstance().setPathGraph(new Path[]{Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getPath()});
		}
	}

	public void switchToVelocityGraph(){
		type = GraphType.VELOCITY;
		GraphAutoPath.getInstance().setVisible(false);
		GraphVelocity.getInstance().setVisible(true);
		add(GraphVelocity.getInstance());
	}

	public void switchToPathGraph(){
		type = GraphType.PATH;
		GraphAutoPath.getInstance().setVisible(true);
		GraphVelocity.getInstance().setVisible(false);
		add(GraphAutoPath.getInstance());
	}
}

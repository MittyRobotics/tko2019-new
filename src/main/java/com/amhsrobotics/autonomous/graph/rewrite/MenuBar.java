package com.amhsrobotics.autonomous.graph.rewrite;

import com.amhsrobotics.autonomous.enums.PathPropertyType;
import com.amhsrobotics.autonomous.movement.PathProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
	private static MenuBar ourInstance = new MenuBar();

	public static MenuBar getInstance() {
		return ourInstance;
	}

	private MenuBar() {
		super();
		UIManager.put("MenuBar.foreground", WindowColors.TEXT_COLOR);
		UIManager.put("MenuBar.background", WindowColors.BACKGROUND_COLOR);
		UIManager.put("MenuItem.foreground", WindowColors.TEXT_COLOR);
		UIManager.put("MenuItem.background", WindowColors.BACKGROUND_COLOR);

		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu viewMenu = new JMenu("View");


		JMenu generateCode = new JMenu("Generate Code");
		JMenuItem generateAll = new JMenuItem("Generate All Path Code");
		JMenuItem generateSelected = new JMenuItem("Generate Selected Path Code");

		generateAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("asdf");

				GeneratedCodeDialog d = new GeneratedCodeDialog();
				for(int i = 0; i < Window.getInstance().getPaths().size(); i++){
					PathProperties props = Window.getInstance().getPaths().get(i);
					new PathProperties(props.getWaypoints(),props.getLookaheadDist(),props.getkCurvature(),props.getMaxAcceleration(),props.getMaxDeceleration(),props.getMaxVelocity(),props.getStartVelocity(),props.getEndVelocity(),props.getReversed(),props.getVision(),props.getName());
					JTextField text = new JTextField("new PathProperties(new Waypoint[]{new Waypoint(new Point2D.Double("+ props.getWaypoints()[0].getWaypoint().getX() + "," + props.getWaypoints()[0].getWaypoint().getY() +")," + props.getWaypoints()[0].getAngle() +"),new Waypoint(new Point2D.Double("+ props.getWaypoints()[0].getWaypoint().getX() + "," + props.getWaypoints()[0].getWaypoint().getY() +")," + props.getWaypoints()[0].getAngle() +")}");
					text.setBorder(BorderFactory.createEmptyBorder(100,10,100,10));
					d.add(text);

				}



				d.pack();


				d.setVisible(true);

			}
		});

		generateCode.add(generateAll);
		generateCode.add(generateSelected);

		fileMenu.add(generateCode);

		add(fileMenu);
		add(editMenu);
		add(viewMenu);

		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

	}

	public void init() {

	}
}

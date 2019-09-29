package com.amhsrobotics.autonomous.graph.rewrite.propfields;

import com.amhsrobotics.autonomous.constants.AutoConstants;
import com.amhsrobotics.autonomous.enums.PathPropertyType;
import com.amhsrobotics.autonomous.graph.rewrite.*;
import com.amhsrobotics.autonomous.graph.rewrite.Window;
import com.amhsrobotics.purepursuit.Waypoint;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class PropertiesTextField extends JTextField {


	private PathPropertyType type;
	public PropertiesTextField(PathPropertyType type){
		super();
		this.type = type;
		setEditable(false);
		setPreferredSize(new Dimension(200,20));
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(100, 100, 100));
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

		PropertiesTextField field = this;

		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()==2){
					if(type == PathPropertyType.WAYPOINT1 || type == PathPropertyType.WAYPOINT2){
						TextInput.getInstance().setWaypointPrompt(field);
					}
					else{
						TextInput.getInstance().setPrompt(getPrompt(), field);
					}

				}
			}
		});

	}

	public void setValue(String value){
		try{
			switch(type){
				case LOOKAHEAD:
					Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).setLookaheadDist(Double.parseDouble(value));
					break;
				case KCURVATURE:
					Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).setkCurvature(Double.parseDouble(value));
					break;
				case MAXACCEL:
					Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).setMaxAcceleration(Double.parseDouble(value));
					break;
				case MAXDECEL:
					Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).setMaxDeceleration(Double.parseDouble(value));
					break;
				case MAXVEL:
					Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).setMaxVelocity(Double.parseDouble(value));
					break;
				case STARTVEL:
					Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).setStartVelocity(Double.parseDouble(value));
					break;
				case ENDVEL:
					Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).setEndVelocity(Double.parseDouble(value));
					break;
				case REVERSED:
					Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).setReversed(Boolean.parseBoolean(value));
					break;
				case VISION:
					Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).setVision(Boolean.parseBoolean(value));
					break;
				case NAME:
					Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).setName(value);
					PathTree.getInstance().refreshList();
					break;
				case WAYPOINT1:
					try {
						String[] split = value.split(":");
						double guessX = Double.parseDouble(split[0]);
						double guessY = Double.parseDouble(split[1]);

						double currentClosest = 1000;
						int currentIndex = 0;
						for(int i = 0; i < AutoConstants.BLUE_POINTS.length; i++){
							if(Point2D.distance(AutoConstants.BLUE_POINTS[i].getWaypoint().getX(),AutoConstants.BLUE_POINTS[i].getWaypoint().getY(), guessX, guessY) < currentClosest){
								currentClosest = Point2D.distance(AutoConstants.BLUE_POINTS[i].getWaypoint().getX(),AutoConstants.BLUE_POINTS[i].getWaypoint().getY(), guessX, guessY);
								currentIndex = i;
							}
						}

						Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).setWaypoints(
								new Waypoint[]{
										AutoConstants.BLUE_POINTS[currentIndex],
										Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getWaypoints()[1]
								});

					}catch(Exception e){}


					break;
				case WAYPOINT2:
					try {
						String[] split = value.split(":");
						double guessX = Double.parseDouble(split[0]);
						double guessY = Double.parseDouble(split[1]);

						double currentClosest = 1000;
						int currentIndex = 0;
						for(int i = 0; i < AutoConstants.BLUE_POINTS.length; i++){
							if(Point2D.distance(AutoConstants.BLUE_POINTS[i].getWaypoint().getX(),AutoConstants.BLUE_POINTS[i].getWaypoint().getY(), guessX, guessY) < currentClosest){
								currentClosest = Point2D.distance(AutoConstants.BLUE_POINTS[i].getWaypoint().getX(),AutoConstants.BLUE_POINTS[i].getWaypoint().getY(), guessX, guessY);
								currentIndex = i;
							}
						}

						Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).setWaypoints(
								new Waypoint[]{
										Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getWaypoints()[0],
										AutoConstants.BLUE_POINTS[currentIndex]

								});

					}catch(Exception e){}


					break;
				case WAYPOINT1ANGLE:
					Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getWaypoints()[0] = new Waypoint(new Point2D.Double(Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getWaypoints()[0].getWaypoint().getX(),Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getWaypoints()[0].getWaypoint().getY()),Double.parseDouble(value));
					break;
				case WAYPOINT2ANGLE:
					Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getWaypoints()[1] = new Waypoint(new Point2D.Double(Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getWaypoints()[1].getWaypoint().getX(),Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getWaypoints()[1].getWaypoint().getY()),Double.parseDouble(value));
					break;
			}
		}
		catch(Exception e){
			System.out.println("Input text was not in the right format");
		}
		updateText();
		WaypointPanel.getInstance().updateFields();
		PropertiesPanel.getInstance().updateFields();
		GraphPanel.getInstance().updateGraphs();
	}

	public void updateText(){
		switch(type){
			case LOOKAHEAD:
				setText("Lookahead Distance: " + Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getLookaheadDist() + "in");
				break;
			case KCURVATURE:
				setText("kCuravture: " + Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getkCurvature());
				break;
			case MAXACCEL:
				setText("Maximum Acceleration: " + Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getMaxAcceleration() + "in/s^2");
				break;
			case MAXDECEL:
				setText("Maximum Deceleration: " + Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getMaxDeceleration() + "in/s^2");
				break;
			case MAXVEL:
				setText("Maximum Velocity: " + Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getMaxVelocity() + "in/s");
				break;
			case STARTVEL:
				setText("Start Velocity: " + Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getStartVelocity() + "in/s");
				break;
			case ENDVEL:
				setText("End Velocity: " + Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getEndVelocity() + "in/s");
				break;
			case REVERSED:
				setText("Reversed: " + Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getReversed());
				break;
			case VISION:
				setText("Vision: " + Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getVision());
				break;
			case NAME:
				setText("Name: " + Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getName());
				break;
			case WAYPOINT1:
				setText("Waypoint 1 X: " + Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getWaypoints()[0].getWaypoint().getX() + " Y: " + Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getWaypoints()[0].getWaypoint().getY());
				break;
			case WAYPOINT2:
				setText("Waypoint 2 X: " + Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getWaypoints()[1].getWaypoint().getX() + " Y: " + Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getWaypoints()[1].getWaypoint().getY());
				break;
			case WAYPOINT1ANGLE:
				setText("Waypoint 1 Angle: " + Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getWaypoints()[0].getAngle());
				break;
			case WAYPOINT2ANGLE:
				setText("Waypoint 2 Angle: " + Window.getInstance().getPaths().get(Window.getInstance().getCurrentSelectedPathPropertiesIndex()).getWaypoints()[1].getAngle());
				break;
		}
	}

	public String getPrompt(){
		switch(type) {
			case LOOKAHEAD:
				return "lookahead = ";
			case KCURVATURE:
				return "kCurvature = ";
			case MAXACCEL:
				return "maxAccel = ";
			case MAXDECEL:
				return "maxDecel = ";
			case MAXVEL:
				return "maxVel = ";
			case STARTVEL:
				return "startVel = ";
			case ENDVEL:
				return "endVel = ";
			case REVERSED:
				return "reversed = ";
			case VISION:
				return "vision = ";
			case NAME:
				return "name = ";
			case WAYPOINT1ANGLE:
				return "angle = ";
			case WAYPOINT2ANGLE:
				return "angle = ";
		}
		return "";
	}


	public PathPropertyType getType() {
		return type;
	}

	public void setType(PathPropertyType type) {
		this.type = type;
	}


}

package com.amhsrobotics.autonomous.constants;

import com.amhsrobotics.autonomous.movement.PathProperties;
import com.amhsrobotics.autonomous.movement.PathSequence;
import com.amhsrobotics.purepursuit.Waypoint;

import java.awt.geom.Point2D;

public class AutoPaths {
	public static PathProperties B_LEFT_START_TO_FRONT_CS = new PathProperties(new Waypoint[]{AutoConstants.BLUE_LEFT_START_POS, AutoConstants.BLUE_LEFT_FRONT_HATCH}, 15, .8, 10, 10, 50,0,0,true,true, "B_LEFT_START_TO_FRONT_CS");
	public static PathProperties B_LEFT_FRONT_CS_TO_LEFT_HELPER = new PathProperties(new Waypoint[]{AutoConstants.BLUE_LEFT_FRONT_HATCH_REVERSED, AutoConstants.BLUE_LEFT_HELPER_POINT}, 15, .8, 10, 10, 50,0,0,false,false, "B_LEFT_FRONT_CS_TO_LEFT_HELPER");
	public static PathProperties B_LEFT_HELPER_TO_LEFT_LOADER = new PathProperties(new Waypoint[]{AutoConstants.BLUE_LEFT_HELPER_POINT_REVERSED, AutoConstants.BLUE_LEFT_LOADER_STATION}, 15, .8, 10, 10, 50,0,0,true,true, "B_LEFT_HELPER_TO_LEFT_LOADER");
	//public static PathProperties B_RIGHT_START_TO_END_HAB = new PathProperties(new Waypoint[]{AutoConstants.BLUE_RIGHT_START_POS, new Waypoint(new Point2D.Double(AutoConstants.BLUE_RIGHT_START_POS.getWaypoint().getX() + 30, AutoConstants.BLUE_RIGHT_START_POS.getWaypoint().getY()),0)}, 10, 2, 20, 20, 50,0,0,true,false, "B_RIGHT_START_TO_END_HAB");
	public static PathProperties B_RIGHT_END_HAB_TO_FRONT_CS = new PathProperties(new Waypoint[]{new Waypoint(new Point2D.Double(AutoConstants.BLUE_RIGHT_START_POS.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_START_POS.getWaypoint().getY()),0), new Waypoint(new Point2D.Double(AutoConstants.BLUE_RIGHT_FRONT_HATCH.getWaypoint().getX()  , AutoConstants.BLUE_RIGHT_FRONT_HATCH.getWaypoint().getY()),0)}, 10, .5, 50, 50, 200,50,0,true,true, "B_RIGHT_END_HAB_TO_FRONT_CS");
	public static PathProperties B_RIGHT_FRONT_CS_TO_LEFT_HELPER = new PathProperties(new Waypoint[]{AutoConstants.BLUE_RIGHT_FRONT_HATCH_REVERSED, AutoConstants.BLUE_RIGHT_HELPER_POINT}, 15, .8, 100, 10, 300,50,0,false,false, "B_RIGHT_FRONT_CS_TO_LEFT_HELPER");
	public static PathProperties B_RIGHT_HELPER_TO_LEFT_LOADER = new PathProperties(new Waypoint[]{AutoConstants.BLUE_RIGHT_HELPER_POINT_REVERSED, AutoConstants.BLUE_RIGHT_LOADER_STATION}, 15, .8, 50, 50, 300,50,0,true,false, "B_RIGHT_HELPER_TO_LEFT_LOADER");
	public static PathProperties R_LEFT_START_TO_FRONT_CS = new PathProperties(new Waypoint[]{AutoConstants.RED_LEFT_START_POS, AutoConstants.RED_LEFT_FRONT_HATCH}, 15, .8, 10, 10, 50,0,0,true,true, "R_LEFT_START_TO_FRONT_CS");
	public static PathProperties R_LEFT_FRONT_CS_TO_LEFT_HELPER = new PathProperties(new Waypoint[]{AutoConstants.RED_LEFT_FRONT_HATCH_REVERSED, AutoConstants.RED_LEFT_HELPER_POINT}, 15, .8, 10, 10, 50,0,0,false,false, "R_LEFT_FRONT_CS_TO_LEFT_HELPER");
	public static PathProperties R_LEFT_HELPER_TO_LEFT_LOADER = new PathProperties(new Waypoint[]{AutoConstants.RED_LEFT_HELPER_POINT_REVERSED, AutoConstants.RED_LEFT_LOADER_STATION}, 15, .8, 10, 10, 50,0,0,true,true, "R_LEFT_HELPER_TO_LEFT_LOADER");

	public static PathProperties[] paths = new PathProperties[]{
			B_LEFT_START_TO_FRONT_CS,
			B_LEFT_FRONT_CS_TO_LEFT_HELPER,
			B_LEFT_HELPER_TO_LEFT_LOADER,
			B_RIGHT_END_HAB_TO_FRONT_CS,
			//B_RIGHT_START_TO_END_HAB,
			B_RIGHT_FRONT_CS_TO_LEFT_HELPER,
			B_RIGHT_HELPER_TO_LEFT_LOADER,
			R_LEFT_START_TO_FRONT_CS,
			R_LEFT_FRONT_CS_TO_LEFT_HELPER,
			R_LEFT_HELPER_TO_LEFT_LOADER
	};

}

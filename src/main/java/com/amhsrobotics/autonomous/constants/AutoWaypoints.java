package com.amhsrobotics.autonomous.constants;

import com.amhsrobotics.purepursuit.Waypoint;

import java.awt.geom.Point2D;

public class AutoWaypoints {

	private static double habExtendDistance = 50;

	public static Waypoint[][] BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET = new Waypoint[][]{
			{AutoConstants.BLUE_LEFT_START_POS, new Waypoint(new Point2D.Double(AutoConstants.BLUE_LEFT_START_POS.getWaypoint().getX() + habExtendDistance, AutoConstants.BLUE_LEFT_START_POS.getWaypoint().getY()), AutoConstants.BLUE_LEFT_START_POS.getAngle()), AutoConstants.BLUE_LEFT_FRONT_HATCH},
			{AutoConstants.BLUE_LEFT_FRONT_HATCH_REVERSED, AutoConstants.BLUE_LEFT_HELPER_POINT},
			{AutoConstants.BLUE_LEFT_HELPER_POINT_REVERSED, AutoConstants.BLUE_LEFT_LOADER_STATION},
			{AutoConstants.BLUE_LEFT_LOADER_STATION_REVERSED, AutoConstants.BLUE_LEFT_HELPER_POINT},
			{AutoConstants.BLUE_LEFT_HELPER_POINT_REVERSED, AutoConstants.BLUE_LEFT_ROCKET_HATCH}
	};
	public static Waypoint[][] RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET = new Waypoint[][]{
			{AutoConstants.RED_LEFT_START_POS, new Waypoint(new Point2D.Double(AutoConstants.RED_LEFT_START_POS.getWaypoint().getX() + habExtendDistance, AutoConstants.RED_LEFT_START_POS.getWaypoint().getY()), AutoConstants.RED_LEFT_START_POS.getAngle()), AutoConstants.RED_LEFT_FRONT_HATCH},
			{AutoConstants.RED_LEFT_FRONT_HATCH_REVERSED, AutoConstants.RED_LEFT_HELPER_POINT},
			{AutoConstants.RED_LEFT_HELPER_POINT_REVERSED, AutoConstants.RED_LEFT_LOADER_STATION},
			{AutoConstants.RED_LEFT_LOADER_STATION_REVERSED, AutoConstants.RED_LEFT_HELPER_POINT},
			{AutoConstants.RED_LEFT_HELPER_POINT_REVERSED, AutoConstants.RED_LEFT_ROCKET_HATCH}
	};
	public static Waypoint[][] BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET = new Waypoint[][]{
			{AutoConstants.BLUE_RIGHT_START_POS, new Waypoint(new Point2D.Double(AutoConstants.BLUE_RIGHT_START_POS.getWaypoint().getX() + habExtendDistance, AutoConstants.BLUE_RIGHT_START_POS.getWaypoint().getY()), AutoConstants.BLUE_RIGHT_START_POS.getAngle()), AutoConstants.BLUE_RIGHT_FRONT_HATCH},
			{AutoConstants.BLUE_RIGHT_FRONT_HATCH_REVERSED, AutoConstants.BLUE_RIGHT_HELPER_POINT},
			{AutoConstants.BLUE_RIGHT_HELPER_POINT_REVERSED, AutoConstants.BLUE_RIGHT_LOADER_STATION},
			{AutoConstants.BLUE_RIGHT_LOADER_STATION_REVERSED, AutoConstants.BLUE_RIGHT_HELPER_POINT},
			{AutoConstants.BLUE_RIGHT_HELPER_POINT_REVERSED, AutoConstants.BLUE_RIGHT_ROCKET_HATCH}
	};
	public static Waypoint[][] RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET = new Waypoint[][]{
			{AutoConstants.BLUE_RIGHT_START_POS, new Waypoint(new Point2D.Double(AutoConstants.BLUE_RIGHT_START_POS.getWaypoint().getX() + habExtendDistance, AutoConstants.BLUE_RIGHT_START_POS.getWaypoint().getY()), AutoConstants.BLUE_RIGHT_START_POS.getAngle()), AutoConstants.BLUE_RIGHT_FRONT_HATCH},
			{AutoConstants.RED_RIGHT_FRONT_HATCH_REVERSED, AutoConstants.RED_RIGHT_HELPER_POINT},
			{AutoConstants.RED_RIGHT_HELPER_POINT_REVERSED, AutoConstants.RED_RIGHT_LOADER_STATION},
			{AutoConstants.RED_RIGHT_LOADER_STATION_REVERSED, AutoConstants.RED_RIGHT_HELPER_POINT},
			{AutoConstants.RED_RIGHT_HELPER_POINT_REVERSED, AutoConstants.RED_RIGHT_ROCKET_HATCH}
	};
}

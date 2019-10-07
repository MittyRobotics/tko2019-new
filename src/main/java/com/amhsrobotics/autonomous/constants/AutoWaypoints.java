package com.amhsrobotics.autonomous.constants;

import com.amhsrobotics.purepursuit.Waypoint;

import java.awt.geom.Point2D;

public class AutoWaypoints {

	private static double habExtendDistance = 50;

	public static Waypoint[][] BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET = new Waypoint[][]{
			{new Waypoint(new Point2D.Double(AutoConstants.BLUE_LEFT_START_POS.getWaypoint().getX(),AutoConstants.BLUE_LEFT_START_POS.getWaypoint().getY()),0), new Waypoint(new Point2D.Double(AutoConstants.BLUE_LEFT_FRONT_HATCH.getWaypoint().getX(),AutoConstants.BLUE_LEFT_FRONT_HATCH.getWaypoint().getY()),0)},
			{new Waypoint(new Point2D.Double(AutoConstants.BLUE_LEFT_FRONT_HATCH.getWaypoint().getX(),AutoConstants.BLUE_LEFT_FRONT_HATCH.getWaypoint().getY()),0), new Waypoint(new Point2D.Double(AutoConstants.BLUE_LEFT_HELPER_POINT.getWaypoint().getX(),AutoConstants.BLUE_LEFT_HELPER_POINT.getWaypoint().getY()),-90)},
			{new Waypoint(new Point2D.Double(AutoConstants.BLUE_LEFT_HELPER_POINT.getWaypoint().getX(),AutoConstants.BLUE_LEFT_HELPER_POINT.getWaypoint().getY()),0), new Waypoint(new Point2D.Double(AutoConstants.BLUE_LEFT_LOADER_STATION.getWaypoint().getX(),AutoConstants.BLUE_LEFT_LOADER_STATION.getWaypoint().getY()),0)},
			{new Waypoint(new Point2D.Double(AutoConstants.BLUE_LEFT_LOADER_STATION.getWaypoint().getX(),AutoConstants.BLUE_LEFT_LOADER_STATION.getWaypoint().getY()),0), new Waypoint(new Point2D.Double(AutoConstants.BLUE_LEFT_HELPER_POINT.getWaypoint().getX(),AutoConstants.BLUE_LEFT_HELPER_POINT.getWaypoint().getY()),0)},
			{new Waypoint(new Point2D.Double(AutoConstants.BLUE_LEFT_HELPER_POINT.getWaypoint().getX(),AutoConstants.BLUE_LEFT_HELPER_POINT.getWaypoint().getY()),0), new Waypoint(new Point2D.Double(AutoConstants.BLUE_LEFT_ROCKET_HATCH.getWaypoint().getX(),AutoConstants.BLUE_LEFT_ROCKET_HATCH.getWaypoint().getY()),0)}
	};
	public static Waypoint[][] RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET = new Waypoint[][]{
			{new Waypoint(new Point2D.Double(AutoConstants.RED_LEFT_START_POS.getWaypoint().getX(),AutoConstants.RED_LEFT_START_POS.getWaypoint().getY()),0), new Waypoint(new Point2D.Double(AutoConstants.RED_LEFT_FRONT_HATCH.getWaypoint().getX(),AutoConstants.RED_LEFT_FRONT_HATCH.getWaypoint().getY()),0)},
			{new Waypoint(new Point2D.Double(AutoConstants.RED_LEFT_FRONT_HATCH.getWaypoint().getX(),AutoConstants.RED_LEFT_FRONT_HATCH.getWaypoint().getY()),0), new Waypoint(new Point2D.Double(AutoConstants.RED_LEFT_HELPER_POINT.getWaypoint().getX(),AutoConstants.RED_LEFT_HELPER_POINT.getWaypoint().getY()),-90)},
			{new Waypoint(new Point2D.Double(AutoConstants.RED_LEFT_HELPER_POINT.getWaypoint().getX(),AutoConstants.RED_LEFT_HELPER_POINT.getWaypoint().getY()),0), new Waypoint(new Point2D.Double(AutoConstants.RED_LEFT_LOADER_STATION.getWaypoint().getX(),AutoConstants.RED_LEFT_LOADER_STATION.getWaypoint().getY()),0)},
			{new Waypoint(new Point2D.Double(AutoConstants.RED_LEFT_LOADER_STATION.getWaypoint().getX(),AutoConstants.RED_LEFT_LOADER_STATION.getWaypoint().getY()),0), new Waypoint(new Point2D.Double(AutoConstants.RED_LEFT_HELPER_POINT.getWaypoint().getX(),AutoConstants.RED_LEFT_HELPER_POINT.getWaypoint().getY()),0)},
			{new Waypoint(new Point2D.Double(AutoConstants.RED_LEFT_HELPER_POINT.getWaypoint().getX(),AutoConstants.RED_LEFT_HELPER_POINT.getWaypoint().getY()),0), new Waypoint(new Point2D.Double(AutoConstants.RED_LEFT_ROCKET_HATCH.getWaypoint().getX(),AutoConstants.RED_LEFT_ROCKET_HATCH.getWaypoint().getY()),0)}
	};
	public static Waypoint[][] BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET = new Waypoint[][]{
			{new Waypoint(new Point2D.Double(AutoConstants.BLUE_RIGHT_START_POS.getWaypoint().getX(),AutoConstants.BLUE_RIGHT_START_POS.getWaypoint().getY()),0), new Waypoint(new Point2D.Double(AutoConstants.BLUE_RIGHT_FRONT_HATCH.getWaypoint().getX(),AutoConstants.BLUE_RIGHT_FRONT_HATCH.getWaypoint().getY()),0)},
			{new Waypoint(new Point2D.Double(AutoConstants.BLUE_RIGHT_FRONT_HATCH.getWaypoint().getX(),AutoConstants.BLUE_RIGHT_FRONT_HATCH.getWaypoint().getY()),0), new Waypoint(new Point2D.Double(AutoConstants.BLUE_RIGHT_HELPER_POINT.getWaypoint().getX(),AutoConstants.BLUE_RIGHT_HELPER_POINT.getWaypoint().getY()),90)},
			{new Waypoint(new Point2D.Double(AutoConstants.BLUE_RIGHT_HELPER_POINT.getWaypoint().getX(),AutoConstants.BLUE_RIGHT_HELPER_POINT.getWaypoint().getY()),0), new Waypoint(new Point2D.Double(AutoConstants.BLUE_RIGHT_LOADER_STATION.getWaypoint().getX(),AutoConstants.BLUE_RIGHT_LOADER_STATION.getWaypoint().getY()),0)},
			{new Waypoint(new Point2D.Double(AutoConstants.BLUE_RIGHT_LOADER_STATION.getWaypoint().getX(),AutoConstants.BLUE_RIGHT_LOADER_STATION.getWaypoint().getY()),0), new Waypoint(new Point2D.Double(AutoConstants.BLUE_RIGHT_HELPER_POINT.getWaypoint().getX(),AutoConstants.BLUE_RIGHT_HELPER_POINT.getWaypoint().getY()),0)},
			{new Waypoint(new Point2D.Double(AutoConstants.BLUE_RIGHT_HELPER_POINT.getWaypoint().getX(),AutoConstants.BLUE_RIGHT_HELPER_POINT.getWaypoint().getY()),0), new Waypoint(new Point2D.Double(AutoConstants.BLUE_RIGHT_ROCKET_HATCH.getWaypoint().getX(),AutoConstants.BLUE_RIGHT_ROCKET_HATCH.getWaypoint().getY()),0)}
	};
	public static Waypoint[][] RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET = new Waypoint[][]{
			{new Waypoint(new Point2D.Double(AutoConstants.RED_RIGHT_START_POS.getWaypoint().getX(),AutoConstants.RED_RIGHT_START_POS.getWaypoint().getY()),0), new Waypoint(new Point2D.Double(AutoConstants.RED_RIGHT_FRONT_HATCH.getWaypoint().getX(),AutoConstants.RED_RIGHT_FRONT_HATCH.getWaypoint().getY()),0)},
			{new Waypoint(new Point2D.Double(AutoConstants.RED_RIGHT_FRONT_HATCH.getWaypoint().getX(),AutoConstants.RED_RIGHT_FRONT_HATCH.getWaypoint().getY()),0), new Waypoint(new Point2D.Double(AutoConstants.RED_RIGHT_HELPER_POINT.getWaypoint().getX(),AutoConstants.RED_RIGHT_HELPER_POINT.getWaypoint().getY()),90)},
			{new Waypoint(new Point2D.Double(AutoConstants.RED_RIGHT_HELPER_POINT.getWaypoint().getX(),AutoConstants.RED_RIGHT_HELPER_POINT.getWaypoint().getY()),0), new Waypoint(new Point2D.Double(AutoConstants.RED_RIGHT_LOADER_STATION.getWaypoint().getX(),AutoConstants.RED_RIGHT_LOADER_STATION.getWaypoint().getY()),0)},
			{new Waypoint(new Point2D.Double(AutoConstants.RED_RIGHT_LOADER_STATION.getWaypoint().getX(),AutoConstants.RED_RIGHT_LOADER_STATION.getWaypoint().getY()),0), new Waypoint(new Point2D.Double(AutoConstants.RED_RIGHT_HELPER_POINT.getWaypoint().getX(),AutoConstants.RED_RIGHT_HELPER_POINT.getWaypoint().getY()),0)},
			{new Waypoint(new Point2D.Double(AutoConstants.RED_RIGHT_HELPER_POINT.getWaypoint().getX(),AutoConstants.RED_RIGHT_HELPER_POINT.getWaypoint().getY()),0), new Waypoint(new Point2D.Double(AutoConstants.RED_RIGHT_ROCKET_HATCH.getWaypoint().getX(),AutoConstants.RED_RIGHT_ROCKET_HATCH.getWaypoint().getY()),0)}
	};
}

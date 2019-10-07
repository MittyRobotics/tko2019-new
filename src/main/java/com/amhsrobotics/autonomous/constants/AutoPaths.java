package com.amhsrobotics.autonomous.constants;

import com.amhsrobotics.autonomous.movement.PathProperties;
import com.amhsrobotics.purepursuit.Waypoint;

public class AutoPaths {
	public static PathProperties B_RIGHT_START_TO_FRONT_CS = new PathProperties(new Waypoint[]{AutoConstants.BLUE_RIGHT_START_POS, AutoConstants.BLUE_RIGHT_FRONT_HATCH}, 20, .8, 25, 25, 75,0,0,true,true, "B_RIGHT_END_HAB_TO_FRONT_CS");
	public static PathProperties B_RIGHT_FRONT_CS_TO_RIGHT_HELPER = new PathProperties(new Waypoint[]{AutoConstants.BLUE_RIGHT_FRONT_HATCH_REVERSED, AutoConstants.BLUE_RIGHT_HELPER_POINT}, 20, .8, 50, 50, 100,50,0,false,false, "B_RIGHT_FRONT_CS_TO_RIGHT_HELPER");
	public static PathProperties B_RIGHT_HELPER_TO_RIGHT_LOADER = new PathProperties(new Waypoint[]{AutoConstants.BLUE_RIGHT_HELPER_POINT_TURN, AutoConstants.BLUE_RIGHT_LOADER_STATION}, 20, .8, 50, 50, 100,0,0,true,true, "B_RIGHT_HELPER_TO_RIGHT_LOADER");
	public static PathProperties B_LEFT_START_TO_FRONT_CS = new PathProperties(new Waypoint[]{AutoConstants.BLUE_LEFT_START_POS, AutoConstants.BLUE_LEFT_FRONT_HATCH}, 20, .8, 25, 25, 75,0,0,true,true, "B_LEFT_START_TO_FRONT_CS");
	public static PathProperties B_LEFT_FRONT_CS_TO_RIGHT_HELPER = new PathProperties(new Waypoint[]{AutoConstants.BLUE_LEFT_FRONT_HATCH_REVERSED, AutoConstants.BLUE_LEFT_HELPER_POINT}, 20, .8, 50, 50, 100,50,0,false,false, "B_LEFT_FRONT_CS_TO_RIGHT_HELPER");
	public static PathProperties B_LEFT_HELPER_TO_RIGHT_LOADER = new PathProperties(new Waypoint[]{AutoConstants.BLUE_LEFT_HELPER_POINT_TURN, AutoConstants.BLUE_LEFT_LOADER_STATION}, 20, .8, 50, 50, 100,0,0,true,true, "B_LEFT_HELPER_TO_RIGHT_LOADER");
	public static PathProperties R_RIGHT_START_TO_FRONT_CS = new PathProperties(new Waypoint[]{AutoConstants.RED_RIGHT_START_POS, AutoConstants.RED_RIGHT_FRONT_HATCH}, 20, .8, 25, 25, 75,0,0,true,true, "R_RIGHT_END_HAB_TO_FRONT_CS");
	public static PathProperties R_RIGHT_FRONT_CS_TO_RIGHT_HELPER = new PathProperties(new Waypoint[]{AutoConstants.RED_RIGHT_FRONT_HATCH_REVERSED, AutoConstants.RED_RIGHT_HELPER_POINT}, 20, .8, 50, 50, 100,50,0,false,false, "R_RIGHT_FRONT_CS_TO_RIGHT_HELPER");
	public static PathProperties R_RIGHT_HELPER_TO_RIGHT_LOADER = new PathProperties(new Waypoint[]{AutoConstants.RED_RIGHT_HELPER_POINT_TURN, AutoConstants.RED_RIGHT_LOADER_STATION}, 20, .8, 50, 50, 100,0,0,true,true, "R_RIGHT_HELPER_TO_RIGHT_LOADER");
	public static PathProperties R_LEFT_START_TO_FRONT_CS = new PathProperties(new Waypoint[]{AutoConstants.RED_LEFT_START_POS, AutoConstants.RED_LEFT_FRONT_HATCH}, 20, .8, 25, 25, 75,0,0,true,true, "R_LEFT_START_TO_FRONT_CS");
	public static PathProperties R_LEFT_FRONT_CS_TO_RIGHT_HELPER = new PathProperties(new Waypoint[]{AutoConstants.RED_LEFT_FRONT_HATCH_REVERSED, AutoConstants.RED_LEFT_HELPER_POINT}, 20, .8, 50, 50, 100,50,0,false,false, "R_LEFT_FRONT_CS_TO_RIGHT_HELPER");
	public static PathProperties R_LEFT_HELPER_TO_RIGHT_LOADER = new PathProperties(new Waypoint[]{AutoConstants.RED_LEFT_HELPER_POINT_TURN, AutoConstants.RED_LEFT_LOADER_STATION}, 20, .8, 50, 50, 100,0,0,true,true, "R_LEFT_HELPER_TO_RIGHT_LOADER");

	public static PathProperties[] paths = new PathProperties[]{
			B_RIGHT_START_TO_FRONT_CS,
			B_RIGHT_FRONT_CS_TO_RIGHT_HELPER,
			B_RIGHT_HELPER_TO_RIGHT_LOADER,
			B_LEFT_START_TO_FRONT_CS,
			B_LEFT_FRONT_CS_TO_RIGHT_HELPER,
			B_LEFT_HELPER_TO_RIGHT_LOADER,
			R_RIGHT_START_TO_FRONT_CS,
			R_RIGHT_FRONT_CS_TO_RIGHT_HELPER,
			R_RIGHT_HELPER_TO_RIGHT_LOADER,
			R_LEFT_START_TO_FRONT_CS,
			R_LEFT_FRONT_CS_TO_RIGHT_HELPER,
			R_LEFT_HELPER_TO_RIGHT_LOADER
	};

}

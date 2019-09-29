package com.amhsrobotics.autonomous.constants;

import com.amhsrobotics.autonomous.movement.PathProperties;
import com.amhsrobotics.autonomous.movement.PathSequence;
import com.amhsrobotics.purepursuit.Waypoint;

public class AutoPaths {
	public static PathProperties B_LEFT_START_TO_FRONT_CS = new PathProperties(new Waypoint[]{AutoConstants.BLUE_LEFT_START_POS, AutoConstants.BLUE_LEFT_FRONT_HATCH}, 15, .8, 10, 10, 50,0,0,true,true, "B_LEFT_START_TO_FRONT_CS");
	public static PathProperties B_LEFT_FRONT_CS_TO_LEFT_HELPER = new PathProperties(new Waypoint[]{AutoConstants.BLUE_LEFT_FRONT_HATCH_REVERSED, AutoConstants.BLUE_LEFT_HELPER_POINT}, 15, .8, 10, 10, 50,0,0,false,false, "B_LEFT_FRONT_CS_TO_LEFT_HELPER");
	public static PathProperties B_LEFT_HELPER_TO_LEFT_LOADER = new PathProperties(new Waypoint[]{AutoConstants.BLUE_LEFT_HELPER_POINT_REVERSED, AutoConstants.BLUE_LEFT_LOADER_STATION}, 15, .8, 10, 10, 50,0,0,true,true, "B_LEFT_HELPER_TO_LEFT_LOADER");
	public static PathProperties R_LEFT_START_TO_FRONT_CS = new PathProperties(new Waypoint[]{AutoConstants.RED_LEFT_START_POS, AutoConstants.RED_LEFT_FRONT_HATCH}, 15, .8, 10, 10, 50,0,0,true,true, "R_LEFT_START_TO_FRONT_CS");
	public static PathProperties R_LEFT_FRONT_CS_TO_LEFT_HELPER = new PathProperties(new Waypoint[]{AutoConstants.RED_LEFT_FRONT_HATCH_REVERSED, AutoConstants.RED_LEFT_HELPER_POINT}, 15, .8, 10, 10, 50,0,0,false,false, "R_LEFT_FRONT_CS_TO_LEFT_HELPER");
	public static PathProperties R_LEFT_HELPER_TO_LEFT_LOADER = new PathProperties(new Waypoint[]{AutoConstants.RED_LEFT_HELPER_POINT_REVERSED, AutoConstants.RED_LEFT_LOADER_STATION}, 15, .8, 10, 10, 50,0,0,true,true, "R_LEFT_HELPER_TO_LEFT_LOADER");

	public static PathProperties[] paths = new PathProperties[]{
			B_LEFT_START_TO_FRONT_CS,
			B_LEFT_FRONT_CS_TO_LEFT_HELPER,
			B_LEFT_HELPER_TO_LEFT_LOADER,
			R_LEFT_START_TO_FRONT_CS,
			R_LEFT_FRONT_CS_TO_LEFT_HELPER,
			R_LEFT_HELPER_TO_LEFT_LOADER
	};

}

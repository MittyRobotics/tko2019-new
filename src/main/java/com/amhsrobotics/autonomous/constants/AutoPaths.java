package com.amhsrobotics.autonomous.constants;

import com.amhsrobotics.autonomous.movement.PathProperties;
import com.amhsrobotics.purepursuit.Waypoint;

public class AutoPaths {
	public static PathProperties B_RIGHT_START_TO_FRONT_CS = new PathProperties(new Waypoint[]{AutoConstants.BLUE_RIGHT_START_POS, AutoConstants.BLUE_RIGHT_FRONT_HATCH}, 15, .8, 50, 50, 200,0,0,true,true, "B_RIGHT_END_HAB_TO_FRONT_CS");
	public static PathProperties B_RIGHT_FRONT_CS_TO_RIGHT_HELPER = new PathProperties(new Waypoint[]{AutoConstants.BLUE_RIGHT_FRONT_HATCH_REVERSED, AutoConstants.BLUE_RIGHT_HELPER_POINT}, 15, .8, 50, 50, 200,0,0,false,false, "B_RIGHT_FRONT_CS_TO_RIGHT_HELPER");
	public static PathProperties B_RIGHT_HELPER_TO_RIGHT_LOADER = new PathProperties(new Waypoint[]{AutoConstants.BLUE_RIGHT_HELPER_POINT_TURN, AutoConstants.BLUE_RIGHT_LOADER_STATION}, 15, .8, 50, 50, 200,0,0,true,false, "B_RIGHT_HELPER_TO_RIGHT_LOADER");

	public static PathProperties[] paths = new PathProperties[]{
			B_RIGHT_START_TO_FRONT_CS,
			B_RIGHT_FRONT_CS_TO_RIGHT_HELPER,
			B_RIGHT_HELPER_TO_RIGHT_LOADER
	};

}

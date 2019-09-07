package com.amhsrobotics.autonomous.constants;

import com.amhsrobotics.purepursuit.Waypoint;

public class AutoPaths {
	public static Waypoint[][] BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET = new Waypoint[][]{
			{AutoConstants.BLUE_LEFT_START_POS, AutoConstants.BLUE_LEFT_FRONT_HATCH},
			{AutoConstants.BLUE_LEFT_FRONT_HATCH_REVERSED, AutoConstants.BLUE_LEFT_HELPER_POINT},
			{AutoConstants.BLUE_LEFT_HELPER_POINT_REVERSED, AutoConstants.BLUE_LEFT_LOADER_STATION},
			{AutoConstants.BLUE_LEFT_LOADER_STATION_REVERSED, AutoConstants.BLUE_LEFT_HELPER_POINT},
			{AutoConstants.BLUE_LEFT_HELPER_POINT_REVERSED, AutoConstants.BLUE_LEFT_ROCKET_HATCH}
	};
}

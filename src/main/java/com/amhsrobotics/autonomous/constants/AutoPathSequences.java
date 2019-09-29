package com.amhsrobotics.autonomous.constants;

import com.amhsrobotics.autonomous.movement.PathProperties;
import com.amhsrobotics.autonomous.movement.PathSequence;
import com.amhsrobotics.purepursuit.Waypoint;

public class AutoPathSequences {
	public static PathSequence BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET = new PathSequence("Blue Left Hatch Cargoship Hatch Rocket",
			new PathProperties[]{
					new PathProperties(AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[0], 15, .8, 10, 10, 50,0,0,false,false, ""),
					new PathProperties(AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[1], 15, .8, 10, 10, 50,0,0,false,false, ""),
					new PathProperties(AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[2], 15, .8, 10, 10, 50,0,0,false,false, ""),
					new PathProperties(AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[3], 15, .8, 10, 10, 50,0,0,false,false, ""),
					new PathProperties(AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[4], 15, .8, 10, 10, 50,0,0,false,false, "")
	});
	public static PathSequence RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET = new PathSequence("Red Left Hatch Cargoship Hatch Rocket",
			new PathProperties[]{
					new PathProperties(AutoWaypoints.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[0], 15, .8, 10, 10, 50,0,0,false,false, ""),
					new PathProperties(AutoWaypoints.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[1], 15, .8, 10, 10, 50,0,0,false,false, ""),
					new PathProperties(AutoWaypoints.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[2], 15, .8, 10, 10, 50,0,0,false,false, ""),
					new PathProperties(AutoWaypoints.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[3], 15, .8, 10, 10, 50,0,0,false,false, ""),
					new PathProperties(AutoWaypoints.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[4], 15, .8, 10, 10, 50,0,0,false,false, "")
			});
	public static PathSequence BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET = new PathSequence("Blue Right Hatch Cargoship Hatch Rocket",
			new PathProperties[]{
					new PathProperties(AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[0], 15, .8, 10, 10, 50,0,0,false,false, ""),
					new PathProperties(AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[1], 15, .8, 10, 10, 50,0,0,false,false, ""),
					new PathProperties(AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[2], 15, .8, 10, 10, 50,0,0,false,false, ""),
					new PathProperties(AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[3], 15, .8, 10, 10, 50,0,0,false,false, ""),
					new PathProperties(AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[4], 15, .8, 10, 10, 50,0,0,false,false, "")
			});
	public static PathSequence RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET = new PathSequence("Red Right Hatch Cargoship Hatch Rocket",
			new PathProperties[]{
					new PathProperties(AutoWaypoints.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[0], 15, .8, 10, 10, 50,0,0,false,false, ""),
					new PathProperties(AutoWaypoints.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[1], 15, .8, 10, 10, 50,0,0,false,false, ""),
					new PathProperties(AutoWaypoints.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[2], 15, .8, 10, 10, 50,0,0,false,false, ""),
					new PathProperties(AutoWaypoints.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[3], 15, .8, 10, 10, 50,0,0,false,false, ""),
					new PathProperties(AutoWaypoints.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[4], 15, .8, 10, 10, 50,0,0,false,false, "")
			});

	public static PathSequence[] sequences = new PathSequence[]{BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET,RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET,BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET,RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET};

	public static PathProperties B_LEFT_START_TO_FRONT_CS = new PathProperties(new Waypoint[]{AutoConstants.BLUE_LEFT_START_POS, AutoConstants.BLUE_LEFT_FRONT_HATCH}, 15, .8, 10, 10, 50,0,0,true,true, "B_LEFT_START_TO_FRONT_CS");
	public static PathProperties B_LEFT_FRONT_CS_TO_LEFT_HELPER = new PathProperties(new Waypoint[]{AutoConstants.BLUE_LEFT_FRONT_HATCH, AutoConstants.BLUE_LEFT_HELPER_POINT}, 15, .8, 10, 10, 50,0,0,false,false, "B_LEFT_FRONT_CS_TO_LEFT_HELPER");
	public static PathProperties B_LEFT_HELPER_TO_LEFT_LOADER = new PathProperties(new Waypoint[]{AutoConstants.BLUE_LEFT_HELPER_POINT, AutoConstants.BLUE_LEFT_LOADER_STATION}, 15, .8, 10, 10, 50,0,0,true,true, "B_LEFT_HELPER_TO_LEFT_LOADER");
	public static PathProperties R_LEFT_START_TO_FRONT_CS = new PathProperties(new Waypoint[]{AutoConstants.RED_LEFT_START_POS, AutoConstants.RED_LEFT_FRONT_HATCH}, 15, .8, 10, 10, 50,0,0,true,true, "R_LEFT_START_TO_FRONT_CS");
	public static PathProperties R_LEFT_FRONT_CS_TO_LEFT_HELPER = new PathProperties(new Waypoint[]{AutoConstants.RED_LEFT_FRONT_HATCH, AutoConstants.RED_LEFT_HELPER_POINT}, 15, .8, 10, 10, 50,0,0,false,false, "R_LEFT_FRONT_CS_TO_LEFT_HELPER");
	public static PathProperties R_LEFT_HELPER_TO_LEFT_LOADER = new PathProperties(new Waypoint[]{AutoConstants.RED_LEFT_HELPER_POINT, AutoConstants.RED_LEFT_LOADER_STATION}, 15, .8, 10, 10, 50,0,0,true,true, "R_LEFT_HELPER_TO_LEFT_LOADER");

	public static PathProperties[] paths = new PathProperties[]{
			B_LEFT_START_TO_FRONT_CS,
			B_LEFT_FRONT_CS_TO_LEFT_HELPER,
			B_LEFT_HELPER_TO_LEFT_LOADER,
			R_LEFT_START_TO_FRONT_CS,
			R_LEFT_FRONT_CS_TO_LEFT_HELPER,
			R_LEFT_HELPER_TO_LEFT_LOADER
	};

}

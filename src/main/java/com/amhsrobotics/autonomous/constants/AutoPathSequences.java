package com.amhsrobotics.autonomous.constants;

import com.amhsrobotics.autonomous.movement.PathProperties;
import com.amhsrobotics.autonomous.movement.PathSequence;

public class AutoPathSequences {
	public static PathSequence BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET = new PathSequence("Blue Left Hatch Cargoship Hatch Rocket",
			new PathProperties[]{
					new PathProperties(AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[0], 15, .8, 10, 10, 50),
					new PathProperties(AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[1], 15, .8, 10, 10, 50),
					new PathProperties(AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[2], 15, .8, 10, 10, 50),
					new PathProperties(AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[3], 15, .8, 10, 10, 50),
					new PathProperties(AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[4], 15, .8, 10, 10, 50)
	});
	public static PathSequence RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET = new PathSequence("Red Left Hatch Cargoship Hatch Rocket",
			new PathProperties[]{
					new PathProperties(AutoWaypoints.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[0], 15, .8, 10, 10, 50),
					new PathProperties(AutoWaypoints.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[1], 15, .8, 10, 10, 50),
					new PathProperties(AutoWaypoints.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[2], 15, .8, 10, 10, 50),
					new PathProperties(AutoWaypoints.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[3], 15, .8, 10, 10, 50),
					new PathProperties(AutoWaypoints.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[4], 15, .8, 10, 10, 50)
			});
	public static PathSequence BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET = new PathSequence("Blue Right Hatch Cargoship Hatch Rocket",
			new PathProperties[]{
					new PathProperties(AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[0], 15, .8, 10, 10, 50),
					new PathProperties(AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[1], 15, .8, 10, 10, 50),
					new PathProperties(AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[2], 15, .8, 10, 10, 50),
					new PathProperties(AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[3], 15, .8, 10, 10, 50),
					new PathProperties(AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[4], 15, .8, 10, 10, 50)
			});
	public static PathSequence RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET = new PathSequence("Red Right Hatch Cargoship Hatch Rocket",
			new PathProperties[]{
					new PathProperties(AutoWaypoints.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[0], 15, .8, 10, 10, 50),
					new PathProperties(AutoWaypoints.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[1], 15, .8, 10, 10, 50),
					new PathProperties(AutoWaypoints.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[2], 15, .8, 10, 10, 50),
					new PathProperties(AutoWaypoints.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[3], 15, .8, 10, 10, 50),
					new PathProperties(AutoWaypoints.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[4], 15, .8, 10, 10, 50)
			});

	public static PathSequence[] sequences = new PathSequence[]{BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET,RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET,BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET,RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET};
}

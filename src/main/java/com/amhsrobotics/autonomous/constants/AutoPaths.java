package com.amhsrobotics.autonomous.constants;

import com.amhsrobotics.purepursuit.Path;
import com.amhsrobotics.purepursuit.PathGenerator;
import com.amhsrobotics.purepursuit.Waypoint;
import com.amhsrobotics.purepursuit.enums.PathType;

import java.awt.geom.Point2D;

public class AutoPaths {

	public static Path[] BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET = new Path[]{
			PathGenerator.getInstance().generate(new Waypoint[]{AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[0][0], AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[0][1]}, PathType.CUBIC_HERMITE_PATH,150,150,100,200),
			PathGenerator.getInstance().generate(new Waypoint[]{AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[1][0], AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[1][1]}, PathType.CUBIC_HERMITE_PATH,150,150,100,200),
			PathGenerator.getInstance().generate(new Waypoint[]{AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[2][0], AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[2][1]}, PathType.CUBIC_HERMITE_PATH,150,150,100,200),
			PathGenerator.getInstance().generate(new Waypoint[]{AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[3][0], AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[3][1]}, PathType.CUBIC_HERMITE_PATH,150,150,100,200),
			PathGenerator.getInstance().generate(new Waypoint[]{AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[4][0], AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[4][1]}, PathType.CUBIC_HERMITE_PATH,150,150,100,200)
	};

	public static Path[] RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET = new Path[]{
			PathGenerator.getInstance().generate(new Waypoint[]{AutoWaypoints.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[0][0], AutoWaypoints.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[0][1]}, PathType.CUBIC_HERMITE_PATH,150,150,100,200),
			PathGenerator.getInstance().generate(new Waypoint[]{AutoWaypoints.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[1][0], AutoWaypoints.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[1][1]}, PathType.CUBIC_HERMITE_PATH,2150,150,100,200),
			PathGenerator.getInstance().generate(new Waypoint[]{AutoWaypoints.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[2][0], AutoWaypoints.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[2][1]}, PathType.CUBIC_HERMITE_PATH,150,150,100,200),
			PathGenerator.getInstance().generate(new Waypoint[]{AutoWaypoints.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[3][0], AutoWaypoints.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[3][1]}, PathType.CUBIC_HERMITE_PATH,150,150,100,200),
			PathGenerator.getInstance().generate(new Waypoint[]{AutoWaypoints.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[4][0], AutoWaypoints.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[4][1]}, PathType.CUBIC_HERMITE_PATH,2150,150,100,200)
	};


	public static Path[] BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET = new Path[]{
			PathGenerator.getInstance().generate(new Waypoint[]{AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[0][0], AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[0][1]}, PathType.CUBIC_HERMITE_PATH,150,150,100,200),
			PathGenerator.getInstance().generate(new Waypoint[]{AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[1][0], AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[1][1]}, PathType.CUBIC_HERMITE_PATH,150,150,100,200),
			PathGenerator.getInstance().generate(new Waypoint[]{AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[2][0], AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[2][1]}, PathType.CUBIC_HERMITE_PATH,150,150,100,200),
			PathGenerator.getInstance().generate(new Waypoint[]{AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[3][0], AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[3][1]}, PathType.CUBIC_HERMITE_PATH,150,150,100,200),
			PathGenerator.getInstance().generate(new Waypoint[]{AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[4][0], AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[4][1]}, PathType.CUBIC_HERMITE_PATH,150,150,100,200)
	};


	public static Path[] RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET = new Path[]{
			PathGenerator.getInstance().generate(new Waypoint[]{AutoWaypoints.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[0][0], AutoWaypoints.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[0][1]}, PathType.CUBIC_HERMITE_PATH,150,150,100,200),
			PathGenerator.getInstance().generate(new Waypoint[]{AutoWaypoints.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[1][0], AutoWaypoints.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[1][1]}, PathType.CUBIC_HERMITE_PATH,150,150,100,200),
			PathGenerator.getInstance().generate(new Waypoint[]{AutoWaypoints.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[2][0], AutoWaypoints.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[2][1]}, PathType.CUBIC_HERMITE_PATH,150,150,100,200),
			PathGenerator.getInstance().generate(new Waypoint[]{AutoWaypoints.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[3][0], AutoWaypoints.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[3][1]}, PathType.CUBIC_HERMITE_PATH,150,150,100,200),
			PathGenerator.getInstance().generate(new Waypoint[]{AutoWaypoints.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[4][0], AutoWaypoints.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[4][1]}, PathType.CUBIC_HERMITE_PATH,150,150,100,200)
	};

}

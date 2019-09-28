package com.amhsrobotics.autonomous.constants;

import com.amhsrobotics.autonomous.movement.VelocityConstraints;
import com.amhsrobotics.purepursuit.Waypoint;


import java.awt.geom.Point2D;

public class AutoConstants {
	/**
	 * Velocity constraints for drive train.
	 * Units: maxAcceleration: inches/second^2 maxVelocity: inches/second
	 */
	public static VelocityConstraints DRIVE_VELOCITY_CONSTRAINTS = new VelocityConstraints(20,100,500);
	/**Inches per angle value for motion profiling turning in place.*/
	public static double INCHES_PER_ANGLE = 2;

	//Robot measurements (in inches)

	public static double ROBOT_CENTER_TO_BACK = 14.5;
	public static double ROBOT_CENTER_TO_EDGE = 14.5;



	//Field dimensions:
	public static double FIELD_HEIGHT = 324;
	public static double FIELD_LENGTH = 648;

	//Cad measurements:
	public static double ROCKET_HATCH_CENTER_TO_OUTER_EDGE = 10.336; //This value was measured in CAD and should be relatively constant throughout all field elements


	//-----Cleaned calibration measurements-----

	public static double BLUE_RIGHT_FIELD_EDGE_TO_LOADER_CENTER = 27.44;
	public static double BLUE_LEFT_FIELD_EDGE_TO_LOADER_CENTER = 27.44;
	public static double BLUE_RIGHT_FIELD_EDGE_TO_HAB_1_INNER_EDGE = 97;
	public static double BLUE_LEFT_FIELD_EDGE_TO_HAB_1_INNER_EDGE = 97;
	public static double BLUE_HAB_1_LENGTH = 48.28;
	public static double BLUE_HAB_2_LENGTH = 47;
	public static double BLUE_HAB_LENGTH = BLUE_HAB_1_LENGTH + BLUE_HAB_2_LENGTH;
	public static double BLUE_HAB_OUTER_EDGE_TO_CARGO_SHIP_FRONT = 124.97;
	public static double BLUE_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE = 10.88;
	public static double BLUE_LEFT_FIELD_BACK_TO_ROCKET_HATCH_CENTER = 213.57;
	public static double BLUE_RIGHT_FIELD_BACK_TO_ROCKET_HATCH_CENTER = 213.57;

	public static double BLUE_LEFT_ROCKET_EXTRUSION = 8;
	public static double BLUE_RIGHT_ROCKET_EXTRUSION = 8;

	public static double BLUE_HELPER_DISTANCE_FROM_LOADER = 150.000;



	//Start pos
	public static Waypoint BLUE_RIGHT_START_POS = new Waypoint(new Point2D.Double(BLUE_HAB_2_LENGTH + ROBOT_CENTER_TO_BACK, (BLUE_RIGHT_FIELD_EDGE_TO_HAB_1_INNER_EDGE + ROBOT_CENTER_TO_EDGE)), 0);
	public static Waypoint BLUE_CENTER_START_POS = new Waypoint(new Point2D.Double(BLUE_HAB_2_LENGTH + ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2), 0);
	public static Waypoint BLUE_LEFT_START_POS = new Waypoint(new Point2D.Double(BLUE_HAB_2_LENGTH + ROBOT_CENTER_TO_BACK, FIELD_HEIGHT - (BLUE_LEFT_FIELD_EDGE_TO_HAB_1_INNER_EDGE + ROBOT_CENTER_TO_EDGE)), 0);

	//Cargo Ship Front Hatch
	public static Waypoint BLUE_RIGHT_FRONT_HATCH = new Waypoint(new Point2D.Double((BLUE_HAB_LENGTH + BLUE_HAB_OUTER_EDGE_TO_CARGO_SHIP_FRONT) - ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2- BLUE_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);
	public static Waypoint BLUE_LEFT_FRONT_HATCH = new Waypoint(new Point2D.Double((BLUE_HAB_LENGTH + BLUE_HAB_OUTER_EDGE_TO_CARGO_SHIP_FRONT) - ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2+ BLUE_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);

	public static Waypoint BLUE_RIGHT_FRONT_HATCH_REVERSED = new Waypoint(new Point2D.Double((BLUE_HAB_LENGTH + BLUE_HAB_OUTER_EDGE_TO_CARGO_SHIP_FRONT) - ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2- BLUE_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 180);
	public static Waypoint BLUE_LEFT_FRONT_HATCH_REVERSED = new Waypoint(new Point2D.Double((BLUE_HAB_LENGTH + BLUE_HAB_OUTER_EDGE_TO_CARGO_SHIP_FRONT) - ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2+ BLUE_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 180);


	//Loader
	public static Waypoint BLUE_RIGHT_LOADER_STATION = new Waypoint(new Point2D.Double(ROBOT_CENTER_TO_BACK, FIELD_HEIGHT - BLUE_LEFT_FIELD_EDGE_TO_LOADER_CENTER), 180);
	public static Waypoint BLUE_LEFT_LOADER_STATION = new Waypoint(new Point2D.Double(ROBOT_CENTER_TO_BACK, BLUE_LEFT_FIELD_EDGE_TO_LOADER_CENTER), 180);

	public static Waypoint BLUE_RIGHT_LOADER_STATION_REVERSED = new Waypoint(new Point2D.Double(ROBOT_CENTER_TO_BACK, BLUE_RIGHT_FIELD_EDGE_TO_LOADER_CENTER), 0);
	public static Waypoint BLUE_LEFT_LOADER_STATION_REVERSED = new Waypoint(new Point2D.Double(ROBOT_CENTER_TO_BACK, FIELD_HEIGHT - BLUE_LEFT_FIELD_EDGE_TO_LOADER_CENTER), 0);


	//Rocket Hatch
	public static Waypoint BLUE_RIGHT_ROCKET_HATCH = new Waypoint(new Point2D.Double((BLUE_RIGHT_FIELD_BACK_TO_ROCKET_HATCH_CENTER)  - (Math.cos(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK)  ,(BLUE_RIGHT_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE) + (Math.sin(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK)) , -28.75);
	public static Waypoint BLUE_LEFT_ROCKET_HATCH = new Waypoint(new Point2D.Double((BLUE_LEFT_FIELD_BACK_TO_ROCKET_HATCH_CENTER)   - (Math.cos(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK)  ,FIELD_HEIGHT - ((BLUE_LEFT_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE) + (Math.sin(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK))) , 28.75);

	public static Waypoint BLUE_RIGHT_ROCKET_HATCH_REVERSED = new Waypoint(new Point2D.Double((BLUE_RIGHT_FIELD_BACK_TO_ROCKET_HATCH_CENTER)  - (Math.cos(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK)  ,(BLUE_RIGHT_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE) + (Math.sin(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK)) , 118.75);
	public static Waypoint BLUE_LEFT_ROCKET_HATCH_REVERSED = new Waypoint(new Point2D.Double((BLUE_LEFT_FIELD_BACK_TO_ROCKET_HATCH_CENTER)   - (Math.cos(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK)  ,FIELD_HEIGHT - ((BLUE_LEFT_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE) + (Math.sin(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK))) , -118.75);


	//Helper points
	public static Waypoint BLUE_RIGHT_HELPER_POINT = new Waypoint(new Point2D.Double(BLUE_HELPER_DISTANCE_FROM_LOADER,BLUE_RIGHT_LOADER_STATION.getWaypoint().getY()), 90);
	public static Waypoint BLUE_LEFT_HELPER_POINT = new Waypoint(new Point2D.Double(BLUE_HELPER_DISTANCE_FROM_LOADER,BLUE_LEFT_LOADER_STATION.getWaypoint().getY()), -90);

	public static Waypoint BLUE_RIGHT_HELPER_POINT_REVERSED = new Waypoint(new Point2D.Double(BLUE_HELPER_DISTANCE_FROM_LOADER,BLUE_RIGHT_LOADER_STATION.getWaypoint().getY()), -90);
	public static Waypoint BLUE_LEFT_HELPER_POINT_REVERSED = new Waypoint(new Point2D.Double(BLUE_HELPER_DISTANCE_FROM_LOADER,BLUE_LEFT_LOADER_STATION.getWaypoint().getY()), 90);


	public static Waypoint BLUE_RIGHT_HELPER_POINT_TURN = new Waypoint(new Point2D.Double(BLUE_HELPER_DISTANCE_FROM_LOADER,BLUE_RIGHT_LOADER_STATION.getWaypoint().getY()), 180);
	public static Waypoint BLUE_LEFT_HELPER_POINT_TURN = new Waypoint(new Point2D.Double(BLUE_HELPER_DISTANCE_FROM_LOADER,BLUE_LEFT_LOADER_STATION.getWaypoint().getY()), 180);


	//------------Standard points (points without robot dimensions taken into account)-------------------

	//Start pos
	public static Waypoint BLUE_RIGHT_START_POS_STANDARD = new Waypoint(new Point2D.Double(BLUE_HAB_2_LENGTH, (BLUE_RIGHT_FIELD_EDGE_TO_HAB_1_INNER_EDGE + ROBOT_CENTER_TO_EDGE)), 0);
	public static Waypoint BLUE_CENTER_START_POS_STANDARD = new Waypoint(new Point2D.Double(BLUE_HAB_2_LENGTH, FIELD_HEIGHT /2), 0);
	public static Waypoint BLUE_LEFT_START_POS_STANDARD = new Waypoint(new Point2D.Double(BLUE_HAB_2_LENGTH, FIELD_HEIGHT - (BLUE_LEFT_FIELD_EDGE_TO_HAB_1_INNER_EDGE + ROBOT_CENTER_TO_EDGE)), 0);

	//Cargo Ship Front Hatch
	public static Waypoint BLUE_RIGHT_FRONT_HATCH_STANDARD = new Waypoint(new Point2D.Double((BLUE_HAB_LENGTH + BLUE_HAB_OUTER_EDGE_TO_CARGO_SHIP_FRONT), FIELD_HEIGHT /2- BLUE_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);
	public static Waypoint BLUE_LEFT_FRONT_HATCH_STANDARD = new Waypoint(new Point2D.Double((BLUE_HAB_LENGTH + BLUE_HAB_OUTER_EDGE_TO_CARGO_SHIP_FRONT), FIELD_HEIGHT /2+ BLUE_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);

	//Loader
	public static Waypoint BLUE_RIGHT_LOADER_STATION_STANDARD = new Waypoint(new Point2D.Double(0, BLUE_RIGHT_FIELD_EDGE_TO_LOADER_CENTER), 28.75);
	public static Waypoint BLUE_LEFT_LOADER_STATION_STANDARD = new Waypoint(new Point2D.Double(0, FIELD_HEIGHT - BLUE_LEFT_FIELD_EDGE_TO_LOADER_CENTER), -28.75);

	//Rocket Hatch
	public static Waypoint BLUE_RIGHT_ROCKET_HATCH_STANDARD = new Waypoint(new Point2D.Double(BLUE_RIGHT_FIELD_BACK_TO_ROCKET_HATCH_CENTER, BLUE_RIGHT_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE), 28.75);
	public static Waypoint BLUE_LEFT_ROCKET_HATCH_STANDARD = new Waypoint(new Point2D.Double(BLUE_LEFT_FIELD_BACK_TO_ROCKET_HATCH_CENTER, FIELD_HEIGHT - (BLUE_LEFT_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE)), -28.75);


	//-----Cleaned calibration measurements-----
	public static double RED_RIGHT_FIELD_EDGE_TO_LOADER_CENTER = 27.44;
	public static double RED_LEFT_FIELD_EDGE_TO_LOADER_CENTER = 27.44;
	public static double RED_RIGHT_FIELD_EDGE_TO_HAB_1_INNER_EDGE = 97;
	public static double RED_LEFT_FIELD_EDGE_TO_HAB_1_INNER_EDGE = 97;
	public static double RED_HAB_1_LENGTH = 48.28;
	public static double RED_HAB_2_LENGTH = 47;
	public static double RED_HAB_LENGTH = RED_HAB_1_LENGTH + RED_HAB_2_LENGTH;
	public static double RED_HAB_OUTER_EDGE_TO_CARGO_SHIP_FRONT = 124.97;
	public static double RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE = 10.88;
	public static double RED_LEFT_FIELD_BACK_TO_ROCKET_HATCH_CENTER = 213.57;
	public static double RED_RIGHT_FIELD_BACK_TO_ROCKET_HATCH_CENTER = 213.57;

	public static double RED_LEFT_ROCKET_EXTRUSION = 8;
	public static double RED_RIGHT_ROCKET_EXTRUSION = 8;

	public static double RED_HELPER_DISTANCE_FROM_LOADER = 150.000;



	//Start pos
	public static Waypoint RED_RIGHT_START_POS = new Waypoint(new Point2D.Double(RED_HAB_2_LENGTH + ROBOT_CENTER_TO_BACK, (RED_RIGHT_FIELD_EDGE_TO_HAB_1_INNER_EDGE + ROBOT_CENTER_TO_EDGE)), 0);
	public static Waypoint RED_CENTER_START_POS = new Waypoint(new Point2D.Double(RED_HAB_2_LENGTH + ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2), 0);
	public static Waypoint RED_LEFT_START_POS = new Waypoint(new Point2D.Double(RED_HAB_2_LENGTH + ROBOT_CENTER_TO_BACK, FIELD_HEIGHT - (RED_LEFT_FIELD_EDGE_TO_HAB_1_INNER_EDGE + ROBOT_CENTER_TO_EDGE)), 0);

	//Cargo Ship Front Hatch
	public static Waypoint RED_RIGHT_FRONT_HATCH = new Waypoint(new Point2D.Double((RED_HAB_LENGTH + RED_HAB_OUTER_EDGE_TO_CARGO_SHIP_FRONT) - ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2- RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);
	public static Waypoint RED_LEFT_FRONT_HATCH = new Waypoint(new Point2D.Double((RED_HAB_LENGTH + RED_HAB_OUTER_EDGE_TO_CARGO_SHIP_FRONT) - ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2+ RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);

	public static Waypoint RED_RIGHT_FRONT_HATCH_REVERSED = new Waypoint(new Point2D.Double((RED_HAB_LENGTH + RED_HAB_OUTER_EDGE_TO_CARGO_SHIP_FRONT) - ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2- RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 180);
	public static Waypoint RED_LEFT_FRONT_HATCH_REVERSED = new Waypoint(new Point2D.Double((RED_HAB_LENGTH + RED_HAB_OUTER_EDGE_TO_CARGO_SHIP_FRONT) - ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2+ RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 180);


	//Loader
	public static Waypoint RED_RIGHT_LOADER_STATION = new Waypoint(new Point2D.Double(ROBOT_CENTER_TO_BACK, RED_RIGHT_FIELD_EDGE_TO_LOADER_CENTER), 180);
	public static Waypoint RED_LEFT_LOADER_STATION = new Waypoint(new Point2D.Double(ROBOT_CENTER_TO_BACK, FIELD_HEIGHT - RED_LEFT_FIELD_EDGE_TO_LOADER_CENTER), 180);

	public static Waypoint RED_RIGHT_LOADER_STATION_REVERSED = new Waypoint(new Point2D.Double(ROBOT_CENTER_TO_BACK, RED_RIGHT_FIELD_EDGE_TO_LOADER_CENTER), 0);
	public static Waypoint RED_LEFT_LOADER_STATION_REVERSED = new Waypoint(new Point2D.Double(ROBOT_CENTER_TO_BACK, FIELD_HEIGHT - RED_LEFT_FIELD_EDGE_TO_LOADER_CENTER), 0);


	//Rocket Hatch
	public static Waypoint RED_RIGHT_ROCKET_HATCH = new Waypoint(new Point2D.Double((RED_RIGHT_FIELD_BACK_TO_ROCKET_HATCH_CENTER)  - (Math.cos(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK)  ,(RED_RIGHT_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE) + (Math.sin(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK)) , 28.75);
	public static Waypoint RED_LEFT_ROCKET_HATCH = new Waypoint(new Point2D.Double((RED_LEFT_FIELD_BACK_TO_ROCKET_HATCH_CENTER)   - (Math.cos(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK)  ,FIELD_HEIGHT - ((RED_LEFT_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE) + (Math.sin(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK))) , -28.75);

	public static Waypoint RED_RIGHT_ROCKET_HATCH_REVERSED = new Waypoint(new Point2D.Double((RED_RIGHT_FIELD_BACK_TO_ROCKET_HATCH_CENTER)  - (Math.cos(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK)  ,(RED_RIGHT_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE) + (Math.sin(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK)) , 118.75);
	public static Waypoint RED_LEFT_ROCKET_HATCH_REVERSED = new Waypoint(new Point2D.Double((RED_LEFT_FIELD_BACK_TO_ROCKET_HATCH_CENTER)   - (Math.cos(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK)  ,FIELD_HEIGHT - ((RED_LEFT_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE) + (Math.sin(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK))) , -118.75);


	//Helper points
	public static Waypoint RED_RIGHT_HELPER_POINT = new Waypoint(new Point2D.Double(RED_HELPER_DISTANCE_FROM_LOADER, RED_RIGHT_LOADER_STATION.getWaypoint().getY()), -90);
	public static Waypoint RED_LEFT_HELPER_POINT = new Waypoint(new Point2D.Double(RED_HELPER_DISTANCE_FROM_LOADER, RED_LEFT_LOADER_STATION.getWaypoint().getY()), 90);

	public static Waypoint RED_RIGHT_HELPER_POINT_REVERSED = new Waypoint(new Point2D.Double(RED_HELPER_DISTANCE_FROM_LOADER, RED_RIGHT_LOADER_STATION.getWaypoint().getY()), 90);
	public static Waypoint RED_LEFT_HELPER_POINT_REVERSED = new Waypoint(new Point2D.Double(RED_HELPER_DISTANCE_FROM_LOADER, RED_LEFT_LOADER_STATION.getWaypoint().getY()), -90);


	public static Waypoint RED_RIGHT_HELPER_POINT_TURN = new Waypoint(new Point2D.Double(RED_HELPER_DISTANCE_FROM_LOADER, RED_RIGHT_LOADER_STATION.getWaypoint().getY()), 180);
	public static Waypoint RED_LEFT_HELPER_POINT_TURN = new Waypoint(new Point2D.Double(RED_HELPER_DISTANCE_FROM_LOADER, RED_LEFT_LOADER_STATION.getWaypoint().getY()), 180);


	//------------Standard points (points without robot dimensions taken into account)-------------------

	//Start pos
	public static Waypoint RED_RIGHT_START_POS_STANDARD = new Waypoint(new Point2D.Double(RED_HAB_2_LENGTH, (RED_RIGHT_FIELD_EDGE_TO_HAB_1_INNER_EDGE + ROBOT_CENTER_TO_EDGE)), 0);
	public static Waypoint RED_CENTER_START_POS_STANDARD = new Waypoint(new Point2D.Double(RED_HAB_2_LENGTH, FIELD_HEIGHT /2), 0);
	public static Waypoint RED_LEFT_START_POS_STANDARD = new Waypoint(new Point2D.Double(RED_HAB_2_LENGTH, FIELD_HEIGHT - (RED_LEFT_FIELD_EDGE_TO_HAB_1_INNER_EDGE + ROBOT_CENTER_TO_EDGE)), 0);

	//Cargo Ship Front Hatch
	public static Waypoint RED_RIGHT_FRONT_HATCH_STANDARD = new Waypoint(new Point2D.Double((RED_HAB_LENGTH + RED_HAB_OUTER_EDGE_TO_CARGO_SHIP_FRONT), FIELD_HEIGHT /2- RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);
	public static Waypoint RED_LEFT_FRONT_HATCH_STANDARD = new Waypoint(new Point2D.Double((RED_HAB_LENGTH + RED_HAB_OUTER_EDGE_TO_CARGO_SHIP_FRONT), FIELD_HEIGHT /2+ RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);

	//Loader
	public static Waypoint RED_RIGHT_LOADER_STATION_STANDARD = new Waypoint(new Point2D.Double(0, RED_RIGHT_FIELD_EDGE_TO_LOADER_CENTER), 0);
	public static Waypoint RED_LEFT_LOADER_STATION_STANDARD = new Waypoint(new Point2D.Double(0, FIELD_HEIGHT - RED_LEFT_FIELD_EDGE_TO_LOADER_CENTER), 0);

	//Rocket Hatch
	public static Waypoint RED_RIGHT_ROCKET_HATCH_STANDARD = new Waypoint(new Point2D.Double(RED_RIGHT_FIELD_BACK_TO_ROCKET_HATCH_CENTER, RED_RIGHT_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE), 28.75);
	public static Waypoint RED_LEFT_ROCKET_HATCH_STANDARD = new Waypoint(new Point2D.Double(RED_LEFT_FIELD_BACK_TO_ROCKET_HATCH_CENTER, FIELD_HEIGHT - (RED_LEFT_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE)), -28.75);

//
//
//	//----------------BLUE SIDE-----------------
//
//	//Field measurements (in inches)
//
//
//	public static double BLUE_HAB_LENGTH = 95.28;
//	public static double RED_HAB_1_LENGTH = 48.28;
//	public static double BLUE_EDGE_TO_LOADER_TAPE = 27.44;
//	public static double BLUE_LOADER_TAPE_TO_INNER_HAB_EDGE = 69.56;
//	public static double RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE = 10.88;
//	public static double BLUE_FIELD_BACK_TO_CARGO_SHIP = 220.25;
//	public static double BLUE_HAB_2_EDGE_TO_ROCKET_HATCH_CENTER = 166.57;
//	public static double BLUE_ROCKET_EXTRUSION = 8;
//	public static double BLUE_ROCKET_HYPOTENUSE = 21;
//
//	//Helper Point Distance
//	public static double RED_HELPER_DISTANCE_FROM_LOADER = 150.000;
//
//
//	//Robot start points
//	public static Waypoint BLUE_RIGHT_START_POS = new Waypoint(new Point2D.Double(BLUE_HAB_LENGTH - RED_HAB_1_LENGTH + ROBOT_CENTER_TO_BACK, (BLUE_EDGE_TO_LOADER_TAPE + BLUE_LOADER_TAPE_TO_INNER_HAB_EDGE + ROBOT_CENTER_TO_EDGE)), 0);
//	public static Waypoint BLUE_CENTER_START_POS = new Waypoint(new Point2D.Double(BLUE_HAB_LENGTH - RED_HAB_1_LENGTH + ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2), 0);
//	public static Waypoint BLUE_LEFT_START_POS = new Waypoint(new Point2D.Double(BLUE_HAB_LENGTH - RED_HAB_1_LENGTH + ROBOT_CENTER_TO_BACK, FIELD_HEIGHT - (BLUE_EDGE_TO_LOADER_TAPE + BLUE_LOADER_TAPE_TO_INNER_HAB_EDGE + ROBOT_CENTER_TO_EDGE)), 0);
//
//	public static Waypoint BLUE_RIGHT_START_POS_STANDARD = new Waypoint(new Point2D.Double(BLUE_HAB_LENGTH - RED_HAB_1_LENGTH , (BLUE_EDGE_TO_LOADER_TAPE + BLUE_LOADER_TAPE_TO_INNER_HAB_EDGE + ROBOT_CENTER_TO_EDGE)), 0);
//	public static Waypoint BLUE_CENTER_START_POS_STANDARD = new Waypoint(new Point2D.Double(BLUE_HAB_LENGTH - RED_HAB_1_LENGTH , FIELD_HEIGHT /2), 0);
//	public static Waypoint BLUE_LEFT_START_POS_STANDARD = new Waypoint(new Point2D.Double(BLUE_HAB_LENGTH - RED_HAB_1_LENGTH , FIELD_HEIGHT - (BLUE_EDGE_TO_LOADER_TAPE + BLUE_LOADER_TAPE_TO_INNER_HAB_EDGE + ROBOT_CENTER_TO_EDGE)), 0);
//
//
//	//Field waypoints
//	public static Waypoint BLUE_RIGHT_FRONT_HATCH = new Waypoint(new Point2D.Double(BLUE_FIELD_BACK_TO_CARGO_SHIP - ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2-RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);
//	public static Waypoint BLUE_LEFT_FRONT_HATCH = new Waypoint(new Point2D.Double(BLUE_FIELD_BACK_TO_CARGO_SHIP - ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2+RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);
//	public static Waypoint BLUE_RIGHT_LOADER_STATION = new Waypoint(new Point2D.Double(ROBOT_CENTER_TO_BACK,BLUE_EDGE_TO_LOADER_TAPE), 0);
//	public static Waypoint BLUE_LEFT_LOADER_STATION = new Waypoint(new Point2D.Double(ROBOT_CENTER_TO_BACK, FIELD_HEIGHT -BLUE_EDGE_TO_LOADER_TAPE), 0);
//
//
//	public static Waypoint BLUE_RIGHT_LOADER_STATION_STANDARD = new Waypoint(new Point2D.Double(0,BLUE_EDGE_TO_LOADER_TAPE), 0);
//	public static Waypoint BLUE_LEFT_LOADER_STATION_STANDARD = new Waypoint(new Point2D.Double(0, FIELD_HEIGHT -BLUE_EDGE_TO_LOADER_TAPE), 0);
//
//
//	public static Waypoint BLUE_RIGHT_FRONT_HATCH_STANDARD = new Waypoint(new Point2D.Double(BLUE_FIELD_BACK_TO_CARGO_SHIP, FIELD_HEIGHT /2-RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);
//	public static Waypoint BLUE_LEFT_FRONT_HATCH_STANDARD = new Waypoint(new Point2D.Double(BLUE_FIELD_BACK_TO_CARGO_SHIP, FIELD_HEIGHT /2+RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);
//
//
//	public static Waypoint BLUE_RIGHT_ROCKET_HATCH = new Waypoint(new Point2D.Double((BLUE_HAB_2_EDGE_TO_ROCKET_HATCH_CENTER + (BLUE_HAB_LENGTH - RED_HAB_1_LENGTH))  - (Math.sin(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK)  ,BLUE_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE + (Math.cos(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK)) , 0);
//	public static Waypoint BLUE_LEFT_ROCKET_HATCH = new Waypoint(new Point2D.Double((BLUE_HAB_2_EDGE_TO_ROCKET_HATCH_CENTER + (BLUE_HAB_LENGTH - RED_HAB_1_LENGTH))   - (Math.sin(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK)  ,FIELD_HEIGHT - (BLUE_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE + (Math.cos(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK))) , 0);
//
//	public static Waypoint BLUE_RIGHT_ROCKET_HATCH_STANDARD = new Waypoint(new Point2D.Double((BLUE_HAB_2_EDGE_TO_ROCKET_HATCH_CENTER + (BLUE_HAB_LENGTH - RED_HAB_1_LENGTH)) ,BLUE_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE), 0);
//	public static Waypoint BLUE_LEFT_ROCKET_HATCH_STANDARD = new Waypoint(new Point2D.Double((BLUE_HAB_2_EDGE_TO_ROCKET_HATCH_CENTER + (BLUE_HAB_LENGTH - RED_HAB_1_LENGTH)) , FIELD_HEIGHT - (BLUE_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE)), 0);
//
//	//Helper Points
//	public static Waypoint BLUE_RIGHT_HELPER_POINT = new Waypoint(new Point2D.Double(RED_HELPER_DISTANCE_FROM_LOADER,BLUE_RIGHT_LOADER_STATION.getWaypoint().getY()), 0);
//	public static Waypoint BLUE_LEFT_HELPER_POINT = new Waypoint(new Point2D.Double(RED_HELPER_DISTANCE_FROM_LOADER,BLUE_LEFT_LOADER_STATION.getWaypoint().getY()), 0);
//
//	//-----------------------RED SIDE---------------------
//	//Field measurements (in inches)
//
//	public static double RED_HAB_LENGTH = 95.28;
//	public static double RED_HAB_1_LENGTH = 48.28;
//	public static double RED_EDGE_TO_LOADER_TAPE = 27.44;
//	public static double RED_LOADER_TAPE_TO_INNER_HAB_EDGE = 69.56;
//	public static double RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE = 10.88;
//	public static double RED_FIELD_BACK_TO_CARGO_SHIP = 220.25;
//	public static double RED_HAB_2_EDGE_TO_ROCKET_HATCH_CENTER = 166.57;
//	public static double RED_ROCKET_EXTRUSION = 8;
//	public static double RED_ROCKET_HYPOTENUSE = 21;
//
//	//Helper Point Distance
//	public static double RED_HELPER_DISTANCE_FROM_LOADER = 150.000;
//
//
//	//Robot start points
//	public static Waypoint RED_RIGHT_START_POS = new Waypoint(new Point2D.Double(RED_HAB_LENGTH - RED_HAB_1_LENGTH + ROBOT_CENTER_TO_BACK, (RED_EDGE_TO_LOADER_TAPE + RED_LOADER_TAPE_TO_INNER_HAB_EDGE + ROBOT_CENTER_TO_EDGE)), 0);
//	public static Waypoint RED_CENTER_START_POS = new Waypoint(new Point2D.Double(RED_HAB_LENGTH - RED_HAB_1_LENGTH + ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2), 0);
//	public static Waypoint RED_LEFT_START_POS = new Waypoint(new Point2D.Double(RED_HAB_LENGTH - RED_HAB_1_LENGTH + ROBOT_CENTER_TO_BACK, FIELD_HEIGHT - (RED_EDGE_TO_LOADER_TAPE + RED_LOADER_TAPE_TO_INNER_HAB_EDGE + ROBOT_CENTER_TO_EDGE)), 0);
//
//	public static Waypoint RED_RIGHT_START_POS_STANDARD = new Waypoint(new Point2D.Double(RED_HAB_LENGTH - RED_HAB_1_LENGTH , (RED_EDGE_TO_LOADER_TAPE + RED_LOADER_TAPE_TO_INNER_HAB_EDGE + ROBOT_CENTER_TO_EDGE)), 0);
//	public static Waypoint RED_CENTER_START_POS_STANDARD = new Waypoint(new Point2D.Double(RED_HAB_LENGTH - RED_HAB_1_LENGTH , FIELD_HEIGHT /2), 0);
//	public static Waypoint RED_LEFT_START_POS_STANDARD = new Waypoint(new Point2D.Double(RED_HAB_LENGTH - RED_HAB_1_LENGTH , FIELD_HEIGHT - (RED_EDGE_TO_LOADER_TAPE + RED_LOADER_TAPE_TO_INNER_HAB_EDGE + ROBOT_CENTER_TO_EDGE)), 0);
//
//
//	//Field waypoints
//	public static Waypoint RED_RIGHT_FRONT_HATCH = new Waypoint(new Point2D.Double(RED_FIELD_BACK_TO_CARGO_SHIP - ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2-RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);
//	public static Waypoint RED_LEFT_FRONT_HATCH = new Waypoint(new Point2D.Double(RED_FIELD_BACK_TO_CARGO_SHIP - ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2+RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);
//	public static Waypoint RED_RIGHT_LOADER_STATION = new Waypoint(new Point2D.Double(ROBOT_CENTER_TO_BACK,RED_EDGE_TO_LOADER_TAPE), 0);
//	public static Waypoint RED_LEFT_LOADER_STATION = new Waypoint(new Point2D.Double(ROBOT_CENTER_TO_BACK, FIELD_HEIGHT -RED_EDGE_TO_LOADER_TAPE), 0);
//
//
//	public static Waypoint RED_RIGHT_LOADER_STATION_STANDARD = new Waypoint(new Point2D.Double(0,RED_EDGE_TO_LOADER_TAPE), 0);
//	public static Waypoint RED_LEFT_LOADER_STATION_STANDARD = new Waypoint(new Point2D.Double(0, FIELD_HEIGHT -RED_EDGE_TO_LOADER_TAPE), 0);
//
//
//	public static Waypoint RED_RIGHT_FRONT_HATCH_STANDARD = new Waypoint(new Point2D.Double(RED_FIELD_BACK_TO_CARGO_SHIP, FIELD_HEIGHT /2-RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);
//	public static Waypoint RED_LEFT_FRONT_HATCH_STANDARD = new Waypoint(new Point2D.Double(RED_FIELD_BACK_TO_CARGO_SHIP, FIELD_HEIGHT /2+RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);
//
//
//	public static double redStandardRocketPointX = Math.sin(Math.toRadians(28.75))*RED_ROCKET_HYPOTENUSE;
//
//
//	public static Waypoint RED_RIGHT_ROCKET_HATCH = new Waypoint(new Point2D.Double((RED_HAB_2_EDGE_TO_ROCKET_HATCH_CENTER + (RED_HAB_LENGTH - RED_HAB_1_LENGTH)) - (Math.sin(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK) ,RED_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE + (Math.cos(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK)) , 0);
//	public static Waypoint RED_LEFT_ROCKET_HATCH = new Waypoint(new Point2D.Double((RED_HAB_2_EDGE_TO_ROCKET_HATCH_CENTER + (RED_HAB_LENGTH - RED_HAB_1_LENGTH))  - (Math.sin(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK),FIELD_HEIGHT - (RED_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE + (Math.cos(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK))) , 0);
//
//	public static Waypoint RED_RIGHT_ROCKET_HATCH_STANDARD = new Waypoint(new Point2D.Double((RED_HAB_2_EDGE_TO_ROCKET_HATCH_CENTER + (RED_HAB_LENGTH - RED_HAB_1_LENGTH)) ,RED_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE), 0);
//	public static Waypoint RED_LEFT_ROCKET_HATCH_STANDARD = new Waypoint(new Point2D.Double((RED_HAB_2_EDGE_TO_ROCKET_HATCH_CENTER + (RED_HAB_LENGTH - RED_HAB_1_LENGTH)) , FIELD_HEIGHT - (RED_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE)), 0);
//
//	//Helper Points
//	public static Waypoint RED_RIGHT_HELPER_POINT = new Waypoint(new Point2D.Double(RED_HELPER_DISTANCE_FROM_LOADER,RED_RIGHT_LOADER_STATION.getWaypoint().getY()), 0);
//	public static Waypoint RED_LEFT_HELPER_POINT = new Waypoint(new Point2D.Double(RED_HELPER_DISTANCE_FROM_LOADER,RED_LEFT_LOADER_STATION.getWaypoint().getY()), 0);


}

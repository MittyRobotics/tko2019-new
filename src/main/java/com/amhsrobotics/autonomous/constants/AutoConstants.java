package com.amhsrobotics.autonomous.constants;

import com.amhsrobotics.autonomous.movement.VelocityConstraints;
import com.amhsrobotics.purepursuit.Waypoint;


import java.awt.geom.Point2D;

public class AutoConstants {
	/**
	 * Velocity constraints for drive train.
	 * Units: maxAcceleration: inches/second^2 maxVelocity: inches/second
	 */
	public static VelocityConstraints DRIVE_VELOCITY_CONSTRAINTS = new VelocityConstraints(100,50,300);
	/**Inches per angle value for motion profiling turning in place.*/
	public static double INCHES_PER_ANGLE = 2;

	//Robot measurements (in inches)

	public static double ROBOT_CENTER_TO_BACK = 20;
	public static double ROBOT_CENTER_TO_EDGE = 20;


	public static double FIELD_HEIGHT = 324;
	public static double FIELD_LENGTH = 648;

	public static double ROCKET_HATCH_CENTER_TO_OUTER_EDGE = 10.336; //This value was measured in CAD and should be relatively constant throughout all field elements

	//----------------BLUE SIDE-----------------

	//Field measurements (in inches)


	public static double BLUE_HAB_LENGTH = 95.28;
	public static double BLUE_HAB_1_LENGTH = 48.28;
	public static double BLUE_EDGE_TO_LOADER_TAPE = 27.44;
	public static double BLUE_LOADER_TAPE_TO_INNER_HAB_EDGE = 69.56;
	public static double BLUE_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE = 10.88;
	public static double BLUE_FIELD_BACK_TO_CARGO_SHIP = 220.25;
	public static double BLUE_HAB_2_EDGE_TO_ROCKET_HATCH_CENTER = 166.57;
	public static double BLUE_ROCKET_EXTRUSION = 8; //Measure
	public static double BLUE_ROCKET_HYPOTENUSE = 21; //UNKNOWN, MEASURE PLEASE

	//Helper Point Distance
	public static double BLUE_HELPER_DISTANCE_FROM_LOADER = 150.000;


	//Robot start points
	public static Waypoint BLUE_RIGHT_START_POS = new Waypoint(new Point2D.Double(BLUE_HAB_LENGTH - BLUE_HAB_1_LENGTH + ROBOT_CENTER_TO_BACK, (BLUE_EDGE_TO_LOADER_TAPE + BLUE_LOADER_TAPE_TO_INNER_HAB_EDGE + ROBOT_CENTER_TO_EDGE)), 0);
	public static Waypoint BLUE_CENTER_START_POS = new Waypoint(new Point2D.Double(BLUE_HAB_LENGTH - BLUE_HAB_1_LENGTH + ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2), 0);
	public static Waypoint BLUE_LEFT_START_POS = new Waypoint(new Point2D.Double(BLUE_HAB_LENGTH - BLUE_HAB_1_LENGTH + ROBOT_CENTER_TO_BACK, FIELD_HEIGHT - (BLUE_EDGE_TO_LOADER_TAPE + BLUE_LOADER_TAPE_TO_INNER_HAB_EDGE + ROBOT_CENTER_TO_EDGE)), 0);

	public static Waypoint BLUE_RIGHT_START_POS_STANDARD = new Waypoint(new Point2D.Double(BLUE_HAB_LENGTH - BLUE_HAB_1_LENGTH , (BLUE_EDGE_TO_LOADER_TAPE + BLUE_LOADER_TAPE_TO_INNER_HAB_EDGE + ROBOT_CENTER_TO_EDGE)), 0);
	public static Waypoint BLUE_CENTER_START_POS_STANDARD = new Waypoint(new Point2D.Double(BLUE_HAB_LENGTH - BLUE_HAB_1_LENGTH , FIELD_HEIGHT /2), 0);
	public static Waypoint BLUE_LEFT_START_POS_STANDARD = new Waypoint(new Point2D.Double(BLUE_HAB_LENGTH - BLUE_HAB_1_LENGTH , FIELD_HEIGHT - (BLUE_EDGE_TO_LOADER_TAPE + BLUE_LOADER_TAPE_TO_INNER_HAB_EDGE + ROBOT_CENTER_TO_EDGE)), 0);


	//Field waypoints
	public static Waypoint BLUE_RIGHT_FRONT_HATCH = new Waypoint(new Point2D.Double(BLUE_FIELD_BACK_TO_CARGO_SHIP - ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2-BLUE_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);
	public static Waypoint BLUE_LEFT_FRONT_HATCH = new Waypoint(new Point2D.Double(BLUE_FIELD_BACK_TO_CARGO_SHIP - ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2+BLUE_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);
	public static Waypoint BLUE_RIGHT_LOADER_STATION = new Waypoint(new Point2D.Double(ROBOT_CENTER_TO_BACK,BLUE_EDGE_TO_LOADER_TAPE), 0);
	public static Waypoint BLUE_LEFT_LOADER_STATION = new Waypoint(new Point2D.Double(ROBOT_CENTER_TO_BACK, FIELD_HEIGHT -BLUE_EDGE_TO_LOADER_TAPE), 0);


	public static Waypoint BLUE_RIGHT_LOADER_STATION_STANDARD = new Waypoint(new Point2D.Double(0,BLUE_EDGE_TO_LOADER_TAPE), 0);
	public static Waypoint BLUE_LEFT_LOADER_STATION_STANDARD = new Waypoint(new Point2D.Double(0, FIELD_HEIGHT -BLUE_EDGE_TO_LOADER_TAPE), 0);


	public static Waypoint BLUE_RIGHT_FRONT_HATCH_STANDARD = new Waypoint(new Point2D.Double(BLUE_FIELD_BACK_TO_CARGO_SHIP, FIELD_HEIGHT /2-BLUE_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);
	public static Waypoint BLUE_LEFT_FRONT_HATCH_STANDARD = new Waypoint(new Point2D.Double(BLUE_FIELD_BACK_TO_CARGO_SHIP, FIELD_HEIGHT /2+BLUE_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);


	public static double blueStandardRocketPointX = Math.sin(Math.toRadians(28.75))*BLUE_ROCKET_HYPOTENUSE;


	public static Waypoint BLUE_RIGHT_ROCKET_HATCH = new Waypoint(new Point2D.Double((BLUE_HAB_2_EDGE_TO_ROCKET_HATCH_CENTER + (BLUE_HAB_LENGTH - BLUE_HAB_1_LENGTH))  - (Math.sin(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK)  ,BLUE_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE + (Math.cos(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK)) , 0);
	public static Waypoint BLUE_LEFT_ROCKET_HATCH = new Waypoint(new Point2D.Double((BLUE_HAB_2_EDGE_TO_ROCKET_HATCH_CENTER + (BLUE_HAB_LENGTH - BLUE_HAB_1_LENGTH))   - (Math.sin(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK)  ,FIELD_HEIGHT - (BLUE_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE + (Math.cos(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK))) , 0);

	public static Waypoint BLUE_RIGHT_ROCKET_HATCH_STANDARD = new Waypoint(new Point2D.Double((BLUE_HAB_2_EDGE_TO_ROCKET_HATCH_CENTER + (BLUE_HAB_LENGTH - BLUE_HAB_1_LENGTH)) ,BLUE_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE), 0);
	public static Waypoint BLUE_LEFT_ROCKET_HATCH_STANDARD = new Waypoint(new Point2D.Double((BLUE_HAB_2_EDGE_TO_ROCKET_HATCH_CENTER + (BLUE_HAB_LENGTH - BLUE_HAB_1_LENGTH)) , FIELD_HEIGHT - (BLUE_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE)), 0);

	//Helper Points
	public static Waypoint BLUE_RIGHT_HELPER_POINT = new Waypoint(new Point2D.Double(BLUE_HELPER_DISTANCE_FROM_LOADER,BLUE_RIGHT_LOADER_STATION.getWaypoint().getY()), 0);
	public static Waypoint BLUE_LEFT_HELPER_POINT = new Waypoint(new Point2D.Double(BLUE_HELPER_DISTANCE_FROM_LOADER,BLUE_LEFT_LOADER_STATION.getWaypoint().getY()), 0);

	//-----------------------RED SIDE---------------------
	//Field measurements (in inches)

	public static double RED_HAB_LENGTH = 95.28;
	public static double RED_HAB_1_LENGTH = 48.28;
	public static double RED_EDGE_TO_LOADER_TAPE = 27.44;
	public static double RED_LOADER_TAPE_TO_INNER_HAB_EDGE = 69.56;
	public static double RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE = 10.88;
	public static double RED_FIELD_BACK_TO_CARGO_SHIP = 220.25;
	public static double RED_HAB_2_EDGE_TO_ROCKET_HATCH_CENTER = 166.57;
	public static double RED_ROCKET_EXTRUSION = 8; //Measure
	public static double RED_ROCKET_HYPOTENUSE = 21; //UNKNOWN, MEASURE PLEASE

	//Helper Point Distance
	public static double RED_HELPER_DISTANCE_FROM_LOADER = 150.000;


	//Robot start points
	public static Waypoint RED_RIGHT_START_POS = new Waypoint(new Point2D.Double(RED_HAB_LENGTH - RED_HAB_1_LENGTH + ROBOT_CENTER_TO_BACK, (RED_EDGE_TO_LOADER_TAPE + RED_LOADER_TAPE_TO_INNER_HAB_EDGE + ROBOT_CENTER_TO_EDGE)), 0);
	public static Waypoint RED_CENTER_START_POS = new Waypoint(new Point2D.Double(RED_HAB_LENGTH - RED_HAB_1_LENGTH + ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2), 0);
	public static Waypoint RED_LEFT_START_POS = new Waypoint(new Point2D.Double(RED_HAB_LENGTH - RED_HAB_1_LENGTH + ROBOT_CENTER_TO_BACK, FIELD_HEIGHT - (RED_EDGE_TO_LOADER_TAPE + RED_LOADER_TAPE_TO_INNER_HAB_EDGE + ROBOT_CENTER_TO_EDGE)), 0);

	public static Waypoint RED_RIGHT_START_POS_STANDARD = new Waypoint(new Point2D.Double(RED_HAB_LENGTH - RED_HAB_1_LENGTH , (RED_EDGE_TO_LOADER_TAPE + RED_LOADER_TAPE_TO_INNER_HAB_EDGE + ROBOT_CENTER_TO_EDGE)), 0);
	public static Waypoint RED_CENTER_START_POS_STANDARD = new Waypoint(new Point2D.Double(RED_HAB_LENGTH - RED_HAB_1_LENGTH , FIELD_HEIGHT /2), 0);
	public static Waypoint RED_LEFT_START_POS_STANDARD = new Waypoint(new Point2D.Double(RED_HAB_LENGTH - RED_HAB_1_LENGTH , FIELD_HEIGHT - (RED_EDGE_TO_LOADER_TAPE + RED_LOADER_TAPE_TO_INNER_HAB_EDGE + ROBOT_CENTER_TO_EDGE)), 0);


	//Field waypoints
	public static Waypoint RED_RIGHT_FRONT_HATCH = new Waypoint(new Point2D.Double(RED_FIELD_BACK_TO_CARGO_SHIP - ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2-RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);
	public static Waypoint RED_LEFT_FRONT_HATCH = new Waypoint(new Point2D.Double(RED_FIELD_BACK_TO_CARGO_SHIP - ROBOT_CENTER_TO_BACK, FIELD_HEIGHT /2+RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);
	public static Waypoint RED_RIGHT_LOADER_STATION = new Waypoint(new Point2D.Double(ROBOT_CENTER_TO_BACK,RED_EDGE_TO_LOADER_TAPE), 0);
	public static Waypoint RED_LEFT_LOADER_STATION = new Waypoint(new Point2D.Double(ROBOT_CENTER_TO_BACK, FIELD_HEIGHT -RED_EDGE_TO_LOADER_TAPE), 0);


	public static Waypoint RED_RIGHT_LOADER_STATION_STANDARD = new Waypoint(new Point2D.Double(0,RED_EDGE_TO_LOADER_TAPE), 0);
	public static Waypoint RED_LEFT_LOADER_STATION_STANDARD = new Waypoint(new Point2D.Double(0, FIELD_HEIGHT -RED_EDGE_TO_LOADER_TAPE), 0);


	public static Waypoint RED_RIGHT_FRONT_HATCH_STANDARD = new Waypoint(new Point2D.Double(RED_FIELD_BACK_TO_CARGO_SHIP, FIELD_HEIGHT /2-RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);
	public static Waypoint RED_LEFT_FRONT_HATCH_STANDARD = new Waypoint(new Point2D.Double(RED_FIELD_BACK_TO_CARGO_SHIP, FIELD_HEIGHT /2+RED_FIELD_CENTER_TO_HATCH_ALIGNMENT_TAPE), 0);


	public static double redStandardRocketPointX = Math.sin(Math.toRadians(28.75))*RED_ROCKET_HYPOTENUSE;


	public static Waypoint RED_RIGHT_ROCKET_HATCH = new Waypoint(new Point2D.Double((RED_HAB_2_EDGE_TO_ROCKET_HATCH_CENTER + (RED_HAB_LENGTH - RED_HAB_1_LENGTH)) - (Math.sin(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK) ,RED_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE + (Math.cos(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK)) , 0);
	public static Waypoint RED_LEFT_ROCKET_HATCH = new Waypoint(new Point2D.Double((RED_HAB_2_EDGE_TO_ROCKET_HATCH_CENTER + (RED_HAB_LENGTH - RED_HAB_1_LENGTH))  - (Math.sin(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK),FIELD_HEIGHT - (RED_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE + (Math.cos(Math.toRadians(28.75) )* ROBOT_CENTER_TO_BACK))) , 0);

	public static Waypoint RED_RIGHT_ROCKET_HATCH_STANDARD = new Waypoint(new Point2D.Double((RED_HAB_2_EDGE_TO_ROCKET_HATCH_CENTER + (RED_HAB_LENGTH - RED_HAB_1_LENGTH)) ,RED_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE), 0);
	public static Waypoint RED_LEFT_ROCKET_HATCH_STANDARD = new Waypoint(new Point2D.Double((RED_HAB_2_EDGE_TO_ROCKET_HATCH_CENTER + (RED_HAB_LENGTH - RED_HAB_1_LENGTH)) , FIELD_HEIGHT - (RED_ROCKET_EXTRUSION + ROCKET_HATCH_CENTER_TO_OUTER_EDGE)), 0);

	//Helper Points
	public static Waypoint RED_RIGHT_HELPER_POINT = new Waypoint(new Point2D.Double(RED_HELPER_DISTANCE_FROM_LOADER,RED_RIGHT_LOADER_STATION.getWaypoint().getY()), 0);
	public static Waypoint RED_LEFT_HELPER_POINT = new Waypoint(new Point2D.Double(RED_HELPER_DISTANCE_FROM_LOADER,RED_LEFT_LOADER_STATION.getWaypoint().getY()), 0);


}

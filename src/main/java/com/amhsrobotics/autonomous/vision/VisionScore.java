package com.amhsrobotics.autonomous.vision;

import com.amhsrobotics.autonomous.constants.AutoWaypoints;
import com.amhsrobotics.autonomous.movement.commands.Translate2dTrajectory;
import com.amhsrobotics.autonomous.movement.commands.VisionAlignment;
import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.hatchpanel.commands.*;
import com.amhsrobotics.purepursuit.enums.PathType;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class VisionScore extends CommandGroup {
	public VisionScore() {
		//private VisionScore(){} is sad because there is nothing here :(
		//"Everybody needs a friend" -Bob Ross

		requires(DriveTrain.getInstance());
//		requires(Arm.getInstance());
//		requires(Slider.getInstance());
//		Waypoint[] waypoints = new Waypoint[2];
//		waypoints[0] = new Waypoint(new Point2D.Double(0,0),0);
//		waypoints[1] = new Waypoint(new Point2D.Double(90,-45),0);
//
//
//		Waypoint[] waypoints1 = new Waypoint[2];
//		waypoints1[0] = new Waypoint(new Point2D.Double(0,0),0);
//		waypoints1[1] = new Waypoint(new Point2D.Double(30,0),0);
//
//		Waypoint[] waypoints2 = new Waypoint[2];
//		waypoints2[0] = new Waypoint(new Point2D.Double(0,0),0);
//		waypoints2[1] = new Waypoint(new Point2D.Double(50,126),90);
//
//		addSequential(new PushForward());
//
//		addSequential( new Translate2dTrajectory(waypoints1, 100,50,100,  PathType.CUBIC_HERMITE_PATH,50,true));
//		addSequential(new PushBackward());
//		addSequential(new Translate2dTrajectory(waypoints, 150,100,500, PathType.CUBIC_HERMITE_PATH, 100, true));
//
//		System.out.println("next trajectory");
//
//		addSequential(new VisionAlignment());
//		addSequential(new Release());
//		addSequential( new Translate2dTrajectory(waypoints2, 50,50,500, PathType.CUBIC_HERMITE_PATH, 0,false));


		addSequential( new Translate2dTrajectory(AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[0], 150,150,500,  PathType.CUBIC_HERMITE_PATH,100,true));
		addSequential(new VisionAlignment());
		addSequential(new PushBackward());
		addSequential(new Translate2dTrajectory(AutoWaypoints.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET[1], 150,150,500, PathType.CUBIC_HERMITE_PATH, 0, true));

	}

}

package com.amhsrobotics.autonomous.vision;

import com.amhsrobotics.autonomous.movement.commands.Translate2dTrajectory;
import com.amhsrobotics.autonomous.movement.commands.VisionAlignment;
import com.amhsrobotics.cargo.Arm;
import com.amhsrobotics.cargo.commands.Angle;
import com.amhsrobotics.cargo.constants.ArmPosition;
import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.hatchpanel.Grabber;
import com.amhsrobotics.hatchpanel.Pusher;
import com.amhsrobotics.hatchpanel.Slider;
import com.amhsrobotics.hatchpanel.commands.*;
import com.amhsrobotics.purepursuit.Waypoint;
import com.amhsrobotics.purepursuit.enums.PathType;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import java.awt.geom.Point2D;

public class VisionScore extends CommandGroup {
	public VisionScore() {
		//private VisionScore(){} is sad because there is nothing here :(
		//"Everybody needs a friend" -Bob Ross

		requires(DriveTrain.getInstance());
//		requires(Arm.getInstance());
//		requires(Slider.getInstance());
		Waypoint[] waypoints = new Waypoint[2];
		waypoints[0] = new Waypoint(new Point2D.Double(0,0),0);
		waypoints[1] = new Waypoint(new Point2D.Double(90,-45),0);


		Waypoint[] waypoints1 = new Waypoint[2];
		waypoints1[0] = new Waypoint(new Point2D.Double(0,0),0);
		waypoints1[1] = new Waypoint(new Point2D.Double(30,0),0);

		Waypoint[] waypoints2 = new Waypoint[2];
		waypoints2[0] = new Waypoint(new Point2D.Double(0,0),0);
		waypoints2[1] = new Waypoint(new Point2D.Double(50,126),90);

		addSequential(new PushForward());

		addSequential( new Translate2dTrajectory(waypoints1, 100,50,100,  PathType.CUBIC_HERMITE_PATH,50,true));
		addSequential(new PushBackward());
		addSequential(new Translate2dTrajectory(waypoints, 150,100,500, PathType.CUBIC_HERMITE_PATH, 100, true));

		System.out.println("next trajectory");

		addSequential(new VisionAlignment());
		addSequential(new Release());
		addSequential( new Translate2dTrajectory(waypoints2, 50,50,500, PathType.CUBIC_HERMITE_PATH, 0,false));
	}

}

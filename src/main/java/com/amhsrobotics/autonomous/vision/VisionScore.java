package com.amhsrobotics.autonomous.vision;

import com.amhsrobotics.autonomous.movement.commands.Translate2dTrajectory;
import com.amhsrobotics.autonomous.movement.commands.VisionAlignment;
import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.hatchpanel.Grabber;
import com.amhsrobotics.hatchpanel.Pusher;
import com.amhsrobotics.hatchpanel.commands.Grab;
import com.amhsrobotics.hatchpanel.commands.PushForward;
import com.amhsrobotics.purepursuit.Waypoint;
import com.amhsrobotics.purepursuit.enums.PathType;
import edu.wpi.first.wpilibj.command.CommandGroup;
import com.amhsrobotics.hatchpanel.commands.PushBackward;
import com.amhsrobotics.hatchpanel.commands.Release;
import edu.wpi.first.wpilibj.command.WaitCommand;

import java.awt.geom.Point2D;

public class VisionScore extends CommandGroup {
	public VisionScore() {
		//private VisionScore(){} is sad because there is nothing here :(
		//"Everybody needs a friend" -Bob Ross
		requires(DriveTrain.getInstance());
		Waypoint[] waypoints = new Waypoint[2];
		waypoints[0] = new Waypoint(new Point2D.Double(0,0),0);
		waypoints[1] = new Waypoint(new Point2D.Double(50,-50),0);
		Waypoint[] waypoints1 = new Waypoint[2];
		waypoints1[0] = new Waypoint(new Point2D.Double(0,0),0);
		waypoints1[1] = new Waypoint(new Point2D.Double(24,0),0);

		addSequential(new Translate2dTrajectory(waypoints, PathType.CUBIC_HERMITE_PATH, 50, true));

		System.out.println("next trajectory");

		addSequential(new VisionAlignment());
		addSequential(new Release());
		addSequential( new Translate2dTrajectory(waypoints1, PathType.CUBIC_HERMITE_PATH, 0,false));
	}

}

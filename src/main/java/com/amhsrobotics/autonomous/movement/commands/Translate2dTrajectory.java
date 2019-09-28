package com.amhsrobotics.autonomous.movement.commands;

import com.amhsrobotics.autonomous.constants.AutoConstants;
import com.amhsrobotics.autonomous.movement.AutonDriver;
import com.amhsrobotics.autonomous.movement.AutonMotionOutput;
import com.amhsrobotics.autonomous.movement.RateLimiter;
import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.autonomous.Odometry;
import com.amhsrobotics.autonomous.enums.DriveState;
import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.purepursuit.Waypoint;
import com.amhsrobotics.purepursuit.enums.PathType;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Robot path following command to follow a generated path from a set of waypoints.
 */
public class Translate2dTrajectory extends Command {
	Waypoint[] waypoints;
	PathType pathType;
	boolean reversed;

	double endVelocity ;
	double t;

	double maxAcceleration;
	double maxDeceleration;

	double maxVelocity;

	int motionID;

	public Translate2dTrajectory(Waypoint[] waypoints) {
		this(waypoints, PathType.CUBIC_HERMITE_PATH, false);
	}
	public Translate2dTrajectory(Waypoint[] waypoints, PathType pathType) {
		this(waypoints, pathType, false);
	}

	public Translate2dTrajectory(Waypoint[] waypoints, boolean reversed) {
		this(waypoints, PathType.CUBIC_HERMITE_PATH,0, reversed);
	}

	public Translate2dTrajectory(Waypoint[] waypoints, PathType pathType, boolean reversed) {
		this(waypoints, pathType,0, reversed);
	}
	public Translate2dTrajectory(Waypoint[] waypoints, PathType pathType, double endVelocity, boolean reversed) {
//		super("Translate2dTrajectory");
//		requires(DriveTrain.getInstance());
//		this.endVelocity = endVelocity;
//
//		this.waypoints = waypoints;
//		this.pathType = pathType;
//		this.reversed = reversed;
//
//		this.motionID = AutonDriver.getInstance().initNewDriveMethod(DriveState.PURE_PURSUIT);
		this(waypoints, AutoConstants.DRIVE_VELOCITY_CONSTRAINTS.getMaxAcceleration(),  AutoConstants.DRIVE_VELOCITY_CONSTRAINTS.getMaxDeceleration(), AutoConstants.DRIVE_VELOCITY_CONSTRAINTS.getMaxVelocity(),pathType,endVelocity,reversed);
	}

	public Translate2dTrajectory(Waypoint[] waypoints, double maxAcceleration, double maxDeceleration, double maxVelocity,  PathType pathType, double endVelocity, boolean reversed) {
		super("Translate2dTrajectory");
		requires(DriveTrain.getInstance());
		this.endVelocity = endVelocity;

		double initX = waypoints[0].getWaypoint().getX();
		double initY = waypoints[0].getWaypoint().getY();
		for(int i = 0; i < waypoints.length; i++){
			waypoints[i] = new Waypoint(new Point2D.Double(Math.abs(waypoints[i].getWaypoint().getX() - initX), (waypoints[i].getWaypoint().getY() - initY)),waypoints[i].getAngle());

		}


		this.waypoints = waypoints;
		this.pathType = pathType;
		this.reversed = reversed;

		this.maxAcceleration = maxAcceleration;
		this.maxDeceleration = maxDeceleration;
		this.maxVelocity = maxVelocity;

		this.motionID = AutonDriver.getInstance().initNewDriveMethod(DriveState.PURE_PURSUIT);
	}



	@Override
	public void initialize() {
		System.out.println("Init point: " + waypoints[0].getWaypoint().getX() + " " + waypoints[0].getWaypoint().getY());
		System.out.println("Init point: " + waypoints[1].getWaypoint().getX() + " " + waypoints[1].getWaypoint().getY());
		Odometry.getInstance().resetPosition();
		Odometry.getInstance().setPos(waypoints[0].getWaypoint().getX(),waypoints[0].getWaypoint().getY(),waypoints[0].getAngle());
		System.out.println(Odometry.getInstance().getRobotX());
		AutonDriver.getInstance().setupTrajectory(waypoints, maxAcceleration, maxDeceleration, maxVelocity, pathType, endVelocity, reversed);
	}


	@Override
	public void execute() {
		t = timeSinceInitialized();
		AutonMotionOutput output = AutonDriver.getInstance().update(t);
		double endt = timeSinceInitialized();

		//System.out.println("Time for follower calculation: " + (endt-t));

		DriveTrain.getInstance().tankVelocity(output.getLeft(), output.getRight());
	}


	@Override
	public void end() {
		System.out.println("Ending Translate2dTrajectory Command!");
		if(reversed){
			DriveTrain.getInstance().tankVelocity(-endVelocity, -endVelocity);
		}
		else {
			DriveTrain.getInstance().tankVelocity(endVelocity, endVelocity);
		}
	}

	@Override
	public void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return AutonDriver.getInstance().isFinished(motionID);
	}
}
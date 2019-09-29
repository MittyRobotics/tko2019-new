package com.amhsrobotics.autonomous.movement.commands;

import com.amhsrobotics.autonomous.constants.AutoConstants;
import com.amhsrobotics.autonomous.movement.AutonDriver;
import com.amhsrobotics.autonomous.movement.AutonMotionOutput;
import com.amhsrobotics.autonomous.movement.PathProperties;
import com.amhsrobotics.autonomous.movement.RateLimiter;
import com.amhsrobotics.autonomous.vision.Limelight;
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
	double startVelocity;
	double t;

	double maxAcceleration;
	double maxDeceleration;

	double maxVelocity;

	int motionID;

	boolean vision;



	boolean hasTarget = false;
	double distance = 0;
	double angle = 0;
	double prevAngle = 0;
	double prevDist = 0;

	double hasTargetCount = 0;
	double hasTargetCooldown = 10;
	double distanceCount = 0;
	double distanceCooldown = 1;
	double angleCount = 0;
	double angleCooldown = 1;

	double intiVisionDist = 100;
	double targetLockedDistThreshold = 20;
	double targetLockedAngleThreshold = 10;

	double maxDistToEnd = 80;

	public Translate2dTrajectory(PathProperties properties) {
		this(properties.getWaypoints(),properties.getMaxAcceleration(),properties.getMaxDeceleration(),properties.getMaxVelocity(),PathType.CUBIC_HERMITE_PATH,properties.getEndVelocity(),properties.getStartVelocity(), properties.getReversed(), properties.getVision());
	}


	public Translate2dTrajectory(Waypoint[] waypoints, double maxAcceleration, double maxDeceleration, double maxVelocity,  PathType pathType,  double startVelocity, double endVelocity, boolean reversed) {

		this(waypoints, maxAcceleration,maxDeceleration,maxVelocity,pathType,startVelocity, endVelocity,reversed,false);
	}

	public Translate2dTrajectory(Waypoint[] waypoints, double maxAcceleration, double maxDeceleration, double maxVelocity,  PathType pathType, double startVelocity, double endVelocity, boolean reversed, boolean vision) {
		super("Translate2dTrajectory");
		requires(DriveTrain.getInstance());
		this.endVelocity = endVelocity;
		this.startVelocity = startVelocity;

		double initX = waypoints[0].getWaypoint().getX();
		double initY = waypoints[0].getWaypoint().getY();
		double initAngle = waypoints[0].getAngle();
		for(int i = 0; i < waypoints.length; i++){
			waypoints[i] = new Waypoint(new Point2D.Double(Math.abs(waypoints[i].getWaypoint().getX() - initX), (waypoints[i].getWaypoint().getY() - initY)),waypoints[i].getAngle()-initAngle);

		}


		this.waypoints = waypoints;
		this.pathType = pathType;
		this.reversed = reversed;

		this.maxAcceleration = maxAcceleration;
		this.maxDeceleration = maxDeceleration;
		this.maxVelocity = maxVelocity;

		this.motionID = AutonDriver.getInstance().initNewDriveMethod(DriveState.PURE_PURSUIT);

		this.vision = vision;
	}




	@Override
	public void initialize() {
		System.out.println("Init point: " + waypoints[0].getWaypoint().getX() + " " + waypoints[0].getWaypoint().getY());
		System.out.println("Init point: " + waypoints[1].getWaypoint().getX() + " " + waypoints[1].getWaypoint().getY());
		Odometry.getInstance().resetPosition();
		Odometry.getInstance().setPos(waypoints[0].getWaypoint().getX(),waypoints[0].getWaypoint().getY(),waypoints[0].getAngle());
		System.out.println(Odometry.getInstance().getRobotX());
		AutonDriver.getInstance().setupTrajectory(waypoints, maxAcceleration, maxDeceleration, maxVelocity, pathType, startVelocity, endVelocity, reversed);
	}


	@Override
	public void execute() {
		t = timeSinceInitialized();

		if(vision){
			updateLimelight();
		}

		AutonMotionOutput output = AutonDriver.getInstance().update(t);
		double endt = timeSinceInitialized();

		//System.out.println("Time for follower calculation: " + (endt-t));

		DriveTrain.getInstance().tankVelocity(output.getLeft(), output.getRight());
	}

	public void updateLimelight(){
		if(vision){
			Limelight.getInstance().updateLimelightValues();
			hasTarget = Limelight.getInstance().isHasTarget();
			distance = Limelight.getInstance().getTargetYFast();
			angle = Limelight.getInstance().getXAngle();
			if(Math.abs(prevDist - distance) < targetLockedDistThreshold && distance < maxDistToEnd){
				distanceCount ++;
				System.out.println("Target locked dist");
			}
			else{
				distanceCount = 0;
			}
			if(Math.abs(prevAngle - angle) < targetLockedAngleThreshold){
				angleCount ++;
				System.out.println("Target locked angle");
			}
			else{
				angleCount = 0;
			}
			if(hasTarget){
				hasTargetCount ++;
				System.out.println("Target locked has");
			}
			else{
				hasTargetCount = 0;
			}
			prevDist = distance;
			prevAngle = angle;
		}
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
		return AutonDriver.getInstance().isFinished(motionID)  || (hasTargetCount > hasTargetCooldown && distanceCount > distanceCooldown && angleCount > angleCooldown && vision);
	}
}
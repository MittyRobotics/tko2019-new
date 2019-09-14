package com.amhsrobotics.autonomous.movement.commands;

import com.amhsrobotics.autonomous.constants.AutoConstants;
import com.amhsrobotics.autonomous.movement.AutonDriver;
import com.amhsrobotics.autonomous.movement.AutonMotionOutput;
import com.amhsrobotics.autonomous.movement.RateLimiter;
import com.amhsrobotics.autonomous.vision.Limelight;
import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.autonomous.Odometry;
import com.amhsrobotics.autonomous.enums.DriveState;
import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.purepursuit.Waypoint;
import com.amhsrobotics.purepursuit.enums.PathType;

/**
 * Robot path following command to follow a generated path from a set of waypoints.
 */
public class Translate2dTrajectoryVision extends Command {
    Waypoint[] waypoints;
    PathType pathType;
    boolean reversed;

    double endVelocity ;
    double t;

    double maxAcceleration;
    double maxDeceleration;

    double maxVelocity;

    int motionID;

    boolean hasTarget = false;
    double distance = 0;
    double angle = 0;
    double prevAngle = 0;
    double prevDist = 0;

    double hasTargetCount = 0;
    double hasTargetCooldown = 20;
    double distanceCount = 0;
    double distanceCooldown = 20;
    double angleCount = 0;
    double angleCooldown = 20;

    double intiVisionDist = 100;
    double targetLockedDistThreshold = 20;
    double targetLockedAngleThreshold = 8;

    public Translate2dTrajectoryVision(Waypoint[] waypoints) {
        this(waypoints, PathType.BEZIER_CURVE_PATH, false);
    }
    public Translate2dTrajectoryVision(Waypoint[] waypoints, PathType pathType) {
        this(waypoints, pathType, false);
    }

    public Translate2dTrajectoryVision(Waypoint[] waypoints, boolean reversed) {
        this(waypoints, PathType.BEZIER_CURVE_PATH,0, reversed);
    }

    public Translate2dTrajectoryVision(Waypoint[] waypoints, PathType pathType, boolean reversed) {
        this(waypoints, pathType,0, reversed);
    }
    public Translate2dTrajectoryVision(Waypoint[] waypoints, PathType pathType, double endVelocity, boolean reversed) {
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

    public Translate2dTrajectoryVision(Waypoint[] waypoints, double maxAcceleration, double maxDeceleration, double maxVelocity,  PathType pathType, double endVelocity, boolean reversed) {
        super("Translate2dTrajectory");
        requires(DriveTrain.getInstance());
        this.endVelocity = endVelocity;

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
        Odometry.getInstance().resetPosition();
        Odometry.getInstance().setPos(waypoints[0].getWaypoint().getX(),waypoints[0].getWaypoint().getY(),waypoints[0].getAngle());
        AutonDriver.getInstance().setupTrajectory(waypoints, maxAcceleration, maxDeceleration, maxVelocity, pathType, endVelocity, reversed);
    }


    @Override
    public void execute() {
        updateLimelight();

        t = timeSinceInitialized();

        AutonMotionOutput output = AutonDriver.getInstance().update(t);
        double endt = timeSinceInitialized();

        //System.out.println("Time for follower calculation: " + (endt-t));

        DriveTrain.getInstance().tankVelocity(output.getLeft(), output.getRight());
    }

    public void updateLimelight(){
        Limelight.getInstance().updateLimelightValues();
        hasTarget = Limelight.getInstance().isHasTarget();
        distance = Limelight.getInstance().getTargetYFast();
        angle = Limelight.getInstance().getXAngle();
        if(Math.abs(prevDist - distance) < targetLockedDistThreshold){
            distanceCount ++;
        }
        else{
            distanceCount = 0;
        }
        if(Math.abs(prevAngle - angle) < targetLockedAngleThreshold){
            angleCount ++;
        }
        else{
            angleCount = 0;
        }
        if(hasTarget){
            hasTargetCount ++;
        }
        else{
            hasTargetCount = 0;
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
        return AutonDriver.getInstance().isFinished(motionID) || (hasTargetCount > hasTargetCooldown && distanceCount > distanceCooldown && angleCount > angleCooldown);
    }
}
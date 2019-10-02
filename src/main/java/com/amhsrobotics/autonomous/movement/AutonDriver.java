package com.amhsrobotics.autonomous.movement;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.amhsrobotics.autonomous.Odometry;
import com.amhsrobotics.autonomous.constants.AutoConstants;
import com.amhsrobotics.autonomous.enums.DriveState;
import com.amhsrobotics.autonomous.enums.LinearMovementType;
import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.drive.constants.TicksPerInch;

import com.amhsrobotics.motionprofile.MotionFrame;
import com.amhsrobotics.motionprofile.TrapezoidalMotionProfile;
import com.amhsrobotics.purepursuit.*;
import com.amhsrobotics.purepursuit.enums.PathType;
import org.opencv.core.Mat;

import java.awt.geom.Point2D;

/**
 * Master autonomous driving class that handles all autonomous driving commands and calculations (vision is a work in
 * progress and is still calculated in the command, it will eventually be moved to master class).
 * <p>
 * This class handles the transition between driving commands, keeps track of the current driving command active, and
 * manages the calculations and libraries for advanced path following and motion profiling.
 */
public class AutonDriver {
	private static AutonDriver ourInstance = new AutonDriver();

	private DriveState currentDriveState;

	private TrapezoidalMotionProfile currentMotionProfile;

	private PathFollower currentPathFollower;

	private Path currentPath;

	private double leftEncoderReset = 0;
	private double rightEncoderReset = 0;

	private int motionID = 0;

	private int finishCount = 0;

	private double stoppingThreshold = 0.5;

	private boolean trajectoryFollowingFinished = true;
	private boolean motionProfileFinished = true;
	private boolean PIDFinished = true;
	private boolean visionFinished = true;


	private Point2D.Double lastPos;


	public static AutonDriver getInstance() {
		return ourInstance;
	}

	private AutonDriver() {
	}

	public int initNewDriveMethod(DriveState driveState) {
		this.finishCount = 0;

		this.currentDriveState = driveState;
		this.motionID++;

		return motionID;
	}

	public void setupTrajectory(Waypoint[] waypoints, double lookahead, double kCurvature, double maxAcceleration, double maxDeceleration, double maxVelocity, PathType pathType, double startVelocity, double endVelcoity, boolean reversed) {
		PathFollowerPosition.getInstance().resetPos(Odometry.getInstance().getRobotX(), Odometry.getInstance().getRobotY(), Odometry.getInstance().getRobotHeading());

		System.out.println(lookahead + " " + kCurvature);

		PathGenerator.getInstance().setPathKCurvature(kCurvature);

		this.trajectoryFollowingFinished = false;
		this.motionProfileFinished = true;
		this.PIDFinished = true;
		this.visionFinished = true;

		this.currentMotionProfile = null;
		this.currentPath = PathGenerator.getInstance().generate(waypoints, pathType, maxAcceleration,maxDeceleration, maxVelocity, startVelocity,endVelcoity, 200);
		this.currentPathFollower = new PathFollower(currentPath, reversed);

		//currentPathFollower.hardSetCurvature(false, 0.001);

		currentPathFollower.setLookaheadDistance(lookahead);
		currentPathFollower.setWheelDistance(27);
	}
	public void setupMotionProfile(double setpoint, LinearMovementType movementType) {
		setupMotionProfile(setpoint,movementType,false);
	}
	public void setupMotionProfile(double setpoint, LinearMovementType movementType, boolean reversed) {
		this.trajectoryFollowingFinished = true;
		this.motionProfileFinished = false;
		this.PIDFinished = true;
		this.visionFinished = true;

		VelocityConstraints velocityConstraints;
		if (movementType == LinearMovementType.TRANSLATION) {
			velocityConstraints = AutoConstants.DRIVE_VELOCITY_CONSTRAINTS;
		} else {
			velocityConstraints = new VelocityConstraints(AutoConstants.DRIVE_VELOCITY_CONSTRAINTS.getMaxAcceleration() * AutoConstants.INCHES_PER_ANGLE,AutoConstants.DRIVE_VELOCITY_CONSTRAINTS.getMaxAcceleration() * AutoConstants.INCHES_PER_ANGLE, AutoConstants.DRIVE_VELOCITY_CONSTRAINTS.getMaxVelocity() * AutoConstants.INCHES_PER_ANGLE);
		}

		this.currentPath = null;
		this.currentPathFollower = null;
		this.currentMotionProfile = new TrapezoidalMotionProfile(velocityConstraints.getMaxAcceleration(), velocityConstraints.getMaxVelocity(), setpoint, 0.06, reversed);
	}

	public void setupPIDMovement() {
		this.trajectoryFollowingFinished = true;
		this.motionProfileFinished = true;
		this.PIDFinished = false;
		this.visionFinished = true;

		this.currentPath = null;
		this.currentPathFollower = null;
		this.currentMotionProfile = null;
	}

	public void setupVision() {
		this.trajectoryFollowingFinished = true;
		this.motionProfileFinished = true;
		this.PIDFinished = true;
		this.visionFinished = true;

		this.currentPath = null;
		this.currentPathFollower = null;
		this.currentMotionProfile = null;
	}


	public AutonMotionOutput update(double t) {
		Odometry.getInstance().run();
		PathFollowerPosition.getInstance().hardSetPos(Odometry.getInstance().getRobotX(), Odometry.getInstance().getRobotY(), Odometry.getInstance().getRobotHeading());
		AutonMotionOutput output = new AutonMotionOutput(0, 0, 0);

		switch (currentDriveState) {
			case IDLE:
			case PID_ROTATE:
				output = updatePIDRotate();
				break;
			case PID_TANSLATE:
				output = updatePIDTranslate();
				break;
			case PURE_PURSUIT:
				output = updatePurePursuit();
				break;
			case MOTION_ROTATE:
				output = updateMotionRotate(t);
				break;
			case MOTION_TRANSLATE:
				output = updateMotionTranslate(t);
				break;
			case VISION:
				output = new AutonMotionOutput(0, 0, 0);
		}

		return output;
	}

	private AutonMotionOutput updatePIDTranslate() {
		if (Math.abs(DriveTrain.getInstance().getTranslationError()) < stoppingThreshold) {
			finishCount++;
		} else {
			finishCount = 0;
		}
		motionProfileFinished = finishCount > 10;

		return new AutonMotionOutput(0, 0, 0);
	}

	private AutonMotionOutput updatePIDRotate() {
		if (Math.abs(DriveTrain.getInstance().getRotationError()) < stoppingThreshold) {
			finishCount++;
		} else {
			finishCount = 0;
		}
		motionProfileFinished = finishCount > 10;

		return new AutonMotionOutput(0, 0, 0);
	}

	private AutonMotionOutput updatePurePursuit() {
		PathFollowerOutput output = currentPathFollower.update();

		trajectoryFollowingFinished = currentPathFollower.isFinished();

		SmartDashboard.putNumber("PP_FF_LeftVelocity", output.getLeftVelocity());
		SmartDashboard.putNumber("PP_FF_RightVelocity", output.getRightVelocity());
		SmartDashboard.putNumber("FB_LeftVelocity", DriveTrain.getInstance().getLeftVelocity());
		SmartDashboard.putNumber("FB_RightVelocity", DriveTrain.getInstance().getRightVelocity());
		System.out.println(currentPathFollower.getCurrentLookaheadPoint().getX() + " " + currentPathFollower.getCurrentLookaheadPoint().getY() + " " + currentPathFollower.getCurvature() + " " + Odometry.getInstance().getRobotX() + " " + Odometry.getInstance().getRobotY() + "");
		SmartDashboard.putNumber("PP_POS_X", PathFollowerPosition.getInstance().getRobotX());
		SmartDashboard.putNumber("PP_POS_Y", PathFollowerPosition.getInstance().getRobotY());
		SmartDashboard.putNumber("PP_FF_LookAheadX", currentPathFollower.getCurrentLookaheadPoint().getX());
		SmartDashboard.putNumber("PP_FF_LookAheadY", currentPathFollower.getCurrentLookaheadPoint().getY());
		SmartDashboard.putNumber("PP_FF_Curvature", currentPathFollower.getCurvature());
		if(Math.abs(currentPathFollower.getCurvature()) > 0.005){
			//System.out.println("Curvature high:  " + currentPathFollower.getCurvature() + " " + output.getLeftVelocity() + " " + output.getRightVelocity());
		}
		//Left and right are swapped for now, I will figure it out later
		return new AutonMotionOutput(output.getRightVelocity(), output.getLeftVelocity(), 0);
	}

	private AutonMotionOutput updateMotionTranslate(double t) {
		MotionFrame frame = currentMotionProfile.getFrameAtTime(t);

		double position = frame.getPosition();
		double velocity = frame.getVelocity();
		double acceleration = frame.getAcceleration();

		double leftEncoder = DriveTrain.getInstance().getLeftEncoder() - leftEncoderReset;
		double rightEncoder = DriveTrain.getInstance().getRightEncoder() - rightEncoderReset;
		double leftPosition = leftEncoder / TicksPerInch.DRIVE_HIGH;
		double rightPosition = rightEncoder / TicksPerInch.DRIVE_HIGH;
		if (position == 0) {
			leftPosition = 0;
			rightPosition = 0;
		}


		SmartDashboard.putNumber("MT_FF_Position", position);
		SmartDashboard.putNumber("MT_FF_Velocity", velocity);
		SmartDashboard.putNumber("MT_FF_Acceleration", acceleration);

		if (Math.abs(DriveTrain.getInstance().getTranslationError()) < stoppingThreshold) {
			finishCount++;
		} else {
			finishCount = 0;
		}
		motionProfileFinished = finishCount > 10 && currentMotionProfile.isFinished();

		return new AutonMotionOutput(position - leftPosition, position - rightPosition, 0);
	}

	private AutonMotionOutput updateMotionRotate(double t) {
		MotionFrame frame = currentMotionProfile.getFrameAtTime(t);

		double position = frame.getPosition();
		double velocity = frame.getVelocity();
		double acceleration = frame.getAcceleration();

		SmartDashboard.putNumber("MR_FF_Position", position);
		SmartDashboard.putNumber("MR_FF_Velocity", velocity);
		SmartDashboard.putNumber("MR_FF_Acceleration", acceleration);

		if (Math.abs(DriveTrain.getInstance().getRotationError()) < stoppingThreshold) {
			finishCount++;
		} else {
			finishCount = 0;
		}
		motionProfileFinished = finishCount > 10 && currentMotionProfile.isFinished();

		return new AutonMotionOutput(0, 0, position);
	}

	public boolean isFinished(int id) {
		boolean finished = true;
		switch (currentDriveState) {
			case IDLE:
				break;
			case PID_ROTATE:
			case PID_TANSLATE:
				finished = currentPathFollower != null || currentPath != null || currentMotionProfile != null || PIDFinished;
				break;
			case PURE_PURSUIT:
				finished = currentPathFollower == null || currentPath == null || currentMotionProfile != null || trajectoryFollowingFinished;
				break;
			case MOTION_ROTATE:
			case MOTION_TRANSLATE:
				finished = currentPathFollower != null || currentPath != null || currentMotionProfile == null || motionProfileFinished;
				break;
			case VISION:
				finished = visionFinished;
				break;
		}

		if (finished && motionID == id) {
			reset();
			currentDriveState = DriveState.IDLE;
		}

		return finished;
	}

	private void reset() {
		this.trajectoryFollowingFinished = true;
		this.motionProfileFinished = true;
		this.PIDFinished = true;
		this.visionFinished = true;

		this.currentPath = null;
		this.currentPathFollower = null;
		this.currentMotionProfile = null;
	}

	public void visionEnd() {
		visionFinished = true;
	}

	public DriveState getCurrentDriveState() {
		return currentDriveState;
	}


	public Point2D.Double getLastPos() {
		return lastPos;
	}

	public void setLastPos(Point2D.Double lastPos) {
		this.lastPos = lastPos;
	}

}

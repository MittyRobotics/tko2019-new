package frc.robot.autonomous.movement;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.autonomous.Odometry;
import frc.robot.autonomous.constants.AutoConstants;
import frc.robot.autonomous.enums.DriveState;
import frc.robot.autonomous.enums.LinearMovementType;
import frc.robot.drive.DriveTrain;
import frc.robot.drive.constants.TicksPerInch;
import utils.motionprofile.MotionFrame;
import utils.motionprofile.TrapezoidalMotionProfile;
import utils.purepursuit.*;

import utils.purepursuit.enums.PathType;

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

	public void setupTrajectory(Waypoint[] waypoints, PathType pathType, boolean reversed) {
		PathFollowerPosition.getInstance().resetPos(Odometry.getInstance().getRobotX(), Odometry.getInstance().getRobotY(), Odometry.getInstance().getRobotHeading());

		this.trajectoryFollowingFinished = false;
		this.motionProfileFinished = true;
		this.PIDFinished = true;
		this.visionFinished = true;

		this.currentMotionProfile = null;
		this.currentPath = PathGenerator.getInstance().generate(waypoints, pathType, AutoConstants.DRIVE_VELOCITY_CONSTRAINTS, 200);
		this.currentPathFollower = new PathFollower(currentPath);
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
			velocityConstraints = AutoConstants.TURN_VELOCITY_CONSTRAINTS;
		}

		this.currentPath = null;
		this.currentPathFollower = null;
		this.currentMotionProfile = new TrapezoidalMotionProfile(velocityConstraints.getMaxAcceleration(), velocityConstraints.getMaxVelocity(), setpoint, 0.02, reversed);
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
		this.visionFinished = false;

		this.currentPath = null;
		this.currentPathFollower = null;
		this.currentMotionProfile = null;
	}


	public AutonMotionOutput update(double t) {
		PathFollowerPosition.getInstance().updatePos(Odometry.getInstance().getRobotX(), Odometry.getInstance().getRobotY(), Odometry.getInstance().getRobotHeading());

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
				output = updateMotionTranslate(t);
				break;
			case MOTION_TRANSLATE:
				output = updateMotionRotate(t);
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
		TrajectoryFollowerOutput output = currentPathFollower.update();

		trajectoryFollowingFinished = currentPathFollower.isFinished();

		SmartDashboard.putNumber("PP_FF_LeftVelocity", output.getLeftVelocity());
		SmartDashboard.putNumber("PP_FF_RightVelocity", output.getRightVelocity());

		return new AutonMotionOutput(output.getLeftVelocity(), output.getRightVelocity(), 0);
	}

	private AutonMotionOutput updateMotionTranslate(double t) {
		MotionFrame frame = currentMotionProfile.getFrameAtTime(t);

		double position = frame.getPosition();
		double velocity = frame.getVelocity();
		double acceleration = frame.getAcceleration();

		double leftEncoder = DriveTrain.getInstance().getLeftEncoder() - leftEncoderReset;
		double rightEncoder = DriveTrain.getInstance().getRightEncoder() - rightEncoderReset;
		double leftPosition = leftEncoder / TicksPerInch.DRIVE;
		double rightPosition = rightEncoder / TicksPerInch.DRIVE;
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
				finished = motionID != id || currentPathFollower != null || currentPath != null || currentMotionProfile != null || PIDFinished;
				break;
			case PURE_PURSUIT:
				finished = motionID != id || currentPathFollower == null || currentPath == null || currentMotionProfile != null || trajectoryFollowingFinished;
				break;
			case MOTION_ROTATE:
			case MOTION_TRANSLATE:
				finished = motionID != id || currentPathFollower != null || currentPath != null || currentMotionProfile == null || motionProfileFinished;
				break;
			case VISION:
				finished = motionID != id || visionFinished;
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

}

package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.autonomous.enums.DriveState;
import frc.robot.autonomous.movement.AutonDriver;
import frc.robot.autonomous.movement.AutonMotionOutput;
import frc.robot.drive.DriveTrain;
import pure_pursuit.Waypoint;
import pure_pursuit.enums.PathType;

/**
 * Robot path following command to follow a generated path from a set of waypoints.
 */
public class Translate2dTrajectory extends Command {
	Waypoint[] waypoints;
	PathType pathType;
	boolean reversed;

	double t;

	int motionID;

	public Translate2dTrajectory(Waypoint[] waypoints) {
		this(waypoints, PathType.BEZIER_CURVE_PATH, false);
	}
	public Translate2dTrajectory(Waypoint[] waypoints, PathType pathType) {
		this(waypoints, pathType, false);
	}

	public Translate2dTrajectory(Waypoint[] waypoints, boolean reversed) {
		this(waypoints, PathType.BEZIER_CURVE_PATH, reversed);
	}

	public Translate2dTrajectory(Waypoint[] waypoints, PathType pathType, boolean reversed) {
		super("Translate2dTrajectory");
		requires(DriveTrain.getInstance());

		this.waypoints = waypoints;
		this.pathType = pathType;
		this.reversed = reversed;

		this.motionID = AutonDriver.getInstance().initNewDriveMethod(DriveState.PURE_PURSUIT);
	}


	@Override
	public void initialize() {
		AutonDriver.getInstance().setupTrajectory(waypoints, pathType, reversed);
	}


	@Override
	public void execute() {
		t = timeSinceInitialized();
		AutonMotionOutput output = AutonDriver.getInstance().update(t);
		double endt = timeSinceInitialized();

		System.out.println("Time for follower calculation: " + (endt-t));

		DriveTrain.getInstance().tankVelocity(output.getLeft(), output.getRight());
	}


	@Override
	public void end() {
		System.out.println("Ending Translate2dTrajectory Command!");
		DriveTrain.getInstance().tankDrive(0, 0);
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
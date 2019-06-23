package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.autonomous.RobotPose;
import frc.robot.autonomous.pure_pursuit.*;
import frc.robot.autonomous.pure_pursuit.enums.PathType;
import frc.robot.drive.DriveTrain;

/**
 * Command to follow a generated path using a custom pure pursuit controller.
 *
 * I know this code is really messy and I will clean it up and make a final version later, but right now I am just testing it.
 */
public class Tradjectory2dMovement extends Command {

	private int count = 0;
	Path path;
	Waypoint[] waypoints;
	PathFollower follower;
	double t;

	/**
	 * Initializes command with a name "Translation" and the required subsystem class that will be used, {@link DriveTrain}
	 */
	public Tradjectory2dMovement(Waypoint[] waypoints) {
		super("Translation");
		requires(DriveTrain.getInstance());
		this.waypoints = waypoints;
	}

	/**
	 * The initialize function is called at the initialization stage of the command.
	 */
	@Override
	public void initialize() {
		RobotPose.getInstance().resetPosition();
		PathGenerator generator = new PathGenerator(PathType.BEZIER_CURVE_PATH, new VelocityConstraints(2,5));
		path = generator.generate(waypoints,100);
		follower = new PathFollower(path);
	}

	/**
	 * This method is called periodically (about every 20ms) and does the work of the command.
	 */
	@Override
	public void execute() {
		RobotPose.getInstance().update();
		t = timeSinceInitialized();
		double[] output = follower.update();
		double leftVelocity = output[0];
		double rightVelocity = output[1];
	}

	/**
	 * This method is called at the end of the command.
	 * <p>
	 * Here we want to reset the wheel speeds to 0.
	 */
	@Override
	public void end() {
		DriveTrain.getInstance().tankDrive(0, 0);
	}

	/**
	 * This method is called when the command is interrupted, therefore ending the command.
	 */
	@Override
	public void interrupted() {
		end();
	}

	/**
	 * This returns whether or not the command has finished
	 *
	 * @return
	 */
	@Override
	protected boolean isFinished() {
		return count > 10;
	}
}
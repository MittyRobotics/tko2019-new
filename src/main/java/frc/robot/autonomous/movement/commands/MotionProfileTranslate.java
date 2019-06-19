package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.drive.DriveTrain;
import frc.robot.drive.constants.TicksPerInch;
import frc.robot.motion_profile.TrapazoidalMotionProfile;

/**
 * Linear translation command for translating the robot either forward or backward on the local forward axis. Since the
 * robot is operating on a differential drive, translation can only occur on the forward axis.
 */
public class MotionProfileTranslate extends Command {

	private int count = 0;
	private double distance; //inches
	private double maxSpeed; //percent output
	private double stoppingThreshold = 0.5; //Translation error threshold, the robot will stop translating when within this threshold of inches
	double t = 0;
	private TrapazoidalMotionProfile motionProfile;

	/**
	 * Initializes command with a name "Translation" and the required subsystem class that will be used, {@link DriveTrain}
	 *
	 * @param distance         the distance that the robot will be moving (inches)
	 * @param maxAcceleration the robot's maximum acceleration speed (ft/s)
	 * @param maxVelocity     the robot's maximum velocity (ft/s)
	 * @param maxOutput       the maximum percent output of the PID loop (0-1)
	 * @param steps            the amount of steps of the motion profile
	 */
	public MotionProfileTranslate(double distance, double maxAcceleration, double maxVelocity, double maxOutput, int steps) {
		super("Translation");
		requires(DriveTrain.getInstance());
		this.distance = distance;
		this.maxSpeed = maxOutput;
		motionProfile = new TrapazoidalMotionProfile(maxAcceleration, maxVelocity, distance, steps);
	}

	/**
	 * The initialize function is called at the initialization stage of the command. This is where the
	 * translation PID control loop values are set. The translation method in the {@link DriveTrain} subsystem is called with the
	 * parameters of distance and max speed to translate the robot the desired amount.
	 */
	@Override
	public void initialize() {
		DriveTrain.getInstance().resetEncoders();
	}

	/**
	 * This method is called periodically (about every 20ms) and does the work of the command.
	 * <p>
	 * A counter is counting up while the robot is within the stopping threshold. This is used to
	 * end the command once the robot is within the stopping threshold for enough times to come to rest.
	 */
	@Override
	public void execute() {

		t = timeSinceInitialized();

		double position = motionProfile.getFrameAtTime(t).getPosition();
		double velocity = motionProfile.getFrameAtTime(t).getVelocity();
		double acceleration = motionProfile.getFrameAtTime(t).getAcceleration();

		double currentPosition = ((DriveTrain.getInstance().getLeftEncoder() + DriveTrain.getInstance().getRightEncoder()) / 2) / TicksPerInch.DRIVE;
		if (position == 0) {
			currentPosition = 0;
		}
		DriveTrain.getInstance().translation(position - currentPosition, maxSpeed);
		//System.out.println(DriveTrain.getInstance().getLeftEncoder() + "  " + DriveTrain.getInstance().getRightEncoder());
		//System.out.println("Feedforward: position: " + position + "in velocity: " + velocity + "in/s acceleration: " + acceleration + "in/s time: " +  t + "s current time: " + i + "s");

		if (Math.abs(DriveTrain.getInstance().getTranslationError() / TicksPerInch.DRIVE) < stoppingThreshold) {
			count++;
		} else {
			count = 0;
		}
		SmartDashboard.putNumber("feedfowardPosition", position);
		SmartDashboard.putNumber("actualPosition",currentPosition);
		SmartDashboard.putNumber("velocity", velocity);
		SmartDashboard.putNumber("acceleration", acceleration);
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
	 * <p>
	 * In this case, we want to return if the robot has translated to its destination.
	 *
	 * @return if the robot has translated to its destination
	 */
	@Override
	protected boolean isFinished() {
		return count > 10 && motionProfile.isDone();
	}
}
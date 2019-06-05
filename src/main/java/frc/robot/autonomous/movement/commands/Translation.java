package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drive.DriveTrain;
import frc.robot.drive.constants.TicksPerInch;

/**
 * Linear translation command for translating the robot either forward or backward on the local forward axis. Since the
 * robot is operating on a differential drive, translation can only occur on the forward axis.
 */
public class Translation extends Command {
	
	private int count = 0;
	private double distance; //inches
	private double maxSpeed; //percent output
	private double stoppingThreshold = 0.5; //Translation error threshold, the robot will stop translating when within this threshold of inches
	
	/**
	 * Initializes command with a name "Translation" and the subsystem class that will be used, {@link DriveTrain}
	 *
	 * @param distance the distance that the robot will be moving
	 * @param maxSpeed the maximum speed at which the robot will be moving at
	 */
	public Translation(double distance, double maxSpeed) {
		super("Translation");
		requires(DriveTrain.getInstance());
		this.distance = distance;
		this.maxSpeed = maxSpeed;
	}
	
	/**
	 * The initialize function is called at the initialization stage of the command. This is where the
	 * translation PID control loop values are set. The translation method in the {@link DriveTrain} subsystem is called with the
	 * parameters of distance and max speed to translate the robot the desired amount.
	 */
	@Override
	public void initialize() {
		DriveTrain.getInstance().translation(distance, maxSpeed);
	}
	
	/**
	 * This method is called periodically (about every 20ms) and does the work of the command.
	 * <p>
	 * A counter is counting up while the robot is within the stopping threshold. This is used to
	 * end the command once the robot is within the stopping threshold for enough times to come to rest.
	 */
	@Override
	public void execute() {
		if (Math.abs(DriveTrain.getInstance().getTranslationError() / TicksPerInch.DRIVE) < stoppingThreshold) {
			count++;
		} else {
			count = 0;
		}
	}
	
	/**
	 * This method is called at the end of the command.
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
	 * This returns whether or not the command has finished, meaning the robot has translated to its destination.
	 *
	 * @return if the robot has translated to its destination
	 */
	@Override
	protected boolean isFinished() {
		return count > 10;
	}
}
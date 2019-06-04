package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drive.DriveTrain;

/**
 * Rotation command for rotating the robot's chassis a set amount of degrees using a PID control loop.
 * <p>
 * This command takes in an angle in degrees, a maximum speed in percent output (0 to 1), and a direction value to rotate the robot.
 */
public class Rotation extends Command {
	
	double angle; //degrees
	double maxSpeed; //percent output
	RotationDirection direction; //direction of rotation
	
	/**
	 * Initializes command
	 *
	 * @param angle     the angle at which the robot will be rotating
	 * @param maxSpeed  the maximum speed at which the robot will be rotating at
	 * @param direction the direction in which the robot will be rotating
	 */
	public Rotation(double angle, double maxSpeed, RotationDirection direction) {
		super("Rotation");
		requires(DriveTrain.getInstance());
		this.angle = angle;
		this.maxSpeed = maxSpeed;
		this.direction = direction;
	}
	
	/**
	 * The initialize function is called at the initialization stage of the command. This is where the
	 * drive PID control loop values are set.
	 */
	@Override
	public void initialize() {
		//"Everybody needs a friend" -Bob Ross
	}
	
	/**
	 * This method is called periodically (about every 20ms) and does the work of the command.
	 */
	@Override
	public void execute() {
		//"Everybody needs a friend" -Bob Ross
	}
	
	/**
	 * This method is called at the end of the command.
	 */
	@Override
	public void end() {
		//"Everybody needs a friend" -Bob Ross
	}
	
	
	/**
	 * This method is called when the command is interrupted
	 */
	@Override
	public void interrupted() {
		end();
	}
	
	/**
	 * This returns whether or not the command is finished.
	 * @return if the command is finished
	 */
	@Override
	protected boolean isFinished() {
		return true;
	}
}
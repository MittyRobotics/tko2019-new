package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drive.DriveTrain;
import frc.robot.drive.constants.TicksPerInch;

/**
 * Rotation command for rotating the robot's chassis a set amount of degrees using a PID control loop.
 * <p>
 * This command takes in an angle in degrees, a maximum speed in percent output (0 to 1), and a direction value to rotate the robot.
 */
public class Rotation extends Command {
	
	double angle; //degrees
	double maxSpeed; //percent output
	RotationDirection direction; //direction of rotation
	double stoppingThreshold = 1; //Rotation error threshold, the robot will stop rotating when within this threshold of degrees
	
	/**
	 * Initializes command with a name "Rotation" and the subsystem class that will be used, {@link DriveTrain}
	 *
	 * @param angle     the angle at which the robot will be rotating
	 * @param maxSpeed  the maximum speed at which the robot will be rotating at
	 * @param direction the direction in which the robot will be rotating
	 */
	public Rotation(double angle, double maxSpeed, RotationDirection direction) {
		
		super("Rotation");
		requires(DriveTrain.getInstance());
		
		if(direction == RotationDirection.Left){
			angle = -angle;
		}
		this.angle = angle;
		this.maxSpeed = maxSpeed;
	}
	
	/**
	 * The initialize function is called at the initialization stage of the command.
	 *
	 * This is where the rotation PID control loop values are set. The rotation method in the {@link DriveTrain}
	 * subsystem is called with the parameters of angle and max speed to rotate the robot the desired amount.
	 */
	@Override
	public void initialize() {
		DriveTrain.getInstance().rotation(angle,maxSpeed);
	}
	
	/**
	 * This method is called periodically (about every 20ms) and does the work of the command.
	 *
	 * All the command's work is to set the setpoint of the PID loop, so nothing is needed here.
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
	 * This method is called when the command is interrupted, therefore ending the command.
	 */
	@Override
	public void interrupted() {
		end();
	}
	
	/**
	 * This returns whether or not the command has finished, meaning the robot has rotated to its target angle.
	 *
	 * @return if the robot has rotated to its target angle
	 */
	@Override
	protected boolean isFinished() {
		if(Math.abs(DriveTrain.getInstance().getRotationError() / TicksPerInch.DRIVE) < stoppingThreshold ){
			return true;
		}
		else{
			return false;
		}
	}
}
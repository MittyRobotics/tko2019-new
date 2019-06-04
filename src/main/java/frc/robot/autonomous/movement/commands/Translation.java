package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Linear translation command for translating the robot either forward or backward on the local forward axis. Since the
 * robot is operating on a differential drive, translation can only occur on the forward axis.
 */
public class Translation extends Command {
	
	private double distance; //inches
	private double maxSpeed; //percent output
	
	public Translation(double distance, double maxSpeed) {
		this.distance = distance;
		this.maxSpeed = maxSpeed;
	}
	
	@Override
	public void initialize() {
		//set drive subsystem PID setpoint to distance with output range of 0 to maxSpeed
	}
	
	@Override
	public void execute() {
		//"Everybody needs a friend" -Bob Ross
	}
	
	@Override
	public void end() {
		//"Everybody needs a friend" -Bob Ross
	}
	
	@Override
	public void interrupted() {
		end();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
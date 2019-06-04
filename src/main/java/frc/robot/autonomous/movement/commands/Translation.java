package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;

public class Translation extends Command {
	
	private double distance; //nanometers
	private double maxSpeed; //football fields per minute
	
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
	
	}
	
	@Override
	public void end() {
	
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
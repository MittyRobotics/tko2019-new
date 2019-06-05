package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.autonomous.constants.VisionConstants;
import frc.robot.autonomous.vision.Limelight;
import frc.robot.drive.DriveTrain;

public class VisionAlignment extends Command {
	
	double maxSpeed; //percent output
	int count = 0;
	
	public VisionAlignment(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	@Override
	public void initialize() {
	
	}
	
	@Override
	public void execute() {
		double x = Limelight.getInstance().getXAngle();
		double threshold = 1;
		if(x > threshold){
			DriveTrain.getInstance().tankDrive(((Math.abs(x) / 29.8) * (maxSpeed-0.2)) + 0.2,-(((Math.abs(x) / 29.8) * (maxSpeed-0.2)) + 0.2));
			count = 0;
		}
		else if(x < -threshold){
			DriveTrain.getInstance().tankDrive(-((Math.abs(x) / 29.8) * (maxSpeed-0.2)) + 0.2,(((Math.abs(x) / 29.8) * (maxSpeed-0.2)) + 0.2));
			count = 0;
		}
		else{
			DriveTrain.getInstance().tankDrive(0,0);
			count ++;
		}
	}
	
	@Override
	public void end() {
		DriveTrain.getInstance().tankDrive(0,0);
	}
	
	@Override
	public void interrupted() {
		end();
	}
	
	@Override
	protected boolean isFinished() {
		return count > 20;
	}
}
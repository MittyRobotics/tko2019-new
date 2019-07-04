package frc.robot.drive.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drive.DriveTrain;
import frc.robot.oi.OI;

public class WheelDrive extends Command {
	
	public WheelDrive() {
		super("Wheel Drive");
		requires(DriveTrain.getInstance());
	}
	
	@Override
	protected void initialize() {
	
	}
	
	@Override
	protected void execute() {
		DriveTrain.getInstance().wheelDrive(OI.getInstance().getJoystick1().getY(GenericHID.Hand.kRight), OI.getInstance().getSteeringWheel().getX(GenericHID.Hand.kLeft), OI.getInstance().getSteeringWheel().getAButton());
	}
	
	@Override
	protected void end() {
		DriveTrain.getInstance().tankDrive(0, 0);
	}
	
	@Override
	protected void interrupted() {
		end();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}

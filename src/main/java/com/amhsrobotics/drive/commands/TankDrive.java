package com.amhsrobotics.drive.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.oi.OI;

public class TankDrive extends Command {
	public TankDrive() {
		super("Tank Drive");
		requires(DriveTrain.getInstance());
	}
	
	@Override
	protected void initialize() {

	}
	
	@Override
	protected void execute() {
		if(OI.getInstance().getJoystick2().getRawButton(8)){
			DriveTrain.getInstance().tankDrive(-OI.getInstance().getXboxController().getY(GenericHID.Hand.kLeft), -OI.getInstance().getXboxController().getY(GenericHID.Hand.kRight), 1);
		} else {
			DriveTrain.getInstance().tankDrive(-OI.getInstance().getXboxController().getY(GenericHID.Hand.kLeft), -OI.getInstance().getXboxController().getY(GenericHID.Hand.kRight), .65);
		}
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

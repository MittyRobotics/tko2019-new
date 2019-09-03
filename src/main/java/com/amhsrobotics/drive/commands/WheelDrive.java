package com.amhsrobotics.drive.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.oi.OI;

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

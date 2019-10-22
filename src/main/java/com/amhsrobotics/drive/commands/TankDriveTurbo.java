package com.amhsrobotics.drive.commands;

import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.oi.OI;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

public class TankDriveTurbo extends Command {

	public TankDriveTurbo() {
		super("Tank Drive Turbo");
		requires(DriveTrain.getInstance());
	}


	@Override
	protected void execute() {
		double left = OI.getInstance().getXboxController().getY(GenericHID.Hand.kLeft);
		double right = OI.getInstance().getXboxController().getY(GenericHID.Hand.kRight);
		double trigger = OI.getInstance().getXboxController().getTriggerAxis(GenericHID.Hand.kRight);
		DriveTrain.getInstance().tankDrive(left, right, 0.75 + trigger/4);
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
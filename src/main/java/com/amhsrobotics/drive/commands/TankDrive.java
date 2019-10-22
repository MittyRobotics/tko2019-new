package com.amhsrobotics.drive.commands;

import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.oi.OI;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

public class TankDrive extends Command {

	public TankDrive() {
		super("Tank Drive");
		requires(DriveTrain.getInstance());
	}


	@Override
	protected void execute() {
		double left = OI.getInstance().getXboxController().getY(GenericHID.Hand.kLeft);
		double right = OI.getInstance().getXboxController().getY(GenericHID.Hand.kRight);
		DriveTrain.getInstance().tankDrive(left, right);
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
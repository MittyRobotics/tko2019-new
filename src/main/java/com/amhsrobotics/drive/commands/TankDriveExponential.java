package com.amhsrobotics.drive.commands;

import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.oi.OI;
import edu.wpi.first.wpilibj.command.Command;

public class TankDriveExponential extends Command {

	public TankDriveExponential() {
		super("Tank Drive Exponential");
		requires(DriveTrain.getInstance());
	}


	@Override
	protected void execute() {
		double left = OI.getInstance().getJoystick1().getY();
		double right = OI.getInstance().getJoystick2().getY();
		left = Math.pow(left, 2) * Math.signum(left);
		right = Math.pow(right, 2) * Math.signum(right);
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
package com.amhsrobotics.drive.commands;

import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.oi.OI;
import edu.wpi.first.wpilibj.command.Command;

public class TankDriveJoystick extends Command {

	public TankDriveJoystick() {
		super("Tank Drive Joystick");
		requires(DriveTrain.getInstance());
	}

	@Override
	protected void execute() {
		double left = OI.getInstance().getJoystick1().getY();
		double right = OI.getInstance().getJoystick2().getY();
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
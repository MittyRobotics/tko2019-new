package com.amhsrobotics.drive.commands;

import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.oi.OI;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

public class TriggerDrive extends Command {

	public TriggerDrive() {
		super("Trigger Drive");
		requires(DriveTrain.getInstance());
	}


	@Override
	protected void execute() {
		double driveF = OI.getInstance().getXboxController()
				.getTriggerAxis(GenericHID.Hand.kLeft);
		double driveB = OI.getInstance().getXboxController()
				.getTriggerAxis(GenericHID.Hand.kRight);
		double turn = OI.getInstance().getXboxController().getX(GenericHID.Hand.kLeft);
		boolean brake = OI.getInstance().getXboxController().getAButton();
		double drive = (driveF - driveB) * (1-turn/2);
		if(brake) {
			DriveTrain.getInstance().tankDrive(0, 0);
		} else if(Math.abs(turn) < 0.1) {
			drive = driveF - driveB;
			DriveTrain.getInstance().tankDrive(drive, drive);
		} else if(Math.abs(driveF) < 0.1 && Math.abs(driveB) < 0.1) {
			DriveTrain.getInstance().tankDrive(turn/2, -turn/2);
		} else {
			DriveTrain.getInstance().tankDrive(drive + turn/2, drive - turn/2);
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
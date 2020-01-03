package com.amhsrobotics.drive.commands;

import com.amhsrobotics.drive.Shifter;
import com.amhsrobotics.drive.constants.GearState;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.oi.OI;

/**
 * Tank Drive Class used to drive the robot during teleop
 */
public class TankDrive extends Command {
	/**
	 * Constructor for Tank Drive Command that runs
	 * Sets the command to the DriveTrain subsystem
	 */
	public TankDrive() {
		super("Tank Drive");
		requires(DriveTrain.getInstance());
	}

	/**
	 * Sets the robot to move based on joystick and button values using tank drive
	 */
	@Override
	protected void execute() {
		if(OI.getInstance().getJoystick2().getRawButton(8)){
			DriveTrain.getInstance().tankDrive(-OI.getInstance().getXboxController().getY(GenericHID.Hand.kLeft), -OI.getInstance().getXboxController().getY(GenericHID.Hand.kRight), 1);
			Shifter.getInstance().shiftGear(GearState.High);
		} else if(OI.getInstance().getJoystick2().getRawButton(10)) {
			DriveTrain.getInstance().tankDrive(-OI.getInstance().getXboxController().getY(GenericHID.Hand.kLeft), -OI.getInstance().getXboxController().getY(GenericHID.Hand.kRight), 1);
			Shifter.getInstance().shiftGear(GearState.Low);
		} else {
			DriveTrain.getInstance().tankDrive(-OI.getInstance().getXboxController().getY(GenericHID.Hand.kLeft), -OI.getInstance().getXboxController().getY(GenericHID.Hand.kRight), .7);
			Shifter.getInstance().shiftGear(GearState.High);
		}
	}

	/**
	 * Sets the robot to stop moving when the command ends
	 */
	@Override
	protected void end() {
		DriveTrain.getInstance().tankDrive(0, 0);
	}

	/**
	 * Sets the robot to stop moving when the command is interrupted
	 */
	@Override
	protected void interrupted() {
		end();
	}

	/**
	 * always returns false, but command can still be interrupted
	 * @return false
	 */
	@Override
	protected boolean isFinished() {
		return false;
	}
}
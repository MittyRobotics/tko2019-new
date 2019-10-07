package com.amhsrobotics.autonomous.movement.commands;

import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.drive.constants.TicksPerInch;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Linear translation command for translating the robot either forward or backward on the local forward axis using a PID control loop.
 * <p>
 * Since the robot is operating on a differential drive, translation can only occur on the forward axis.
 */
public class PIDTranslation extends Command {
	private double setpointLeft, setpointRight;
	private int count;
	public PIDTranslation(double setpoint) {
		setpointLeft = setpoint;
		setpointRight = setpoint;
		requires(DriveTrain.getInstance());
	}

	@Override
	public void initialize() {
		setpointLeft += DriveTrain.getInstance().getLeftEncoder();
		setpointRight += DriveTrain.getInstance().getRightEncoder();
	}


	@Override
	public void execute() {
		double currentLeft = DriveTrain.getInstance().getLeftEncoder() / TicksPerInch.DRIVE_HIGH;
		double currentRight = DriveTrain.getInstance().getRightEncoder() / TicksPerInch.DRIVE_HIGH;
		double leftMove, rightMove;
		final double RAMP_RATE = 5, THRESHOLD = 1;
		if(setpointLeft - currentLeft > RAMP_RATE){
			leftMove = currentLeft + RAMP_RATE;
		} else if (setpointLeft - currentLeft < -RAMP_RATE){
			leftMove = currentLeft - RAMP_RATE;
		} else {
			leftMove = setpointLeft;
		}
		if(setpointRight - currentRight > RAMP_RATE){
			rightMove = currentRight + RAMP_RATE;
		} else if(setpointRight - currentRight < -RAMP_RATE){
			rightMove = currentRight - RAMP_RATE;
		} else {
			rightMove = setpointRight;
		}
		DriveTrain.getInstance().translation(leftMove, rightMove);
		if(Math.abs(setpointLeft - currentLeft) < THRESHOLD && Math.abs(setpointRight - currentRight) < THRESHOLD){
			count++;
		} else {
			count = 0;
		}
	}


	@Override
	public void end() {
		DriveTrain.getInstance().tankDrive(0, 0);
	}

	@Override
	public void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return count > 5;
	}
}
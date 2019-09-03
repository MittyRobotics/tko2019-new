package com.amhsrobotics.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.autonomous.enums.DriveState;
import com.amhsrobotics.autonomous.movement.AutonDriver;
import com.amhsrobotics.drive.DriveTrain;

/**
 * Linear translation command for translating the robot either forward or backward on the local forward axis using a PID control loop.
 * <p>
 * Since the robot is operating on a differential drive, translation can only occur on the forward axis.
 */
public class PIDTranslation extends Command {
	double setpoint;
	double maxOutput;
	double t;

	int motionID;

	public PIDTranslation(double setpoint) {
		this(setpoint,0.5);
	}

	public PIDTranslation(double setpoint, double maxOutput) {
		super("PIDTranslation");
		requires(DriveTrain.getInstance());

		this.setpoint = setpoint;
		this.maxOutput = maxOutput;

		this.motionID = AutonDriver.getInstance().initNewDriveMethod(DriveState.PID_ROTATE);
	}

	@Override
	public void initialize() {
		AutonDriver.getInstance().setupPIDMovement();
		DriveTrain.getInstance().translation(setpoint, maxOutput);
	}


	@Override
	public void execute() {
		t = timeSinceInitialized();
		AutonDriver.getInstance().update(t);
	}


	@Override
	public void end() {
		System.out.println("Ending PIDTranslation Command!");
		DriveTrain.getInstance().tankDrive(0, 0);
	}

	@Override
	public void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return AutonDriver.getInstance().isFinished(motionID);
	}
}
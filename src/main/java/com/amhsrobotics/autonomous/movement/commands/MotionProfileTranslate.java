package com.amhsrobotics.autonomous.movement.commands;

import com.amhsrobotics.autonomous.movement.AutonDriver;
import com.amhsrobotics.autonomous.movement.AutonMotionOutput;
import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.autonomous.enums.DriveState;
import com.amhsrobotics.autonomous.enums.LinearMovementType;
import com.amhsrobotics.drive.DriveTrain;

/**
 * Linear translation command for translating the robot either forward or backward on the local forward axis following a Trapezoidal Motion Profile over time.
 * <p>
 * Since the robot is operating on a differential drive, translation can only occur on the forward axis.
 */
public class MotionProfileTranslate extends Command {
	double setpoint;
	double maxOutput;
	double t;

	int motionID;

	public MotionProfileTranslate(double setpoint) {
		this(setpoint, 0.5);
	}

	public MotionProfileTranslate(double setpoint, double maxOutput) {
		super("PIDTranslation");
		requires(DriveTrain.getInstance());

		this.setpoint = setpoint;
		this.maxOutput = maxOutput;

		this.motionID = AutonDriver.getInstance().initNewDriveMethod(DriveState.MOTION_TRANSLATE);
	}

	@Override
	public void initialize() {
		if(setpoint < 0){
			AutonDriver.getInstance().setupMotionProfile(setpoint, LinearMovementType.TRANSLATION, true);
		}
		else{
			AutonDriver.getInstance().setupMotionProfile(setpoint, LinearMovementType.TRANSLATION);
		}

		t = 0;
	}

	@Override
	public void execute() {
		t = timeSinceInitialized();
		AutonMotionOutput output = AutonDriver.getInstance().update(t);
		DriveTrain.getInstance().translation(output.getLeft(), output.getRight(), maxOutput);
	}


	@Override
	public void end() {
		System.out.println("Ending MotionProfileTranslate Command!");
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
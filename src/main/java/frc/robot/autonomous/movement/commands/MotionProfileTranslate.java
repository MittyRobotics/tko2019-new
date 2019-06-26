package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.autonomous.enums.DriveState;
import frc.robot.autonomous.enums.LinearMovementType;
import frc.robot.autonomous.movement.AutonDriver;
import frc.robot.autonomous.movement.AutonMotionOutput;
import frc.robot.drive.DriveTrain;
import utils.motionprofile.TrapezoidalMotionProfile;
import utils.purepursuit.VelocityConstraints;

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
		super("PIDTranslation");
		requires(DriveTrain.getInstance());

		this.setpoint = setpoint;
		this.maxOutput = 0.5;

		this.motionID = AutonDriver.getInstance().initNewDriveMethod(DriveState.MOTION_TRANSLATE);
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
		AutonDriver.getInstance().setupMotionProfile(setpoint, LinearMovementType.TRANSLATION);
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
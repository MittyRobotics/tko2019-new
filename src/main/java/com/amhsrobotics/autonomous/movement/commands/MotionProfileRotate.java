package com.amhsrobotics.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.autonomous.enums.DriveState;
import com.amhsrobotics.autonomous.enums.LinearMovementType;
import com.amhsrobotics.autonomous.enums.RotationDirection;
import com.amhsrobotics.autonomous.movement.AutonDriver;
import com.amhsrobotics.drive.DriveTrain;

/**
 * Rotation command for rotating the robot a set amount of degrees using a PID control loop following a Trapezoidal Motion Profile over time.
 * <p>
 * This command takes in an angle in degrees, a maximum output in percent output (0 to 1), and a direction value to rotate the robot.
 */
public class MotionProfileRotate extends Command {

	double setpoint;
	double maxOutput;
	double t;

	int motionID;

	public MotionProfileRotate(double setpoint,  RotationDirection direction) {
		this(setpoint, 0.5, direction);
	}
	public MotionProfileRotate(double setpoint, double maxOutput,  RotationDirection direction) {
		super("MotionProfileRotate");
		requires(DriveTrain.getInstance());

		this.setpoint = Math.abs(setpoint) * direction.value;
		this.maxOutput = maxOutput;

		this.motionID = AutonDriver.getInstance().initNewDriveMethod(DriveState.MOTION_ROTATE);
	}

	@Override
	public void initialize() {
		if(setpoint < 0){
			AutonDriver.getInstance().setupMotionProfile(setpoint,LinearMovementType.ROTATION, true);
		}
		else{
			AutonDriver.getInstance().setupMotionProfile(setpoint,LinearMovementType.ROTATION);
		}

		t = 0;
	}


	@Override
	public void execute() {
		t = timeSinceInitialized();
		DriveTrain.getInstance().rotation(AutonDriver.getInstance().update(t).getAngle(),maxOutput);
	}


	@Override
	public void end() {
		System.out.println("Ending MotionProfileRotate Command!");
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
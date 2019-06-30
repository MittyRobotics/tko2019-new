package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.autonomous.enums.DriveState;
import frc.robot.autonomous.enums.RotationDirection;
import frc.robot.autonomous.movement.AutonDriver;
import frc.robot.drive.DriveTrain;

/**
 * PIDRotation command for rotating the robot's chassis a set amount of degrees using a PID control loop.
 * <p>
 * This command takes in an angle in degrees, a maximum output in percent output (0 to 1), and a direction value to rotate the robot.
 */
public class PIDRotation extends Command {
	double setpoint;
	double maxOutput;
	double t;

	int motionID;

	public PIDRotation(double setpoint, RotationDirection direction) {
		new PIDRotation(setpoint,0.5,direction);
	}

	public PIDRotation(double setpoint, double maxOutput, RotationDirection direction) {
		super("PIDRotation");
		requires(DriveTrain.getInstance());

		this.setpoint = Math.abs(setpoint) * direction.value;
		this.maxOutput = maxOutput;

		this.motionID = AutonDriver.getInstance().initNewDriveMethod(DriveState.PID_ROTATE);
	}

	@Override
	public void initialize() {
		AutonDriver.getInstance().setupPIDMovement();
		DriveTrain.getInstance().rotation(setpoint,maxOutput);
	}


	@Override
	public void execute() {
		t = timeSinceInitialized();
		AutonDriver.getInstance().update(t);
	}


	@Override
	public void end() {
		System.out.println("Ending PIDRotation Command!");
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
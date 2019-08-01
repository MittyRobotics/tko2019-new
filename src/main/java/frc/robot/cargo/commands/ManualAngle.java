package frc.robot.cargo.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargo.Arm;
import frc.robot.oi.OI;

/**
 * Manual Angle command, which changes the arm position based on a joystick
 * The joystick controls motor power
 * This is the default command for the Arm subsystem
 */
public class ManualAngle extends Command {
	/**
	 * Constructor for the Manual Angle Command
	 * Sets the command to the Arm subsystem
	 */
	public ManualAngle() {
		super("Manual Angle");
		requires(Arm.getInstance());
	}

	@Override
	protected void initialize() {

	}

	/**
	 * Sets motor speeds for the arm based on joystick value
	 */
	@Override
	protected void execute() {
		if (Math.abs(OI.getInstance().getJoystick2().getY()) > 0.35) {
			Arm.getInstance().manualAngle(OI.getInstance().getJoystick2().getY());
		}
	}

	/**
	 * Sets the motor to stop moving when the command end
	 */
	@Override
	protected void end() {
		Arm.getInstance().manualAngle(0);
	}

	/**
	 * Sets the motor to stop moving when the command is interrupted
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

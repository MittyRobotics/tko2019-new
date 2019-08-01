package frc.robot.cargo.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargo.Rollers;

/**
 * Intake command, which runs the intake if the ball sensor is not pressed
 */
public class Intake extends Command {

	/**
	 * Constructor for Intake
	 * Sets command to run on the Roller subsystem
	 */
	public Intake() {
		super("Intake");
		requires(Rollers.getInstance());
	}

	/**
	 * Sets to roller motors to intake the ball
	 */
	@Override
	protected void initialize() {
		Rollers.getInstance().intake();
	}

	@Override
	protected void execute() {

	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {
		end();
	}

	/**
	 * checks if the command is finished
	 * @return if the ball is detected
	 */
	@Override
	protected boolean isFinished() {
		return Rollers.getInstance().ballDetected();
	}
}

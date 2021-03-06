package com.amhsrobotics.cargo.commands;

import com.amhsrobotics.cargo.Rollers;
import edu.wpi.first.wpilibj.command.Command;

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

	/**
	 * Stops the rollers when the command ends
	 */
	@Override
	protected void end() {
		Rollers.getInstance().stopRollers();
	}

	/**
	 * Stops the rollers when the command is interrupted
	 */
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

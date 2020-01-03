package com.amhsrobotics.cargo.commands;

import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.cargo.Rollers;

/**
 * Stop Rollers command, which sets the rollers to stop spinning
 */
public class StopRollers extends Command {
	/**
	 * Constructor for Stop Rollers Command
	 * Sets the command to the Roller Subsystem
	 */
	public StopRollers() {
		super("Stop Rollers");
		requires(Rollers.getInstance());
	}

	/**
	 * Sets to rollers to stop moving
	 */
	@Override
	protected void initialize() {
		Rollers.getInstance().stopRollers();
	}

	/**
	 * Command ends immediately
	 * @return true
	 */
	@Override
	protected boolean isFinished() {
		return true;
	}
}

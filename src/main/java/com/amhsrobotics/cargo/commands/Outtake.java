package com.amhsrobotics.cargo.commands;

import com.amhsrobotics.cargo.Rollers;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Outtake command, which sets the rollers to outtake the ball
 */
public class Outtake extends Command {
	/**
	 * Constructor for Outtake Command
	 * Sets command to the Roller subsystem
	 */
	public Outtake() {
		super("Outtake");
		requires(Rollers.getInstance());
	}

	/**
	 * Sets the roller motors to outtake ball
	 */
	@Override
	protected void initialize() {
		Rollers.getInstance().outtake();
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

package com.amhsrobotics.cargo.commands;

import com.amhsrobotics.cargo.Rollers;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Outtake Back command, which runs to shoot the ball using vision
 */
public class OuttakeBack extends Command {

	/**
	 * Constructor for Outtake
	 * Sets command to run on the Roller subsystem
	 */
	public OuttakeBack() {
		super("Intake");
		requires(Rollers.getInstance());
	}

	/**
	 * Sets to roller motors to outtake the ball
	 */
	@Override
	protected void initialize() {
		Rollers.getInstance().visionOuttake();

	}

	/**
	 * checks if the command is finished
	 * @return always true
	 */
	@Override
	protected boolean isFinished() {
		return true;
	}
}

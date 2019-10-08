package com.amhsrobotics.hatchpanel.commands;

import com.amhsrobotics.hatchpanel.Grabber;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Grab Command to Release a hatch panel
 */
public class Release extends Command {

	/**
	 * Constructor for Release command
	 */
	public Release() {
		super("Release");
		requires(Grabber.getInstance());
	}

	/**
	 * Runs the command to release a hatch panel
	 */
	@Override
	protected void initialize(){
		Grabber.getInstance().release();
	}

	/**
	 * Always ends immediately
	 * @return true
	 */
	@Override
	protected boolean isFinished() {
		return true;
	}
}

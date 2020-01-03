package com.amhsrobotics.hatchpanel.commands;

import com.amhsrobotics.hatchpanel.Grabber;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Grab Command to Grab a hatch panel
 */
public class Grab extends Command {

	/**
	 * Constructor for Grab command
	 */
	public Grab() {
		super("Grab");
		requires(Grabber.getInstance());
	}

	/**
	 * Runs the command to grab a hatch panel
	 */
	@Override
	protected void initialize(){
		Grabber.getInstance().grab();
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

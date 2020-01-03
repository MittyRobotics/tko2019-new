package com.amhsrobotics.hatchpanel.commands;

import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.hatchpanel.Pusher;

/**
 * PushForward Command that sets the slider to be pushed forward
 */
public class PushForward extends Command {
	/**
	 * Constructor for PushForward command
	 * requires the Pusher subsystem
	 */
	public PushForward() {
		super("Push Forward");
		requires(Pusher.getInstance());
	}

	/**
	 * Runs function to push slider forward once
	 */
	@Override
	protected void initialize() {
		Pusher.getInstance().pushForward();
	}

	/**
	 * Always ends command immediately
	 * @return true
	 */
	@Override
	protected boolean isFinished() {
		return true;
	}
}
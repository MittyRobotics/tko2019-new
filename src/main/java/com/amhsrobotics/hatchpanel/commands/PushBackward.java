package com.amhsrobotics.hatchpanel.commands;

import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.hatchpanel.Pusher;

/**
 * PushBackward Command that sets the slider to be pulled back
 */
public class PushBackward extends Command {
	/**
	 * Constructor for PushBackward command
	 * requires the Pusher subsystem
	 */
	public PushBackward() {
		super("Push Backward");
		requires(Pusher.getInstance());
	}

	/**
	 * Runs function to push slider back once
	 */
	@Override
	protected void initialize() {
		Pusher.getInstance().pushBackward();
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
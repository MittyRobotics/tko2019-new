package com.amhsrobotics.hatchpanel.commands;

import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.hatchpanel.Pusher;

public class PushBackward extends Command {
	public PushBackward() {
		super("Push Backward");
		requires(Pusher.getInstance());
	}

	@Override
	protected void initialize() {
		Pusher.getInstance().pushBackward();
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

	@Override
	protected boolean isFinished() {
		return true;
	}
}
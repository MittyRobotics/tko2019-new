package com.amhsrobotics.hatchpanel.commands;

import com.amhsrobotics.hatchpanel.Pusher;
import edu.wpi.first.wpilibj.command.Command;

public class PushForward extends Command {
	public PushForward() {
		super("Push Forward");
		requires(Pusher.getInstance());
	}

	@Override
	protected void initialize() {
		Pusher.getInstance().pushForward();
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
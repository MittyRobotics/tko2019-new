package com.amhsrobotics.autonomous;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Testing command to test the scheduler.
 * <p>
 * This prints out which phase the command is in to test if the scheduler is correctly running each phase of the command.
 */
public class TestCommand extends Command {

	public TestCommand() {
		super("TestCommand");
		System.out.println("TestCommand");
	}


	@Override
	public void initialize() {
		System.out.println("initialize");
	}


	@Override
	public void execute() {
		System.out.println("execute");
	}


	@Override
	public void end() {
		System.out.println("end");
	}

	@Override
	public void interrupted() {
		System.out.println("interrupted");
		end();
	}


	@Override
	protected boolean isFinished() {
		return false;
	}
}
package frc.robot.cargo.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargo.Rollers;

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

	/**
	 * Command ends immediately
	 * @return true
	 */
	@Override
	protected boolean isFinished() {
		return true;
	}
}

package frc.robot.cargo.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargo.Rollers;

public class StopRollers extends Command {
	/**
	 * Constructor for Stop Rollers Command
	 * Sets the command to the Roller Subsystem
	 */
	public StopRollers() {
		super("Stop Rollers");
		requires(Rollers.getInstance());
	}

	/**
	 * Sets to rollers to stop moving
	 */
	@Override
	protected void initialize() {
		Rollers.getInstance().stopRollers();
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

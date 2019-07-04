package frc.robot.cargo.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargo.Rollers;

public class StopRollers extends Command {
	public StopRollers() {
		super("Stop Rollers");
		requires(Rollers.getInstance());
	}

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

	@Override
	protected boolean isFinished() {
		return true;
	}
}

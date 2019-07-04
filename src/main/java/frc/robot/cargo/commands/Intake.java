package frc.robot.cargo.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargo.Rollers;

public class Intake extends Command {
	public Intake() {
		super("Intake");
		requires(Rollers.getInstance());
	}

	@Override
	protected void initialize() {
		Rollers.getInstance().intake();
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
		return Rollers.getInstance().ballDetected();
	}
}

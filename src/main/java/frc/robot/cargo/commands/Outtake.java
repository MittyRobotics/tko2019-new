package frc.robot.cargo.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargo.Rollers;
import frc.robot.oi.OI;

public class Outtake extends Command {
	public Outtake() {
		super("Outtake");
		requires(Rollers.getInstance());
	}

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

	@Override
	protected boolean isFinished() {
		return true;
	}
}

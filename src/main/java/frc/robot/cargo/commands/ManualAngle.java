package frc.robot.cargo.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargo.Arm;
import frc.robot.oi.OI;

public class ManualAngle extends Command {
	public ManualAngle() {
		super("Manual Angle");
		requires(Arm.getInstance());
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Arm.getInstance().manualAngle(OI.getInstance().getJoystick1().getY());
	}

	@Override
	protected void end() {
		Arm.getInstance().manualAngle(0);
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}

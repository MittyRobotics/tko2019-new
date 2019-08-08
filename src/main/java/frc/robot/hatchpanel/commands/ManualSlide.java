package frc.robot.hatchpanel.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.hatchpanel.Slider;
import frc.robot.oi.OI;

public class ManualSlide extends Command {
	public ManualSlide() {
		super("Manual Slide");
		requires(Slider.getInstance());
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {

		if (Math.abs(OI.getInstance().getJoystick1().getX()) > 0.2) {
			Slider.getInstance().manualSlide(OI.getInstance().getJoystick1().getX());
		}
	}

	@Override
	protected void end() {
		Slider.getInstance().manualSlide(0);
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
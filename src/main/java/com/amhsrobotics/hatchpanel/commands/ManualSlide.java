package com.amhsrobotics.hatchpanel.commands;

import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.hatchpanel.Slider;
import com.amhsrobotics.oi.OI;

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

			Slider.getInstance().manualSlide(OI.getInstance().getJoystick1().getX() / 4);
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
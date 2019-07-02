package frc.robot.hatchpanel.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.hatchpanel.Slider;
import frc.robot.hatchpanel.constants.SliderPosition;
import frc.robot.oi.OI;

public class Slide extends Command {
	private SliderPosition sliderPosition;
	public Slide(SliderPosition sliderPosition) {
		super("Slide");
		requires(Slider.getInstance());
		this.sliderPosition = sliderPosition;
	}

	@Override
	protected void initialize() {
		Slider.getInstance().slide(sliderPosition);
	}

	@Override
	protected void execute() {

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
		return Math.abs(Slider.getInstance().getSlideError()) < 10
				|| Math.abs(OI.getInstance().getJoystick1().getX()) > 0.2;
	}
}

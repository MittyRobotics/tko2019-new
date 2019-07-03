package frc.robot.hatchpanel.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.hatchpanel.Slider;
import frc.robot.hatchpanel.constants.SliderPosition;
import frc.robot.oi.OI;
import motion_profile.TrapezoidalMotionProfile;

public class Slide extends Command {
	private SliderPosition sliderPosition;
	private TrapezoidalMotionProfile slide;
	private double startPosition;
	private double t;
	public Slide(SliderPosition sliderPosition) {
		super("Slide");
		requires(Slider.getInstance());
		this.sliderPosition = sliderPosition;
	}

	@Override
	protected void initialize() {
		slide = Slider.getInstance().slide(sliderPosition);
		t = 0;
		startPosition = Slider.getInstance().getPosition();
	}

	@Override
	protected void execute() {
		t = timeSinceInitialized();
		Slider.getInstance().setSliderPosition(startPosition + slide.getFrameAtTime(t).getPosition());
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
		return slide.isFinished() || Math.abs(OI.getInstance().getJoystick1().getX()) > 0.2;
	}
}

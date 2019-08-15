package com.amhsrobotics.hatchpanel.commands;

import com.amhsrobotics.hatchpanel.constants.SliderPosition;
import com.amhsrobotics.hatchpanel.constants.TicksPerInch;
import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.hatchpanel.Slider;
import com.amhsrobotics.oi.OI;
import team1351.motionprofile.*;

public class Slide extends Command {
	private SliderPosition sliderPosition;
	private TrapezoidalMotionProfile slide;
	private double t;
	public Slide(SliderPosition sliderPosition) {
		super("Slide");
		requires(Slider.getInstance());
		this.sliderPosition = sliderPosition;
		System.out.println("construct");
	}

	@Override
	protected void initialize() {
		System.out.println("init");
		slide = Slider.getInstance().slide(sliderPosition);
		t = 0;
	}

	@Override
	protected void execute() {
		t = timeSinceInitialized();
		MotionFrame frame = slide.getFrameAtTime(t);
		Slider.getInstance().setSliderPosition(frame.getPosition() * TicksPerInch.SLIDER);
		System.out.println(frame.getPosition());
	}

	@Override
	protected void end() {
		Slider.getInstance().manualSlide(0);
		System.out.println("end");
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

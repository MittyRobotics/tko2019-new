package com.amhsrobotics.hatchpanel.commands;

import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.hatchpanel.Slider;
import com.amhsrobotics.oi.OI;

/**
 * Manual Slide Command to move slider with joystick
 */
public class ManualSlide extends Command {
	/**
	 * Constructor for Manual Slide
	 * Requires the Slider subsystem
	 */
	public ManualSlide() {
		super("Manual Slide");
		requires(Slider.getInstance());
	}

	/**
	 * Constantly runs the joystick to manual slide
	 */
	@Override
	protected void execute() {
		Slider.getInstance().manualSlide(OI.getInstance().getJoystick1().getX() / 4);
	}

	/**
	 * Sets slider to not move when the command ends
	 */
	@Override
	protected void end() {
		Slider.getInstance().manualSlide(0);
	}

	/**
	 * Sets the slider to not move when the command is interrupted
	 */
	@Override
	protected void interrupted() {
		end();
	}

	/**
	 * Sets the command to end when the joystick goes within the threshold
	 * @return if value of joystick is less than 0.2 away from the center
	 */
	@Override
	protected boolean isFinished() {
		return Math.abs(OI.getInstance().getJoystick1().getX()) < 0.2;
	}
}
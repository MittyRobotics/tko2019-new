package com.amhsrobotics.cargo.commands;

import com.amhsrobotics.cargo.Rollers;
import com.amhsrobotics.cargo.constants.IntakeSpeeds;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Intake command, which runs the intake if the ball sensor is not pressed
 */
public class OuttakeBack extends Command {

	/**
	 * Constructor for Intake
	 * Sets command to run on the Roller subsystem
	 */
	public OuttakeBack() {
		super("Intake");
		requires(Rollers.getInstance());
	}

	/**
	 * Sets to roller motors to intake the ball
	 */
	@Override
	protected void initialize() {
		Rollers.getInstance().setRollerSpeeds(IntakeSpeeds.TOP_ROLLER_OUTTAKE_BACK, 0);
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

	/**
	 * checks if the command is finished
	 * @return if the ball is detected
	 */
	@Override
	protected boolean isFinished() {
		return true;
	}
}

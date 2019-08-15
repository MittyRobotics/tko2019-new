package com.amhsrobotics.cargo.commands;

import com.amhsrobotics.cargo.constants.ArmPosition;
import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.cargo.Arm;
import com.amhsrobotics.oi.OI;
import com.amhsrobotics.motionprofile.MotionFrame;
import com.amhsrobotics.motionprofile.TrapezoidalMotionProfile;

/**
 * Angle command, which sets the arm angle
 * The angle is set to either rocket, ground, or cargoship, based on a parameter
 */
public class Angle extends Command {
	private ArmPosition armPosition;
	private TrapezoidalMotionProfile angle;
	private double t;

	/**
	 * Constructor for Angle Command that runs
	 * Sets the command to the Angle subsystem
	 * @param armPosition the position for arm to move
	 */
	public Angle(ArmPosition armPosition) {
		super("Angle");
		requires(Arm.getInstance());
		this.armPosition = armPosition;
	}

	/**
	 * Initializes the command
	 */
	@Override
	protected void initialize() {
		angle = Arm.getInstance().angle(armPosition);
		t = 0;
	}

	/**
	 * Constantly sets the arm to be at a position based on a Trapezoidal Motion Profile at time t
	 */
	@Override
	protected void execute() {
		t = timeSinceInitialized();
		MotionFrame frame = angle.getFrameAtTime(t);
		Arm.getInstance().setArmPosition(frame.getPosition());
		System.out.println(frame.getPosition());
	}

	/**
	 * Holds the arm at the final position of the Trapezoidal Motion Profile
	 */
	@Override
	protected void end() {
		//Arm.getInstance().setArmPosition(angle.getFrameAtTime(t).getPosition());
		new HoldPosition(angle.getFrameAtTime(t).getPosition()).start();
	}

	/**
	 * Sets the Arm motor to stop moving when command is interrupted
	 */
	@Override
	protected void interrupted() {
		Arm.getInstance().manualAngle(0);
	}

	/**
	 * Checks if the command is finished
	 * @return if manual should take over or the motion frame is finished
	 */
	@Override
	protected boolean isFinished() {
		return angle.isFinished() || Math.abs(OI.getInstance().getJoystick2().getY()) > 0.1;
	}
}

package frc.robot.autonomous.movement;

import frc.robot.autonomous.movement.commands.Translate2dTrajectory;
import frc.robot.autonomous.movement.commands.MotionProfileTranslate;

/**
 * Final output class for all drive motion (excluding vision).
 * <p>
 * This contains a left wheel value, right wheel value, and angle value (for rotation commands). Left and right wheel
 * values are in either velocity or position units, velocity for {@link Translate2dTrajectory} commands and position
 * for {@link MotionProfileTranslate} commands.
 */
public class AutonMotionOutput {
	private double left;
	private double right;
	private double angle;

	public AutonMotionOutput(double left, double right, double angle) {
		this.left = left;
		this.right = right;
		this.angle = angle;
	}

	public double getLeft() {
		return left;
	}

	public double getRight() {
		return right;
	}

	public double getAngle() {
		return angle;
	}
}

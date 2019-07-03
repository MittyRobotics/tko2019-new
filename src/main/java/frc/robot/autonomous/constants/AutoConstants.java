package frc.robot.autonomous.constants;

import frc.robot.autonomous.movement.VelocityConstraints;

public class AutoConstants {
	/**
	 * Velocity constraints for drive train.
	 * Units: maxAcceleration: inches/second^2 maxVelocity: inches/second
	 */
	public static VelocityConstraints DRIVE_VELOCITY_CONSTRAINTS = new VelocityConstraints(8,60);
	/**Inches per angle value for motion profiling turning in place.*/
	public static double INCHES_PER_ANGLE = 2;
}

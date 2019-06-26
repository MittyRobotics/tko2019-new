package frc.robot.autonomous.constants;

import utils.purepursuit.VelocityConstraints;

public class AutoConstants {
	public static VelocityConstraints DRIVE_VELOCITY_CONSTRAINTS = new VelocityConstraints(8,60); //Units: Inches
	public static VelocityConstraints TURN_VELOCITY_CONSTRAINTS = new VelocityConstraints(5,10); //Units: Angle in degrees
}

package frc.robot.autonomous.movement.commands;

/**
 * The direction of rotation for rotation commands. Normally this would be
 * established with either a negative or positive value for the rotation command,
 * but the direction parameter of the rotation command was created to avoid confusion
 */
public enum RotationDirection {
	Right(0),
	Left(1);
	
	public int value;
	
	RotationDirection(int i) {
		value = i;
	}
}

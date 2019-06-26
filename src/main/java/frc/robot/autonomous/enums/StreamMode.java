package frc.robot.autonomous.enums;

/**
 * The stream mode for the Limelight camera
 */
public enum StreamMode {
	Standard(0),
	Main(1),
	Secondary(2);
	
	public int value;
	
	StreamMode(int i) {
		value = i;
	}
}

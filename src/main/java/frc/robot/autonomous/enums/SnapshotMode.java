package frc.robot.autonomous.enums;

/**
 * The snapshot mode for the Limelight camera
 */
public enum SnapshotMode {
	Off(0),
	On(1);
	
	public int value;
	
	SnapshotMode(int i) {
		value = i;
	}
}

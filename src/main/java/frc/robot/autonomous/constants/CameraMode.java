package frc.robot.autonomous.constants;

public enum CameraMode {
	Vision(0),
	Driver(1);
	
	public int value;
	
	CameraMode(int i) {
		value = i;
	}
}

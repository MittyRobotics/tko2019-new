package com.amhsrobotics.autonomous.enums;

/**
 * The camera mode for the Limelight camera.
 */
public enum CameraMode {
	Vision(0),
	Driver(1);
	
	public int value;
	
	CameraMode(int i) {
		value = i;
	}
}

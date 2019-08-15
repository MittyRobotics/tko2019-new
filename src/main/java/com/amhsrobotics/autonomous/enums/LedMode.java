package com.amhsrobotics.autonomous.enums;

/**
 * The LED mode for the Limelight camera
 */
public enum LedMode {
	Current(0),
	Off(1),
	Blinking(2),
	On(3);
	
	public int value;
	
	LedMode(int i) {
		value = i;
	}
}

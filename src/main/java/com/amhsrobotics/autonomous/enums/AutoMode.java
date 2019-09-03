package com.amhsrobotics.autonomous.enums;

public enum AutoMode {
	EXIT_HAB_AUTO(0),
	CARGO_SHIP_FRONT_HATCH_AUTO(1);

	public int value;

	AutoMode(int i) {
		value = i;
	}

	public static AutoMode getMode(int value) {
		for (AutoMode mode : AutoMode.values()) {
			if (mode.value == value) return mode;
		}
		return EXIT_HAB_AUTO;
	}
}

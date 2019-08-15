package com.amhsrobotics.hardware;

public class Compressor extends edu.wpi.first.wpilibj.Compressor {
	private static Compressor ourInstance = new Compressor();

	public static Compressor getInstance() {
		return ourInstance;
	}

	private Compressor() {
		super();
	}
}

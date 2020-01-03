package com.amhsrobotics.hardware;

/**
 * Class to access the compressor
 */
public class Compressor extends edu.wpi.first.wpilibj.Compressor {
	private static Compressor ourInstance = new Compressor();

	/**
	 * Gets the only instance of the compressor being used as a singleton
	 * @return a static instance of the compressor
	 */
	public static Compressor getInstance() {
		return ourInstance;
	}

	/**
	 * Runs the constructor of the wpilib Compressor Class
	 */
	private Compressor() {
		super();
	}
}
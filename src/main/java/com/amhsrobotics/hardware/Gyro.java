package com.amhsrobotics.hardware;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Gyro extends ADXRS450_Gyro {
	private static Gyro ourInstance = new Gyro();

	public static Gyro getInstance() {
		return ourInstance;
	}

	private Gyro() {
		super();
	}
}

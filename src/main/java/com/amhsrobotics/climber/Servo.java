package com.amhsrobotics.climber;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Servo extends Subsystem {
	private static Servo ourInstance = new Servo();

	public static Servo getInstance() {
		return ourInstance;
	}
	private edu.wpi.first.wpilibj.Servo servo;
	private Servo() {
		super("Servo");
	}
	public void initHardware(){
		servo = new edu.wpi.first.wpilibj.Servo(0);
		servo.setAngle(0);
	}

	@Override
	protected void initDefaultCommand() {

	}
	public void setAngle(double angle){
		servo.setAngle(angle);
	}
}

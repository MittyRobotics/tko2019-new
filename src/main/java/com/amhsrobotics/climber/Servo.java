package com.amhsrobotics.climber;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Servo extends Subsystem {
	private static Servo ourInstance = new Servo();

	public static Servo getInstance() {
		return ourInstance;
	}
	private edu.wpi.first.wpilibj.Servo servo1, servo2;
	private Servo() {
		super("Servo");
	}
	public void initHardware(){
		servo1 = new edu.wpi.first.wpilibj.Servo(0);
		servo2 = new edu.wpi.first.wpilibj.Servo(1);
	}

	@Override
	protected void initDefaultCommand() {

	}
	public void setAngle(int servo, double angle){
		if(servo == 1){
			servo1.setAngle(angle);
		} else {
			servo2.setAngle(angle);
		}
	}
}

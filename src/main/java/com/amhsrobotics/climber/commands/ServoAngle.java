package com.amhsrobotics.climber.commands;

import com.amhsrobotics.climber.Servo;
import edu.wpi.first.wpilibj.command.Command;

public class ServoAngle extends Command {
	private double angle;
	private int servo;
	public ServoAngle(int servo, double angle){
		super("Servo Angle");
		this.servo = servo;
		this.angle = angle;
		requires(Servo.getInstance());
	}
	@Override
	protected void initialize(){
		Servo.getInstance().setAngle(servo, angle);
	}
	@Override
	protected boolean isFinished() {
		return true;
	}
}

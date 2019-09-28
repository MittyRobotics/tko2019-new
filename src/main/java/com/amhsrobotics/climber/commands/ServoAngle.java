package com.amhsrobotics.climber.commands;

import com.amhsrobotics.climber.Servo;
import edu.wpi.first.wpilibj.command.Command;

public class ServoAngle extends Command {
	private double angle;
	public ServoAngle(double angle){
		super("Servo Angle");
		this.angle = angle;
		requires(Servo.getInstance());
	}
	@Override
	protected void initialize(){
		Servo.getInstance().setAngle(angle);
	}
	@Override
	protected boolean isFinished() {
		return true;
	}
}

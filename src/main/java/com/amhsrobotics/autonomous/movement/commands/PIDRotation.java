package com.amhsrobotics.autonomous.movement.commands;

import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.drive.constants.PID;
import com.amhsrobotics.hardware.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
public class PIDRotation extends Command {
	private double target;
	private PIDController controller;
	private int count = 0;
	public PIDRotation(double target){
		requires(DriveTrain.getInstance());

		this.target = target;
	}
	@Override
	protected void initialize(){
		final double MAX_SPEED = .3;
		target += Gyro.getInstance().getAngle();
		PIDOutput dummyOutput = output -> {

		};
		controller = new PIDController(PID.TURN[0], PID.TURN[1], PID.TURN[2], Gyro.getInstance(),
				dummyOutput);
		controller.setOutputRange(-MAX_SPEED, MAX_SPEED);
		controller.enable();
	}

	@Override
	protected void execute(){
		final double RAMP_RATE = 20;
		final double THRESHOLD = 1;
		if(target - Gyro.getInstance().getAngle() > RAMP_RATE){
			controller.setSetpoint(Gyro.getInstance().getAngle() + RAMP_RATE);
		} else if(target - Gyro.getInstance().getAngle() < -RAMP_RATE){
			controller.setSetpoint(Gyro.getInstance().getAngle() - RAMP_RATE);
		} else {
			controller.setSetpoint(Gyro.getInstance().getAngle());
		}
		if(Math.abs(target - Gyro.getInstance().getAngle()) < THRESHOLD){
			count++;
		} else {
			count = 0;
		}

		System.out.println(controller.get());
		DriveTrain.getInstance().tankDrive(-controller.get(),controller.get());
	}

	@Override
	protected void end(){
		DriveTrain.getInstance().tankDrive(0, 0);
	}

	@Override
	protected boolean isFinished() {
		return count > 1;
	}
}
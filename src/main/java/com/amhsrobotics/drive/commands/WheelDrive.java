package com.amhsrobotics.drive.commands;

import com.amhsrobotics.drive.constants.PID;
import com.amhsrobotics.hardware.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.oi.OI;

public class WheelDrive extends Command {
	private PIDController controller;
	private double maxTurnSpeed;
	private double startAngle;
	private double prevAngle;
	private double rampRate = 2;
	private double tempAngle;
	public WheelDrive(double maxTurnSpeed) {
		super("Wheel Drive");
		requires(DriveTrain.getInstance());
		this.maxTurnSpeed = maxTurnSpeed;
	}
	
	@Override
	protected void initialize() {
		PIDOutput output = new PIDOutput() {
			@Override
			public void pidWrite(double output) {

			}
		};
		controller = new PIDController(PID.TURN[0], PID.TURN[1], PID.TURN[2],
				Gyro.getInstance(), output);
		controller.setOutputRange(-maxTurnSpeed, maxTurnSpeed);
		startAngle = Gyro.getInstance().getAngle();
		controller.setInputRange(-180, 180);
		prevAngle = startAngle;
		tempAngle = startAngle;
		controller.setSetpoint(startAngle);
		controller.enable();
	}
	
	@Override
	protected void execute() {
//		rampRate = controller.getError() /2;
		final double DRIVE_E = 0.5;
		final double TURN_E = 1 - DRIVE_E;
		final double WHEEL_ROTATION = 90 * 90.0/70;
		double angle = -OI.getInstance().getSteeringWheel().getX();
		if(Math.abs(angle) < 0.05){
			angle = 0;
		}
		angle *= WHEEL_ROTATION;
		if(angle < -180){
			angle += 180;
		} else if (angle > 180){
			angle -= 180;
		}
//		if(Math.abs(prevAngle - angle) < 2){
//			angle = prevAngle;
//		}
		if(Math.abs(angle - tempAngle) < rampRate){
			tempAngle = angle;
		} else if (angle > tempAngle){
			tempAngle += rampRate;
		} else {
			tempAngle -= rampRate;
		}
		controller.setSetpoint(startAngle + tempAngle);
		double drive = OI.getInstance().getJoystick2().getY() * DRIVE_E;
		if(Math.abs(drive) < 0.05){
			drive = 0;
		}
		System.out.println("GYRO: " + Gyro.getInstance().getAngle());
		System.out.println("ANGLE: " + angle);
		System.out.println("PID: " + controller.get());
		double turn = controller.get() * TURN_E;
		DriveTrain.getInstance().tankDrive(drive - turn,
				drive + turn);
//		DriveTrain.getInstance().wheelDrive(OI.getInstance().getJoystick1().getY(GenericHID.Hand.kRight),
//		    OI.getInstance().getSteeringWheel().getX(GenericHID.Hand.kLeft),
//		    OI.getInstance().getSteeringWheel().getAButton());
		prevAngle = angle;
	}
	
	@Override
	protected void end() {
		DriveTrain.getInstance().tankDrive(0, 0);
	}
	
	@Override
	protected void interrupted() {
		end();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}

package com.amhsrobotics.drive.commands;

import com.amhsrobotics.drive.constants.PID;
import com.amhsrobotics.hardware.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.oi.OI;

public class WheelDrive extends Command {
	private PIDController controller;
	private double maxTurnSpeed;
	private double startAngle;
	private double prevAngle;
	public WheelDrive(double maxTurnSpeed) {
		super("Wheel Drive");
		requires(DriveTrain.getInstance());
		this.maxTurnSpeed = maxTurnSpeed;
	}
	
	@Override
	protected void initialize() {
		controller = new PIDController(PID.TURN[0], PID.TURN[1], PID.TURN[2],
				Gyro.getInstance(), DriveTrain.getInstance().getLeftController());
		controller.setOutputRange(-maxTurnSpeed, maxTurnSpeed);
		controller.setContinuous(true);
		startAngle = Gyro.getInstance().getAngle();
		controller.setInputRange(-180, 180);
		prevAngle = startAngle;
	}
	
	@Override
	protected void execute() {
		final double DRIVE_E = 0.5;
		final double TURN_E = 1 - DRIVE_E;
		final double WHEEL_ROTATION = 360;
		double angle = OI.getInstance().getSteeringWheel().getX();
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
		controller.setSetpoint(startAngle + angle);
		double drive = OI.getInstance().getJoystick1().getY() * DRIVE_E;
		if(Math.abs(drive) < 0.05){
			drive = 0;
		}
		double turn = controller.get() * TURN_E;
		DriveTrain.getInstance().tankDrive(drive + turn,
				drive - turn * TURN_E);
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

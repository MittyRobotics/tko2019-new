package frc.robot.autonomous;

import frc.robot.drive.DriveTrain;
import frc.robot.hardware.Gyro;

import java.util.TimerTask;

public class RobotPose {
	private static RobotPose ourInstance = new RobotPose();

	private double robotX = 0;
	private double robotY = 0;
	private double robotHeading = 0;

	private double lastLeftEncoderPos = 0;
	private double lastRightEncoderPos = 0;

	public static RobotPose getInstance() {
		return ourInstance;
	}

	private RobotPose() {
	}

	public void resetPosition(){
		robotX = 0;
		robotY = 0;
		//lastLeftEncoderPos = getLeftEncoder();
		//lastRightEncoderPos = getRightEncoder();
	}

	public void setPos(double x, double y, double heading){
		this.robotX = x;
		this.robotY = y;
		this.robotHeading = heading;
	}
	public void update(){

		//Update robot values based on encoder and gyro output
		robotHeading = Gyro.getInstance().getAngle();
		double deltaLeftPos = DriveTrain.getInstance().getLeftEncoder() - lastLeftEncoderPos;
		double deltaRightPos = DriveTrain.getInstance().getRightEncoder()  - lastRightEncoderPos;
		double deltaPosition = (deltaLeftPos + deltaRightPos)/2;
		robotX += deltaPosition * Math.cos(Math.toRadians(robotHeading));
		robotY += deltaPosition * Math.sin(Math.toRadians(robotHeading));
	}

	public double getRobotX(){
		return robotX;
	}

	public double getRobotY(){
		return robotY ;
	}

	public double getRobotHeading(){
		return robotHeading;
	}
}

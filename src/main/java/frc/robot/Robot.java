package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.command.Scheduler;

import frc.robot.autonomous.movement.commands.Translate2dTrajectory;
import frc.robot.drive.DriveTrain;

import frc.robot.drive.commands.TankDrive;
import frc.robot.hardware.Gyro;
import utils.purepursuit.RobotPose;
import utils.purepursuit.VelocityConstraints;
import utils.purepursuit.Waypoint;

import java.awt.geom.Point2D;


public class Robot extends TimedRobot {

	Robot() {
		super(0.02);
	}
	
	@SuppressWarnings("ResultOfMethodCallIgnored")
	@Override
	public void robotInit() {
		DriveTrain.getInstance();
		DriveTrain.getInstance().initHardware();
		//Shifter.getInstance();
		//OI.getInstance();
		//Compressor.getInstance();
		Gyro.getInstance();
		//Limelight.getInstance();
	}
	
	@Override
	public void robotPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic(){
	}

	@Override
	public void autonomousInit() {

	}
	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopInit() {
		new TankDrive().start();
	}

	@Override
	public void teleopPeriodic() {
	}
	
	@Override
	public void testInit() {
		new TankDrive().start();
		RobotPose.getInstance().resetPosition();
	}
	
	@Override
	public void testPeriodic() {
		RobotPose.getInstance().update();
		System.out.println(RobotPose.getInstance().getRobotX() + " " + RobotPose.getInstance().getRobotY() + " " + RobotPose.getInstance().getRobotHeading() + " Left: " + DriveTrain.getInstance().getLeftEncoder() + " Right: " + DriveTrain.getInstance().getRightEncoder());

	}
}
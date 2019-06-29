package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.command.Scheduler;

import frc.robot.autonomous.movement.commands.Translate2dTrajectory;
import frc.robot.drive.DriveTrain;

import frc.robot.drive.commands.TankDrive;
import frc.robot.drive.constants.TicksPerInch;
import frc.robot.hardware.Gyro;
import frc.robot.oi.OI;
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

	}

	@Override
	public void teleopPeriodic() {
		DriveTrain.getInstance().curavtureDrive(OI.getInstance().getXboxController().getY(GenericHID.Hand.kRight), OI.getInstance().getSteeringWheel().getX(), OI.getInstance().getSteeringWheel().getAButton());
	}
	
	@Override
	public void testInit() {
		new TankDrive().start();
		RobotPose.getInstance().resetPosition(DriveTrain.getInstance().getLeftEncoder(), DriveTrain.getInstance().getRightEncoder(), Gyro.getInstance().getAngle());
	}
	
	@Override
	public void testPeriodic() {
		RobotPose.getInstance().update(DriveTrain.getInstance().getLeftEncoder(), DriveTrain.getInstance().getRightEncoder(), Gyro.getInstance().getAngle(), TicksPerInch.DRIVE);
		System.out.println(RobotPose.getInstance().getRobotX() + " " + RobotPose.getInstance().getRobotY() + " " + RobotPose.getInstance().getRobotHeading() + " Left: " + DriveTrain.getInstance().getLeftEncoder() + " Right: " + DriveTrain.getInstance().getRightEncoder());

	}
}
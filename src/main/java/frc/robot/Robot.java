package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.autonomous.movement.commands.TestCommand;

import frc.robot.autonomous.movement.commands.Translate2dTradjectory;
import frc.robot.drive.DriveTrain;

import frc.robot.hardware.Gyro;
import frc.robot.pure_pursuit.RobotPose;
import frc.robot.pure_pursuit.VelocityConstraints;
import frc.robot.pure_pursuit.Waypoint;

import java.awt.*;
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

		Waypoint[] waypoints = new Waypoint[2];
		waypoints[0] = new Waypoint(new Point2D.Double(0,0), new Point2D.Double(10,0));
		waypoints[1] = new Waypoint(new Point2D.Double(48,-48), new Point2D.Double(48,0));
		new Translate2dTradjectory(waypoints, new VelocityConstraints(12,70)).start();
	}
	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopInit() {
	}

	@Override
	public void teleopPeriodic() {
	}
	
	@Override
	public void testInit() {
		RobotPose.getInstance().resetPosition();
	}
	
	@Override
	public void testPeriodic() {
		RobotPose.getInstance().update();
		System.out.println(RobotPose.getInstance().getRobotX() + " " + RobotPose.getInstance().getRobotY() + " " + RobotPose.getInstance().getRobotHeading());
	}
}
package frc.robot;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.command.Scheduler;

import frc.robot.autonomous.Odometry;
import frc.robot.autonomous.movement.commands.MotionProfileTranslate;
import frc.robot.autonomous.movement.commands.Translate2dTrajectory;
import frc.robot.autonomous.vision.Limelight;
import frc.robot.cargo.Arm;
import frc.robot.cargo.Rollers;
import frc.robot.cargo.commands.CalibrateArm;
import frc.robot.drive.DriveTrain;

import frc.robot.drive.Shifter;
import frc.robot.drive.commands.TankDrive;
import frc.robot.hardware.Compressor;
import frc.robot.hardware.Gyro;
import frc.robot.hatchpanel.Grabber;
import frc.robot.hatchpanel.Pusher;
import frc.robot.hatchpanel.Slider;
import frc.robot.hatchpanel.commands.CalibrateSlider;
import frc.robot.oi.OI;
import team1351.purepursuit.Waypoint;
import team1351.purepursuit.enums.PathType;

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
		Shifter.getInstance();
		Slider.getInstance();
		Slider.getInstance().initHardware();
		Pusher.getInstance();
		Grabber.getInstance();

		Rollers.getInstance();
		Rollers.getInstance().initHardware();
		Arm.getInstance();
		Arm.getInstance().initHardware();

		Odometry.getInstance().resetPosition();

		OI.getInstance();
		Compressor.getInstance();
		Gyro.getInstance();
		Limelight.getInstance();

		Notifier odometryNotifier = new Notifier(Odometry.getInstance());
		odometryNotifier.startPeriodic(0.005);
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
//		Odometry.getInstance().resetPosition();
//		Waypoint[] waypoints = new Waypoint[2];
//		waypoints[0] = new Waypoint(new Point2D.Double(0,0),0);
//		waypoints[1] = new Waypoint(new Point2D.Double(48,-24),0);
//		new Translate2dTrajectory(waypoints, PathType.CUBIC_HERMITE_PATH, true).start();
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
		new CalibrateArm().start();
		new CalibrateSlider().start();

	}
	
	@Override
	public void testPeriodic() {

	}
}
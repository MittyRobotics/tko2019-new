package frc.robot;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.command.Scheduler;

import frc.robot.autonomous.Odometry;
import frc.robot.autonomous.TestCommand;
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
import frc.robot.hatchpanel.commands.PushBackward;
import frc.robot.hatchpanel.commands.PushForward;
import frc.robot.hatchpanel.commands.Slide;
import frc.robot.hatchpanel.constants.SliderPosition;
import frc.robot.oi.OI;
import team1351.motionprofile.TrapezoidalMotionProfile;
import team1351.purepursuit.Waypoint;
import team1351.purepursuit.enums.PathType;

import java.awt.geom.Point2D;


public class Robot extends TimedRobot {

	Robot() {
		super(0.06);
	}
	
	@SuppressWarnings("ResultOfMethodCallIgnored")
	@Override
	public void robotInit() {

		long t = System.nanoTime();
		DriveTrain.getInstance();
		System.out.println("drive: " + ((System.nanoTime()-t)/1000000));
		t = System.nanoTime();
		DriveTrain.getInstance().initHardware();
		System.out.println("init: " + ((System.nanoTime()-t)/1000000));
		t = System.nanoTime();
		Shifter.getInstance();
		System.out.println("shift: " + ((System.nanoTime()-t)/1000000));
		t = System.nanoTime();
		Slider.getInstance();
		System.out.println("slider: " +((System.nanoTime()-t)/1000000));
		t = System.nanoTime();
		Slider.getInstance().initHardware();
		System.out.println("init: " + ((System.nanoTime()-t)/1000000));
		t = System.nanoTime();
		Pusher.getInstance();
		System.out.println("push: " +((System.nanoTime()-t)/1000000));
		t = System.nanoTime();
		Grabber.getInstance();
		System.out.println("grab: " +((System.nanoTime()-t)/1000000));
		t = System.nanoTime();

		Rollers.getInstance();
		System.out.println("roll: " + ((System.nanoTime()-t)/1000000));
		t = System.nanoTime();
		Rollers.getInstance().initHardware();
		System.out.println("init: " +((System.nanoTime()-t)/1000000));
		t = System.nanoTime();
		Arm.getInstance();
		System.out.println("arm: " + ((System.nanoTime()-t)/1000000));
		t = System.nanoTime();
		Arm.getInstance().initHardware();
		System.out.println("init: " + ((System.nanoTime()-t)/1000000));
		t = System.nanoTime();

		//Odometry.getInstance().resetPosition();
		System.out.println("odometry: " + ((System.nanoTime()-t)/1000000));
		t = System.nanoTime();

		OI.getInstance();
		System.out.println("oi: " +((System.nanoTime()-t)/1000000));
		t = System.nanoTime();
		Compressor.getInstance();
		System.out.println("compressor: " + ((System.nanoTime()-t)/1000000));
		t = System.nanoTime();
		Gyro.getInstance();
		System.out.println("gyro: " +((System.nanoTime()-t)/1000000));
		//Limelight.getInstance();

		//Notifier odometryNotifier = new Notifier(Odometry.getInstance());
		//odometryNotifier.startPeriodic(0.005);
	}
	
	@Override
	public void robotPeriodic() {
		Scheduler.getInstance().run();
		//System.out.println("asdf");

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
		//new TankDrive().start();
		//new TestCommand().start();
		//new Slide(SliderPosition.Middle).start();
		//new PushBackward().start();
		//Slider.getInstance().setSliderPosition(-500);
	}

	@Override
	public void teleopPeriodic() {
		//System.out.println(Slider.getInstance().getSliderPosition());
	}

	@Override
	public void testInit() {
		//new CalibrateArm().start();
		//new CalibrateSlider().start();
		//new Slide(SliderPosition.Middle).start();
		//TrapezoidalMotionProfile test = new TrapezoidalMotionProfile(2,8,4,12,0.06,true);
		//new TestCommand().start();
	}
	
	@Override
	public void testPeriodic() {
		//Rollers.getInstance().intake();   //WORKING
		//Arm.getInstance().manualAngle(0.2); //WORKING
		//Slider.getInstance().manualSlide(0.3); //WORKING
		//Pusher.getInstance().pushForward(); //NOT WORKING (pneumatics issue)
		Arm.getInstance().zeroEncoder(); //WORKING
		Slider.getInstance().zeroEncoder(); //WORKING

		//System.out.println("Running");
		//System.out.println("run");
	}
}
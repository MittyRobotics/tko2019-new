package com.amhsrobotics;

import com.amhsrobotics.autonomous.Odometry;
import com.amhsrobotics.autonomous.enums.StreamMode;
import com.amhsrobotics.autonomous.movement.commands.Translate2dTrajectory;
import com.amhsrobotics.autonomous.vision.Limelight;
import com.amhsrobotics.cargo.Arm;
import com.amhsrobotics.cargo.Rollers;
import com.amhsrobotics.climber.ClimbSubsystem;
import com.amhsrobotics.climber.commands.MoveClimber;
import com.amhsrobotics.climber.commands.ResetEncoder;
import com.amhsrobotics.climber.constants.ClimberPosition;
import com.amhsrobotics.climber.constants.TicksPerInch;
import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.drive.Shifter;
import com.amhsrobotics.hardware.Compressor;
import com.amhsrobotics.hardware.Gyro;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.command.Scheduler;

import com.amhsrobotics.hatchpanel.Grabber;
import com.amhsrobotics.hatchpanel.Pusher;
import com.amhsrobotics.hatchpanel.Slider;
import com.amhsrobotics.oi.OI;
import com.amhsrobotics.purepursuit.Waypoint;
import com.amhsrobotics.purepursuit.enums.PathType;

import java.awt.geom.Point2D;


public class Robot extends TimedRobot {

	Robot() {
		super(0.1);
	}

	@SuppressWarnings("ResultOfMethodCallIgnored")
	@Override
	public void robotInit() {
		ClimbSubsystem.getInstance();
		ClimbSubsystem.getInstance().initHardware();
		OI.getInstance();
//		long t = System.nanoTime();
//		DriveTrain.getInstance();
//		System.out.println("drive: " + ((System.nanoTime()-t)/1000000));
//		t = System.nanoTime();
//		DriveTrain.getInstance().initHardware();
//		System.out.println("init: " + ((System.nanoTime()-t)/1000000));
//		t = System.nanoTime();
//		Shifter.getInstance();
//		System.out.println("shift: " + ((System.nanoTime()-t)/1000000));
//		t = System.nanoTime();
//		Slider.getInstance();
//		System.out.println("slider: " +((System.nanoTime()-t)/1000000));
//		t = System.nanoTime();
//		Slider.getInstance().initHardware();
//		System.out.println("init: " + ((System.nanoTime()-t)/1000000));
//		t = System.nanoTime();
//		Pusher.getInstance();
//		System.out.println("push: " +((System.nanoTime()-t)/1000000));
//		t = System.nanoTime();
//		Grabber.getInstance();
//		System.out.println("grab: " +((System.nanoTime()-t)/1000000));
//		t = System.nanoTime();
//
//		Rollers.getInstance();
//		System.out.println("roll: " + ((System.nanoTime()-t)/1000000));
//		t = System.nanoTime();
//		Rollers.getInstance().initHardware();
//		System.out.println("init: " +((System.nanoTime()-t)/1000000));
//		t = System.nanoTime();
//		Arm.getInstance();
//		System.out.println("arm: " + ((System.nanoTime()-t)/1000000));
//		t = System.nanoTime();
//		Arm.getInstance().initHardware();
//		System.out.println("init: " + ((System.nanoTime()-t)/1000000));
//		t = System.nanoTime();
//
//		Odometry.getInstance().resetPosition();
//		System.out.println("odometry reset: " + ((System.nanoTime()-t)/1000000));
//		t = System.nanoTime();
//
//		OI.getInstance();
//		System.out.println("oi: " +((System.nanoTime()-t)/1000000));
//		t = System.nanoTime();
//		Compressor.getInstance();
//		Compressor.getInstance().start();
//		System.out.println("compressor: " + ((System.nanoTime()-t)/1000000));
//		t = System.nanoTime();
//		Gyro.getInstance();
//		System.out.println("gyro: " +((System.nanoTime()-t)/1000000));
//		t = System.nanoTime();
//		Limelight.getInstance();
//		Limelight.getInstance().enableVisionMode();
//		Limelight.getInstance().setStreamMode(StreamMode.Secondary);
//		System.out.println("Limelight: " + ((System.nanoTime()-t)/1000000));
//
//		t = System.nanoTime();
//		Notifier odometryNotifier = new Notifier(Odometry.getInstance());
//		odometryNotifier.startPeriodic(0.005);
//		System.out.println("odometry notifier start: " + ((System.nanoTime()-t)/1000000));
	}

	@Override
	public void robotPeriodic() {
		Scheduler.getInstance().run();
//		Compressor.getInstance().start();
//		System.out.println("asdf");

	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic(){
	}

	@Override
	public void autonomousInit() {
//		new AimAssist().start();
//		new PushBackward().start();
//		Odometry.getInstance().resetPosition();
//		Waypoint[] waypoints = new Waypoint[2];
//		waypoints[0] = new Waypoint(new Point2D.Double(0,0),0);
//		waypoints[1] = new Waypoint(new Point2D.Double(-48,0),0);
//		new Translate2dTrajectory(waypoints, PathType.CUBIC_HERMITE_PATH, true).start();
//		new AutoSlider().start();
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
		Scheduler.getInstance().run();
//		System.out.println(ClimbSubsystem.getInstance().leftTalon.getSelectedSensorPosition()/ TicksPerInch.CLIMBER_TPI);
		//Arm.getInstance().manualAngle(0.2);
		//System.out.println(Arm.getInstance().getArmPosition());

	}

	@Override
	public void testInit() {
//		ClimbSubsystem.getInstance();
		ClimbSubsystem.getInstance().zeroEncoder();
//		System.out.println(ClimbSubsystem.getInstance().leftTalon.getSelectedSensorPosition());

//		OI.getInstance();
		//new CalibrateArm().start();
		//new CalibrateSlider().start();
		//new Slide(SliderPosition.Middle).start();
		//TrapezoidalMotionProfile test = new TrapezoidalMotionProfile(2,8,4,12,0.06,true);
		//new TestCommand().start();
//		Arm.getInstance().zeroEncoder(); //WORKING

//		Slider.getInstance().zeroEncoder(); //WORKING
//		new ResetEncoder().start();
	}
	
	@Override
	public void testPeriodic() {
//		ClimbSubsystem.getInstance().safety();
//		System.out.print("Limit 0: ");
//		System.out.println(ClimbSubsystem.getInstance().getLimit0());
//		System.out.print("Limit 1: ");
//		System.out.println(ClimbSubsystem.getInstance().getLimit1());
//		if (!ClimbSubsystem.getInstance().safety()) {
//			ClimbSubsystem.getInstance().setSpeedSlider(OI.getInstance().getJoystick1().getY());
//		}
//		DriveTrain.getInstance().tankVelocity(50,50);
		//System.out.println(Slider.getInstance().getSliderSensor());
		//Rollers.getInstance().intake();   //WORKING
	//	System.out.println(Arm.getInstance().getArmPosition());
		//Slider.getInstance().manualSlide(0.3); //WORKING
//		Pusher.getInstance().pushForward(); //NOT WORKING (pneumatics issue)


		//System.out.println("Running");
		//System.out.println("run");
	}
}
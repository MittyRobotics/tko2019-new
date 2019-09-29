package com.amhsrobotics;

import com.amhsrobotics.autonomous.Odometry;
import com.amhsrobotics.autonomous.constants.AutoConstants;
import com.amhsrobotics.autonomous.enums.StreamMode;
import com.amhsrobotics.autonomous.modes.CargoShipFrontHatchAuto;
import com.amhsrobotics.autonomous.movement.RateLimiter;
import com.amhsrobotics.autonomous.movement.commands.AimAssist;
import com.amhsrobotics.autonomous.movement.commands.AutoSlider;
import com.amhsrobotics.autonomous.movement.commands.Translate2dTrajectory;
import com.amhsrobotics.autonomous.vision.Limelight;
import com.amhsrobotics.autonomous.vision.VisionScore;
import com.amhsrobotics.cargo.Arm;
import com.amhsrobotics.cargo.Rollers;
import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.drive.Shifter;
import com.amhsrobotics.drive.commands.TankDrive;
import com.amhsrobotics.hardware.Compressor;
import com.amhsrobotics.hardware.Gyro;
import com.amhsrobotics.purepursuit.Waypoint;
import com.amhsrobotics.purepursuit.enums.PathType;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.command.Scheduler;

import com.amhsrobotics.hatchpanel.Grabber;
import com.amhsrobotics.hatchpanel.Pusher;
import com.amhsrobotics.hatchpanel.Slider;
import com.amhsrobotics.oi.OI;

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

		Odometry.getInstance().resetPosition();
		System.out.println("odometry reset: " + ((System.nanoTime()-t)/1000000));
		t = System.nanoTime();

		OI.getInstance();
		System.out.println("oi: " +((System.nanoTime()-t)/1000000));
		t = System.nanoTime();
		Compressor.getInstance();
		Compressor.getInstance().start();
		System.out.println("compressor: " + ((System.nanoTime()-t)/1000000));
		t = System.nanoTime();
		Gyro.getInstance();
		System.out.println("gyro: " +((System.nanoTime()-t)/1000000));
		t = System.nanoTime();
		Limelight.getInstance();
		Limelight.getInstance().enableVisionMode();
		Limelight.getInstance().setStreamMode(StreamMode.Secondary);
		System.out.println("Limelight: " + ((System.nanoTime()-t)/1000000));

		t = System.nanoTime();
		Notifier odometryNotifier = new Notifier(Odometry.getInstance());
		odometryNotifier.startPeriodic(0.06);
		System.out.println("odometry notifier start: " + ((System.nanoTime()-t)/1000000));

		RateLimiter.getInstance().setPercentOutputParameters(.1 ,1);
		RateLimiter.getInstance().setVelocityParameters(AutoConstants.DRIVE_VELOCITY_CONSTRAINTS.getMaxAcceleration(),AutoConstants.DRIVE_VELOCITY_CONSTRAINTS.getMaxVelocity());
	}

	@Override
	public void robotPeriodic() {
		Scheduler.getInstance().run();
		Compressor.getInstance().start();
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
		//new CargoShipFrontHatchAuto(0).start();
		new Translate2dTrajectory(new Waypoint[]{new Waypoint(new Point2D.Double(0,0), 0), new Waypoint(new Point2D.Double(48,12 ),0)},20,20,100,PathType.CUBIC_HERMITE_PATH,0,0,false,false).start();

	}
	@Override
	public void autonomousPeriodic() {
		//System.out.println("left: "+DriveTrain.getInstance().getLeftEncoder() + " right: " + DriveTrain.getInstance().getRightEncoder());
	}

	@Override
	public void teleopInit() {
		new TankDrive().start();
		//new TestCommand().start();
		//new Slide(SliderPosition.Middle).start();
		//new PushBackward().start();
		//Slider.getInstance().setSliderPosition(-500);
	}

	@Override
	public void teleopPeriodic() {
		//Arm.getInstance().manualAngle(0.2);
		//System.out.println(Arm.getInstance().getArmPosition());
//		System.out.println(Rollers.getInstance().ballDetected());
	}

	@Override
	public void testInit() {


		//new CalibrateArm().start();
		//new CalibrateSlider().start();
		//new Slide(SliderPosition.Middle).start();
		//TrapezoidalMotionProfile test = new TrapezoidalMotionProfile(2,8,4,12,0.06,true);
		//new TestCommand().start();
		Arm.getInstance().zeroEncoder(); //WORKING

		Slider.getInstance().zeroEncoder(); //WORKING

	}
	
	@Override
	public void testPeriodic() {
//		DriveTrain.getInstance().tankVelocity(50,50);
		//System.out.println(Slider.getInstance().getSliderSensor());
		//Rollers.getInstance().intake();   //WORKING
	//	System.out.println(Arm.getInstance().getArmPosition());
		//Slider.getInstance().manualSlide(0.3); //WORKING
		//Pusher.getInstance().pushForward(); //NOT WORKING (pneumatics issue)


		//System.out.println("Running");
		//System.out.println("run");
	}
}
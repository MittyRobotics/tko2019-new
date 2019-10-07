package com.amhsrobotics;

import com.amhsrobotics.autonomous.Odometry;
import com.amhsrobotics.autonomous.enums.StreamMode;
import com.amhsrobotics.autonomous.modes.BR_CargoShipFrontHatchAuto;
import com.amhsrobotics.autonomous.vision.Limelight;
import com.amhsrobotics.cargo.Arm;
import com.amhsrobotics.cargo.Rollers;
import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.drive.Shifter;
import com.amhsrobotics.hardware.Compressor;
import com.amhsrobotics.hardware.Gyro;
import com.amhsrobotics.hatchpanel.Grabber;
import com.amhsrobotics.hatchpanel.Pusher;
import com.amhsrobotics.hatchpanel.Slider;
import com.amhsrobotics.oi.OI;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;


public class Robot extends TimedRobot {
	private Command autonCommand;
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
		Slider.getInstance().setSliderPosition(0);
		Arm.getInstance().setArmPosition(0);

	}

	@Override
	public void robotPeriodic() {
		Scheduler.getInstance().run();
		Compressor.getInstance().start();
	}

	@Override
	public void disabledInit() {
	}



	@Override
	public void disabledPeriodic(){
	}

	@Override
	public void autonomousInit() {
		Limelight.getInstance().setStreamMode(StreamMode.Secondary);
		Limelight.getInstance().setPipeline(0);


		autonCommand = new BR_CargoShipFrontHatchAuto();
		autonCommand.start();


	}


	@Override
	public void autonomousPeriodic() {
		if(OI.getInstance().getJoystick2().getRawButtonPressed(9)){
			autonCommand.cancel();
		}
	}

	@Override
	public void teleopInit() {
		Limelight.getInstance().setPipeline(1);
		if(autonCommand != null){
			autonCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {

	}

	@Override
	public void testInit() {
		Slider.getInstance().zeroEncoder();
	}
	
	@Override
	public void testPeriodic() {

	}
}
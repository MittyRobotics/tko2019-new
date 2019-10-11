package com.amhsrobotics;

import com.amhsrobotics.drive.DriveTrain;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;


public class Robot extends TimedRobot {
	Robot() {
		super(0.06);
	}

	@SuppressWarnings("ResultOfMethodCallIgnored")
	@Override
	public void robotInit() {
		DriveTrain.getInstance();
		DriveTrain.getInstance().initHardware();
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

	}
}
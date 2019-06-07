package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.autonomous.vision.Limelight;
import frc.robot.drive.DriveTrain;
import frc.robot.drive.Shifter;
import frc.robot.hardware.Compressor;
import frc.robot.hardware.Gyro;
import frc.robot.oi.OI;

public class Robot extends TimedRobot {
	
	Robot() {
		super(0.06);
	}
	
	@SuppressWarnings("ResultOfMethodCallIgnored")
	@Override
	public void robotInit() {
		DriveTrain.getInstance();
		Shifter.getInstance();
		Compressor.getInstance();
		Gyro.getInstance();
		OI.getInstance();
	}
	
	@Override
	public void robotPeriodic() {
	
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
		Scheduler.getInstance().run();
	}
	
	@Override
	public void testInit() {
	
	}
	
	@Override
	public void testPeriodic() {

	}
}
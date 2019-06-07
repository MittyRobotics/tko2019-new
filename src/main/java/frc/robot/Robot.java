package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.autonomous.movement.commands.VisionAlignment;
import frc.robot.autonomous.vision.Limelight;
import frc.robot.drive.DriveTrain;
import frc.robot.drive.Shifter;
import frc.robot.drive.constants.GearState;
import frc.robot.hardware.Compressor;
import frc.robot.hardware.Gyro;
import frc.robot.oi.OI;

public class Robot extends TimedRobot {
	
	Robot() {
		super(0.04);
	}
	
	@SuppressWarnings("ResultOfMethodCallIgnored")
	@Override
	public void robotInit() {
		DriveTrain.getInstance();
		DriveTrain.getInstance().initHardware();
		Shifter.getInstance();
		OI.getInstance();
		Compressor.getInstance();
		Gyro.getInstance();
		Limelight.getInstance();
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
		Compressor.getInstance().start();
	}
	
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		Limelight.getInstance().enableVisionMode();
		Limelight.getInstance().updateLimelightValues();
		Limelight.getInstance().printValues();
		System.out.println(DriveTrain.getInstance().getMotorOutput());
		if(!Limelight.getInstance().isHasTarget()){
			Shifter.getInstance().shiftGear(GearState.Low);
		}
	}
	
	@Override
	public void testInit() {

	}
	
	@Override
	public void testPeriodic() {
		Limelight.getInstance().enableVisionMode();
		Limelight.getInstance().updateLimelightValues();
		Limelight.getInstance().printValues();
	}
}
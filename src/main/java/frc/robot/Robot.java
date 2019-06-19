package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.autonomous.movement.commands.MotionProfileTranslate;
import frc.robot.autonomous.vision.Limelight;
import frc.robot.drive.DriveTrain;
import frc.robot.hardware.Compressor;
import frc.robot.hardware.Gyro;

public class Robot extends TimedRobot {
	
	Robot() {
		super(0.05);
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
		new MotionProfileTranslate(12,1,3,0.5,0.05);
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
		//Limelight.getInstance().printValues();
		//System.out.println(DriveTrain.getInstance().getMotorOutput());
	}
	
	@Override
	public void testInit() {

	}
	
	@Override
	public void testPeriodic() {
		Limelight.getInstance().enableVisionMode();
		Limelight.getInstance().updateLimelightValues();
		Limelight.getInstance().printValues();
        //System.out.println(Gyro.getInstance().getAngle() + " gyro");
	}
}
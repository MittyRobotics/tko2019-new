package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.autonomous.movement.commands.VisionAlignment;
import frc.robot.autonomous.vision.Limelight;
import frc.robot.drive.DriveTrain;
import frc.robot.drive.Shifter;
import frc.robot.drive.constants.GearState;
import frc.robot.drive.constants.TicksPerInch;
import frc.robot.hardware.Compressor;
import frc.robot.hardware.Gyro;
import frc.robot.motion_profile.TrapazoidalMotionProfile;
import frc.robot.oi.OI;

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

	double i;
	@Override
	public void autonomousInit() {
		i = 0;
		DriveTrain.getInstance().resetEncoders();
	}
	TrapazoidalMotionProfile motionProfile = new TrapazoidalMotionProfile(24,50,48,200); //inches
	double prevPosition = 0;
	@Override
	public void autonomousPeriodic() {
		i+=0.05;
		//System.out.println(i);
		double position = motionProfile.getFrameAtTime(i).getPosition();
		double velocity = motionProfile.getFrameAtTime(i).getVelocity();
		double acceleration = motionProfile.getFrameAtTime(i).getAcceleration();
		double t = motionProfile.getFrameAtTime(i).getT();

		double currentPosition = ((DriveTrain.getInstance().getLeftEncoder() + DriveTrain.getInstance().getRightEncoder())  /2) / TicksPerInch.DRIVE;
		if(position == 0){
			currentPosition = 0;
		}
		DriveTrain.getInstance().translation(position - currentPosition,1);
		//System.out.println(DriveTrain.getInstance().getLeftEncoder() + "  " + DriveTrain.getInstance().getRightEncoder());
		//System.out.println("Feedforward: position: " + position + "in velocity: " + velocity + "in/s acceleration: " + acceleration + "in/s time: " +  t + "s current time: " + i + "s");
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
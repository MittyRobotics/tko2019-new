package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.autonomous.constants.VisionConstants;
import frc.robot.autonomous.vision.Limelight;
import frc.robot.drive.DriveTrain;
import frc.robot.hardware.Gyro;

import java.awt.geom.Point2D;

public class VisionAlignment extends Command {

	private double distance;
	private double targetYaw;
	private double yaw;

	private double deltaHighTimer;
	private double lostTargetTimer;
	private double reachedTargetTimer;

	private double deltaHighCooldown = 10;
	private double lostTargetCooldown = 10;
	private double reachedTargetCooldown = 10;

	private double prevYaw;
	private double prevDist;

	double DESIRED_DISTANCE = 10;

	public VisionAlignment(){
		super("Vision Alignment");
		requires(DriveTrain.getInstance());
	}

	protected void initialize() {

	}

	protected void execute() {

		updateValues();

		checkForLostTarget();
		checkForHighDelta();
		checkForReachedTarget();


		double drive = 0;
		double turn = 0;
		double skew = 0;


		double MAX_SPEED = 0.6;
		double TURN_DRIVE_RATIO = 0.65;
		double TURN_SKEW_RATIO = 0.5;
		double DRIVE_K = 0.021;
		double TURN_K = 0.025;
		double SKEW_K = 0.04;

		if(distance > DESIRED_DISTANCE){
			drive = Math.min(MAX_SPEED * (1-TURN_DRIVE_RATIO), distance/DRIVE_K);
		}
		else{
			drive = 0;
		}

		turn = Math.min((MAX_SPEED * (TURN_DRIVE_RATIO) * TURN_SKEW_RATIO), Math.max(-(MAX_SPEED * (TURN_DRIVE_RATIO) * TURN_SKEW_RATIO), yaw / TURN_K));

		skew = Math.min((MAX_SPEED * (TURN_DRIVE_RATIO) * (1-TURN_SKEW_RATIO)), Math.max(-(MAX_SPEED * (TURN_DRIVE_RATIO) * (1-TURN_SKEW_RATIO)), targetYaw / SKEW_K));



		double left = -drive + turn + skew;
		double right = -drive - turn - skew;

		DriveTrain.getInstance().tankDrive(left, right);


		prevYaw = yaw;
		prevDist = distance;

	}

	private void checkForHighDelta(){
		if(Math.abs(yaw - prevYaw) > 8 || Math.abs(distance - prevDist) > 10){
			System.out.println("Vision robot alignment target position delta is too high. Checking for end: " + deltaHighTimer + "/" + deltaHighCooldown);
			deltaHighTimer++;
		}
		else{
			deltaHighTimer = 0;
		}
	}

	private void checkForLostTarget(){
		if(!Limelight.getInstance().isHasTarget()){
			System.out.println("Vision robot alignment target has been lost. Checking for end: " + lostTargetTimer + "/" + lostTargetCooldown);
			lostTargetTimer ++;
		}
		else{
			lostTargetTimer = 0;
		}
	}

	private void checkForReachedTarget(){
		if(distance < DESIRED_DISTANCE){
			System.out.println("Vision robot alignment has reached the target. Checking for end: " + reachedTargetTimer + "/" + reachedTargetCooldown);
			reachedTargetTimer ++;
		}
		else{
			reachedTargetTimer = 0;
		}
	}

	private void updateValues(){
		if(Limelight.getInstance().isHasTarget()){
			distance = Limelight.getInstance().getTargetYFast();
			targetYaw = Limelight.getInstance().getTargetYawFast();
			yaw = Limelight.getInstance().getXAngle();
		}
	}


	protected void end() {
		if(deltaHighTimer >= deltaHighCooldown){
			System.out.println("Vision robot alignment ended unexpectedly. Cause: Target position delta is too high");
		}
		else if(lostTargetTimer >= lostTargetCooldown){
			System.out.println("Vision robot alignment ended unexpectedly. Cause: Lost target");
		}
		else if(reachedTargetTimer >= reachedTargetCooldown){
			System.out.println("Vision robot alignment has ended successfully! Cause: reached target");
		}
		else{
			System.out.println("Vision robot alignment ended unexpectedly. Cause: Unknown");
		}
	}

	protected void interrupted() {
		end();
	}

	protected boolean isFinished() {
		return deltaHighTimer > deltaHighCooldown || lostTargetTimer > lostTargetCooldown || reachedTargetTimer > reachedTargetCooldown;
	}
}

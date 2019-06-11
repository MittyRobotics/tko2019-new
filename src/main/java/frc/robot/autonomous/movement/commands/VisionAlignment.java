package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.autonomous.constants.VisionConstants;
import frc.robot.autonomous.vision.Limelight;
import frc.robot.drive.DriveTrain;
import frc.robot.drive.constants.PID;
import frc.robot.hardware.Gyro;

public class VisionAlignment extends Command {

	private double MAX_SPEED;

	private int lostTargetCooldown = 50;
	private int lostTargetCount = 0;

	private int endVisionCount = 0;
	private int endVisionTime = 10;




	/**
	 * Initializes command with a name "VisionAlignment" and the required subsystem class that will be used, {@link DriveTrain}
	 *
	 * @param maxSpeed  the maximum speed
	 */
	public VisionAlignment(double maxSpeed) {
		super("VisionAlignment");
		requires(DriveTrain.getInstance());
		this.MAX_SPEED = maxSpeed;
	}

	/**
	 * The initialize function is called at the initialization stage of the command.
	 * <p>
	 * Here the {@link Limelight} is set to vision mode, turning the exposure down on the camera and enabling
	 * the LEDs for optimum vision tracking.
	 */
	@Override
	public void initialize() {
		Limelight.getInstance().enableVisionMode();
		Limelight.getInstance().setPipeline(0);
	}

	/**
	 * This method is called periodically (about every 20ms) and does the work of the command.
	 */
	double distance = 0, xOffset = 0, targetAngle = 0, angle = 0;

	@Override
	public void execute() {

		if(!Limelight.getInstance().isHasTarget()){
			lostTargetCount ++;
			System.out.println("Lost sight of target " + lostTargetCount + "/" + lostTargetCooldown);
		}
		else{
			lostTargetCount = 0;
			distance = Limelight.getInstance().getTargetZFast();
			xOffset = Limelight.getInstance().getTargetXFast();
			targetAngle = Limelight.getInstance().getTargetYawFast();
			angle = Limelight.getInstance().getXAngle();
		}

		double drive, turn, skew;

		double DRIVE_K = 0.26;
		double DESIRED_DISTANCE = 5;
		drive = Math.min((DESIRED_DISTANCE - distance) * DRIVE_K, MAX_SPEED);

		double TURN_K = 0.03;
		turn = angle * TURN_K;

		double SKEW_K = 0.015;
		skew = targetAngle * SKEW_K;

		turn = turn + skew;

		drive = -drive;

		DriveTrain.getInstance().tankDrive(drive + turn, drive - turn);

		if (Math.abs(angle) <= 1 && Limelight.getInstance().isHasTarget() && distance <= 1) { //TODO: Tune thresholds
			endVisionCount++;
			System.out.println("Testing for end of vision sequence: " + endVisionCount + "/" + endVisionTime);
		}
	}

	/**
	 * This method is called at the end of the command.
	 * <p>
	 * Here we want to reset the wheel speeds to 0. We also put the {@link Limelight} back into driver mode because
	 * the vision tracking is completed.
	 */
	@Override
	public void end() {
		DriveTrain.getInstance().tankDrive(0, 0);
		Limelight.getInstance().enableDriverMode();
		System.out.println("Ended VisionAlignment.java command");
	}

	/**
	 * This method is called when the command is interrupted, therefore ending the command.
	 */
	@Override
	public void interrupted() {
		end();
	}

	/**
	 * This returns whether or not the command has finished.
	 *
	 * @return
	 */
	@Override
	protected boolean isFinished() {
		return endVisionCount > endVisionTime || lostTargetCount > lostTargetCooldown;
	}
}
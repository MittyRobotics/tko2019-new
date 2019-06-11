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

	private double maxSpeed;
	double startingDriveSpeed = 0;
	double startingTurnSpeed = 0;

	int lostTargetCooldown = 50;
	int lostTargetCount = 0;

	private int endVisionCount = 0;
	int endVisionTime = 10;

	boolean pipelineSet = false;




	/**
	 * Initializes command with a name "VisionAlignment" and the required subsystem class that will be used, {@link DriveTrain}
	 *
	 * @param maxSpeed  the maximum speed
	 */
	public VisionAlignment(double maxSpeed) {
		super("VisionAlignment");
		requires(DriveTrain.getInstance());
		this.maxSpeed = maxSpeed;
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
		Limelight.getInstance().setPipeline(1);
	}

	/**
	 * This method is called periodically (about every 20ms) and does the work of the command.
	 */
	@Override
	public void execute() {
		if(!Limelight.getInstance().isHasTarget()){
			lostTargetCount ++;
			System.out.println("Lost sight of target " + lostTargetCount + "/" + lostTargetCooldown);
		}
		else{
			lostTargetCount = 0;
		}

		//Get values from limelight
		double distance = Limelight.getInstance().getCamZ(); //Distance from robot to target (perpendicular to target)
		double xOffset = Limelight.getInstance().getCamX(); //Perpendicular distance from perpendicular distance to target to robot
		double targetAngle = Limelight.getInstance().getCamYaw(); //Robot's rotation relative to target (target rotation is at (0,0,0))
		double angle = Limelight.getInstance().getXAngle(); //X angle to target relative to robot's forward vector

		double drive = 0;
		double turn = 0;



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
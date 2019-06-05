package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.autonomous.constants.VisionConstants;
import frc.robot.autonomous.vision.Limelight;
import frc.robot.drive.DriveTrain;

public class VisionAlignmentTest extends Command {
	
	private double maxSpeed; //percent output
	private double baseSpeed; //percent output
	private double threshold = 1; //angle threshold for when the robot is pointing towards the target
	private boolean continuous; //whether or not the command should continue running after it is pointing to the target, used if target or robot is continuously moving
	private int count = 0;
	
	/**
	 * Initializes command with a name "VisionAlignmentTest" and the required subsystem class that will be used, {@link DriveTrain}
	 *
	 * @param maxSpeed   the maximum speed at which the robot will be rotating at
	 * @param baseSpeed  the base speed at which the robot will be rotating at
	 * @param continuous if the command should continue running after the robot is aligned for continuous tracking (ex: moving target)
	 */
	public VisionAlignmentTest(double maxSpeed, double baseSpeed, boolean continuous) {
		super("VisionAlignmentTest");
		requires(DriveTrain.getInstance());
		this.maxSpeed = maxSpeed;
		this.baseSpeed = baseSpeed;
		this.continuous = continuous;
	}
	
	/**
	 * The initialize function is called at the initialization stage of the command.
	 */
	@Override
	public void initialize() {
		//"Everybody needs a friend" -Bob Ross
	}
	
	/**
	 * This method is called periodically (about every 20ms) and does the work of the command.
	 * <p>
	 * This is recieving the X angle value from the {@link Limelight} API to determine which direction and at what
	 * speed to rotate the robot. The robot starts turning at a base speed, and then a mapped value of the x angle
	 * between 0 and maxSpeed-baseSpeed is added. The end result is the robot turning faster when the target is
	 * further away from the center, and slower when the target is closer to the center.
	 */
	@Override
	public void execute() {
		double x = Limelight.getInstance().getXAngle();
		double mappedX = (Math.abs(x) / 29.8) * (maxSpeed - baseSpeed);
		if (x > threshold) {
			DriveTrain.getInstance().tankDrive((mappedX + baseSpeed), -((mappedX) + baseSpeed));
			count = 0;
		} else if (x < -threshold) {
			DriveTrain.getInstance().tankDrive(-(mappedX + baseSpeed), ((mappedX) + baseSpeed));
			count = 0;
		} else {
			DriveTrain.getInstance().tankDrive(0, 0);
			count++;
		}
	}
	
	/**
	 * This method is called at the end of the command.
	 * <p>
	 * Here we want to reset the wheel speeds to 0.
	 */
	@Override
	public void end() {
		DriveTrain.getInstance().tankDrive(0, 0);
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
	 * <p>
	 * In this case, we want to return if the robot is pointing towards the target. If continuous is enabled,
	 * we want to always return false, since the command should continue running.
	 *
	 * @return if the robot is aligned with the target
	 */
	@Override
	protected boolean isFinished() {
		if (continuous) {
			return false;
		} else {
			return count > 20;
		}
	}
}
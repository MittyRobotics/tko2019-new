package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.autonomous.constants.VisionConstants;
import frc.robot.autonomous.vision.Limelight;
import frc.robot.drive.DriveTrain;
import frc.robot.drive.commands.GearShift;
import frc.robot.drive.constants.GearState;
import org.w3c.dom.html.HTMLImageElement;

public class VisionAlignmentTest extends Command {
	
	private double driveMaxSpeed; //percent output
	private double turnMaxSpeed;
	double newTurnVal;
	double newDriveVal;
	private double baseSpeed; //percent output
	private double threshold = 1; //angle threshold for when the robot is pointing towards the target
	private boolean continuous; //whether or not the command should continue running after it is pointing to the target, used if target or robot is continuously moving
	private int count = 0;
	
	/**
	 * Initializes command with a name "VisionAlignmentTest" and the required subsystem class that will be used, {@link DriveTrain}
	 *
	 * @param driveMaxSpeed   the maximum speed at which the robot will be rotating at
	 * @param baseSpeed  the base speed at which the robot will be rotating at
	 * @param continuous if the command should continue running after the robot is aligned for continuous tracking (ex: moving target)
	 */
	public VisionAlignmentTest(double driveMaxSpeed, double turnMaxSpeed, double baseSpeed, boolean continuous) {
		super("VisionAlignmentTest");
		requires(DriveTrain.getInstance());
		this.driveMaxSpeed = driveMaxSpeed;
		this.baseSpeed = baseSpeed;
		this.continuous = continuous;
		this.turnMaxSpeed = turnMaxSpeed;
		this.newTurnVal = turnMaxSpeed * 2/3;
		this.newDriveVal = driveMaxSpeed * 2/3;
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
	 * <p>
	 * This is receiving the X angle value from the {@link Limelight} API to determine which direction and at what
	 * speed to rotate the robot. The robot starts turning at a base speed, and then a mapped value of the x angle
	 * between 0 and maxSpeed-baseSpeed is added. The end result is the robot turning faster when the target is
	 * further away from the center, and slower when the target is closer to the center.
	 */
	@Override
	public void execute() {

		double x = Limelight.getInstance().getXAngle();
		double turnValue;
		double mappedX = (Math.abs(x) / 29.8) * (turnMaxSpeed - baseSpeed);
		if(!Limelight.getInstance().isHasTarget()){
			turnValue = 0;
		}
		else if (x > threshold) {
			turnValue = mappedX + baseSpeed;
			count = 0;
		} else if (x < -threshold) {
			turnValue = -(mappedX + baseSpeed);
			count = 0;
		} else {
			turnValue = 0;
			count++;
		}

		double area = Limelight.getInstance().getArea() / 50;
		double driveValue;

		if(Limelight.getInstance().getArea() > 7){
			Limelight.getInstance().setPipeline(0);
			System.out.println("Switched To Pipeline 0");
			turnMaxSpeed = newTurnVal;

			driveMaxSpeed = newDriveVal;
		}

		if(area > 0.8 || !Limelight.getInstance().isHasTarget()){
			driveValue = 0;
			new GearShift(GearState.Low);
		} else {
			if(driveMaxSpeed < driveMaxSpeed/area){
				driveValue = -driveMaxSpeed;
			} else {
				driveValue = -driveMaxSpeed / area;
			}
		}
		DriveTrain.getInstance().tankDrive(driveValue + turnValue, driveValue - turnValue);
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
		System.out.println("EXITSADFJHASDKJLFHASJKDLFHAJKSLDHFJKLAHDFJKLAHSDKLFJHASKJLDFHJKALSDHFKJLASDHFJKLASDHFJKLASHDJKFLHJK");
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
			return !DriverStation.getInstance().isEnabled();
		} else {
			return count > 20;
		}
	}
}
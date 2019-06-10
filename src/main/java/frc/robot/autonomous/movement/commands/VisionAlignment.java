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

	private double maxDriveSpeed; //percent output
	private double maxTurnSpeed;
	private double stoppingDistance;

	private int count = 0;

	//Empty PIDOutput
	private PIDOutput pidOutput = new PIDOutput() {
		@Override
		public void pidWrite(double output) {
		}
	};
	private PIDController turnController = new PIDController(PID.VISION_TURN[0], PID.VISION_TURN[1], PID.VISION_TURN[2], Gyro.getInstance(), pidOutput);

	/**
	 * Initializes command with a name "VisionAlignment" and the required subsystem class that will be used, {@link DriveTrain}
	 *
	 * @param maxTurnSpeed  the maximum speed at which the robot will be rotating at
	 * @param maxDriveSpeed the maximum speed at which the robot will be translating at
	 */
	public VisionAlignment(double maxDriveSpeed, double maxTurnSpeed, double stoppingDistance) {
		super("VisionAlignment");
		requires(DriveTrain.getInstance());
		this.maxDriveSpeed = maxDriveSpeed;
		this.maxTurnSpeed = maxTurnSpeed;
		this.stoppingDistance = stoppingDistance;
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
		turnController.setInputRange(0, 360);
		turnController.setOutputRange(-maxTurnSpeed, maxTurnSpeed);
	}

	/**
	 * This method is called periodically (about every 20ms) and does the work of the command.
	 */
	@Override
	public void execute() {
		//Get values from limelight
		double distance = Limelight.getInstance().getCamZ();
		double xOffset = Limelight.getInstance().getCamX();
		double skew = Limelight.getInstance().getCamYaw();
		double angle = Limelight.getInstance().getXAngle();


		int SLOW_RANGE = 3; //feet

		////////Start Math////////
		double d = Math.sqrt((xOffset * xOffset) + (distance * distance));

		double newDistance = distance - stoppingDistance;

		double d1 = Math.sqrt((xOffset * xOffset) + ((newDistance) * (newDistance)));

		double x = d * Math.sin(Math.toRadians(angle));

		double x1 = stoppingDistance * Math.sin(Math.toRadians(skew));

		double x2 = x1 + x;

		double a2 = Math.toDegrees(Math.asin(x2 / d1));

		if (xOffset < 0 || angle < 0) {
			a2 = -a2;
		}
		////////End Math////////

		////////Start Movement Calculations////////
		double gyroAngle = Gyro.getInstance().getAngle();

		double drive;
		double turn = 0;

		drive = Math.min((newDistance / (12 * SLOW_RANGE)) * maxDriveSpeed, maxDriveSpeed);

		double finalAngleSetpoint = a2 + skew; //TODO: Add variable ratio if needed

		if (gyroAngle + finalAngleSetpoint < 0) {
			gyroAngle = gyroAngle + finalAngleSetpoint + 360;
		} else if (gyroAngle + finalAngleSetpoint >= 360) {
			gyroAngle = gyroAngle + finalAngleSetpoint - 360;
		} else {
			gyroAngle = gyroAngle + finalAngleSetpoint;
		}

		turnController.setSetpoint(gyroAngle);

		turn = turnController.get(); //Get latest calculated output
		/////////End Movement Calculations////////

		DriveTrain.getInstance().tankDrive(drive + turn, drive - turn);

		if (Math.abs(angle) <= 1 && newDistance <= 1) {
			count++;
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
		turnController.disable();
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
		return count > 10;
	}
}
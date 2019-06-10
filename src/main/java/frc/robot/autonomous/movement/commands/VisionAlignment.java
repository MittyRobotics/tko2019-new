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
		double distance = Limelight.getInstance().getCamZ(); //Distance from robot to target (perpendicular to target)
		double xOffset = Limelight.getInstance().getCamX(); //Perpendicular distance from perpendicular distance to target to robot
		double skew = Limelight.getInstance().getCamYaw(); //Robot's rotation relative to target (target rotation is at (0,0,0))
		double angle = Limelight.getInstance().getXAngle(); //X angle to target relative to robot's forward vector

		//Measurement Graph:
		//     ------   <-target
		//       |
		//       |   <- distance
		//       |
		//       |--------[] <- robot
		//         ^x Offset

		int SLOW_RANGE = 3; //feet

		//Math for calculating new target angle at stoppingDistance away from the target
		//TODO: IDK if the angle standards (left is negative, angle starts at 0 relative to target, etc.) on Limelight are how I assumed they are, so I will experiment with the limelight and correct the math if needed

		//Direct distance to target (hypotenuse)
		double d = Math.sqrt((xOffset * xOffset) + (distance * distance));

		//New distance that the robot should target
		double newDistance = distance - stoppingDistance;

		//Direct distance to new stopping point
		double d1 = Math.sqrt((xOffset * xOffset) + ((newDistance) * (newDistance)));

		//Value for calculating other values that is hard to explain in comments
		double x = d * Math.sin(Math.toRadians(angle));

		//Value for calculating other values that is hard to explain in comments
		double x1 = stoppingDistance * Math.sin(Math.toRadians(skew));

		//Value for calculating other values that is hard to explain in comments
		double x2 = x1 + x;

		//Final angle value to new stopping point
		double a2 = Math.toDegrees(Math.asin(x2 / d1));

		//Set angle to negative if robot is on other side
		if (xOffset < 0 || angle < 0) {
			a2 = -a2;
		}

		//Calculations for drive and turn values
		double gyroAngle = Gyro.getInstance().getAngle();

		double drive;
		double turn;

		//Calculate drive speed based on distance divided by the slow range in inches, if robot is further from slow
		// range it defaults to max speed and if robot is within slow range it starts to slow down based on how
		// far it is from the target
		drive = Math.min((newDistance / (12 * SLOW_RANGE)) * maxDriveSpeed, maxDriveSpeed);

		//Add skew value to final angle setpoint, this will make the robot curve into the target and eventually be
		// perpendicular to it, otherwise the robot runs straight into the target no matter what rotation the target is
		// relative to the robot
		double finalAngleSetpoint = a2 + skew; //TODO: Add variable ratio if needed

		//Correct angle values based on gyro value going from 0-360
		if (gyroAngle + finalAngleSetpoint < 0) {
			gyroAngle = gyroAngle + finalAngleSetpoint + 360;
		} else if (gyroAngle + finalAngleSetpoint >= 360) {
			gyroAngle = gyroAngle + finalAngleSetpoint - 360;
		} else {
			gyroAngle = gyroAngle + finalAngleSetpoint;
		}

		//Set setpoint of PID controller
		turnController.setSetpoint(gyroAngle);

		//Set turn value to output of PID controller
		turn = turnController.get(); //Get latest calculated output

		//Move left and right wheels based on turn and drive values
		DriveTrain.getInstance().tankDrive(drive + turn, drive - turn);

		//Check if robot is positioned correctly infront of target, if so start a counter that is used to end the command
		if (Math.abs(angle) <= 1 && newDistance <= 1) { //TODO: Tune thresholds
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
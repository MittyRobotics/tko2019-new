package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.autonomous.enums.DriveState;
import frc.robot.autonomous.movement.AutonDriver;
import frc.robot.autonomous.vision.Limelight;
import frc.robot.drive.DriveTrain;

/**
 * The base vision command to drive towards and in front of a target, aligning the robot facing the target close to directly in front of it.
 */
public class VisionAlignment extends Command {

	/**Maximum forward driving speed in percent output (0-1).*/
	private double MAX_DRIVE_SPEED = 0.4;
	/**Maximum turning speed in percent output (0-1).*/
	private double MAX_TURN_SPEED = 0.2;

	//counters to test for end of sequence
	private int reachedTargetCount = 0;
	private int lowDeltaCount = 0;
	private int highDeltaCount = 0;
	private int failedCalculationsCount = 0;
	private int lostTargetCount = 0;
	private int reachedTargetTime = 10;
	private int lowDeltaTime = 10;
	private int highDeltaTime = 0;
	private int failedCalculationsTime = 10;
	private int lostTargetTime = 10;

	private double acceleration = 0;

	double lastDistance = -1000;
	double lastAngle = -1000;

	private int motionID;



	/**
	 * Initializes command with a name "VisionAlignment" and the required subsystem class that will be used, {@link DriveTrain}
	 */
	public VisionAlignment() {
		super("VisionAlignment");
		requires(DriveTrain.getInstance());

		motionID = AutonDriver.getInstance().initNewDriveMethod(DriveState.VISION);
	}

	/**
	 * The initialize function is called at the initialization stage of the command.
	 * <p>
	 * Here the {@link Limelight} is set to vision mode, turning the exposure down on the camera and enabling
	 * the LEDs for optimum vision tracking. All of the values are also reset.
	 */
	@Override
	public void initialize() {
		AutonDriver.getInstance().setupVision();

		Limelight.getInstance().enableVisionMode();
		Limelight.getInstance().setPipeline(0);
		lowDeltaCount = 0;
		highDeltaCount = 0;
		reachedTargetCount = 0;
		highDeltaCount = 0;
		lostTargetCount = 0;
		failedCalculationsCount = 0;
		lastAngle = -1000;
		lastDistance = -1000;
	}


	private double distance = 20, xOffset = 0, targetAngle = 0, angle = 0, finalTurn = 0, finalDrive = 0;
	private double drive = 0, turn = 0, skew = 0;

	//Drive gain tuned for drive speed when driving into target
	private double DRIVE_K = 0.021;
	private double DESIRED_DISTANCE = 8;
	//Turn gain tuned for turning speed
	private double TURN_K = 0.025;
	//Skew gain tuned for the amount of influence skew has on turning
	private double SKEW_K = 0.04;

	/**
	 * This method is called periodically (about every 20ms) and does the work of the command.
	 */
	@Override
	public void execute() {
		Limelight.getInstance().updateLimelightValues();

		//Acceleration value that smoothes the starting movement of drive.
		//TODO: Make acceleration value compensate for current velocity so it smooths the transition when entering vision mode
		if (acceleration < 1) {
			acceleration += 0.09;
		}

		//Check if Limelight has target, if so update values, if not keep previous values and test for end of sequence
		if (!Limelight.getInstance().isHasTarget()) {
			lostTargetCount++;
			System.out.println("Lost sight of target " + lostTargetCount + "/" + lostTargetTime);
		} else {
			lostTargetCount = 0;
			distance = Limelight.getInstance().getTargetZFast();
			targetAngle = Limelight.getInstance().getTargetYawFast();
			angle = Limelight.getInstance().getXAngle();
		}
		if(distance == -1000 && targetAngle == -1000){
			failedCalculationsCount ++;
			System.out.println("Failed Calculations: " + failedCalculationsCount + "/" + failedCalculationsTime);
		}
		else{
			Limelight.getInstance().printValues();
			failedCalculationsCount = 0;

			//Cap drive value from -max drive speed to max drive speed
			drive = Math.min((distance - DESIRED_DISTANCE) * DRIVE_K, MAX_DRIVE_SPEED);

			//Make drive never go under 0
			drive = Math.max(drive, 0);

			turn = angle * TURN_K;

			//Cap turn value from -max turning speed to max turning speed
			if (turn > MAX_TURN_SPEED) {
				turn = MAX_TURN_SPEED;
			} else if (turn < -MAX_TURN_SPEED) {
				turn = -MAX_TURN_SPEED;
			}

			//Compensate for errors in skew value
			if (targetAngle < 15) {
				if (targetAngle < 0) {
					skew = -SKEW_K;
				} else if (targetAngle > 0) {
					skew = SKEW_K;
				} else {
					skew = 0;
				}
			} else {
				skew = 0;
			}


		}
		//Subtract skew influence to turn
		turn = turn - skew;
		//Reverse drive value and multiply by acceleration
		drive = -drive * acceleration;
		//Slowly change drive value for no sudden jerks in movement
		if (finalDrive < drive) {
			finalDrive += 0.02;
		} else {
			finalDrive -= 0.02;
		}



		//Apply drive and turn values to the wheels
		DriveTrain.getInstance().tankDrive(finalDrive + turn, finalDrive - turn);


		//Multiple checks for end of sequence
		if (Math.abs(angle) <= 4 && Limelight.getInstance().isHasTarget() && distance <= DESIRED_DISTANCE + 1) { //TODO: Tune thresholds
			reachedTargetCount++;
			System.out.println("Testing for end of vision sequence: " + reachedTargetCount + "/" + reachedTargetTime);
		} else {
			reachedTargetCount = 0;
		}
		if (Math.abs(lastDistance - distance) < 0.5) {
			//lowDeltaCount++;
			System.out.println("Delta change is low: " + lowDeltaCount + "/" + lowDeltaTime);
		} else {
			lowDeltaCount = 0;
		}
		//If the change in angle or distance is greater than a certain threshold, this means an error in target
		//detection has occurred. In this case we should end immediately
		if (lastAngle != -1000 && distance != -1000) {
			if (Math.abs(distance - lastDistance) > 80 || Math.abs(angle - lastAngle) > 30) {
				highDeltaCount ++;
				System.out.println("Target is too far away from previous target: " + highDeltaCount + "/" + highDeltaTime);
			}
			else{
				highDeltaCount = 0;
			}
		}

		lastDistance = distance;
		lastAngle = angle;
	}

	/**
	 * This method is called at the end of the command.
	 * <p>
	 * This resets the wheel speeds to 0. It also puts the {@link Limelight} back into driver mode because
	 * the vision tracking is completed.
	 */
	@Override
	public void end() {
		AutonDriver.getInstance().visionEnd();
		DriveTrain.getInstance().tankDrive(0, 0);
		//Limelight.getInstance().enableDriverMode();
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
	 * @return if any of the end timers are passed
	 */
	@Override
	protected boolean isFinished() {
		return AutonDriver.getInstance().isFinished(motionID) || reachedTargetCount > reachedTargetTime || lostTargetCount > lostTargetTime || lowDeltaCount > lowDeltaTime || highDeltaCount > highDeltaTime || failedCalculationsCount > failedCalculationsTime;
	}
}
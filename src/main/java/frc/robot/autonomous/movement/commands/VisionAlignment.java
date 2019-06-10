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
	double startingDriveSpeed = 0;
    double startingTurnSpeed = 0;

	boolean pipelineSet = false;

	private int count = 0;

	//Empty PIDOutput
	private PIDOutput pidOutput = output -> {
    };

	private PIDController turnController = new PIDController(0.01,0,0, Gyro.getInstance(), pidOutput);

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
        Limelight.getInstance().setPipeline(1);


		turnController.setInputRange(0, 360);
		turnController.setContinuous(true);
		turnController.setOutputRange(-maxTurnSpeed, maxTurnSpeed);
		turnController.enable();
	}

	/**
	 * This method is called periodically (about every 20ms) and does the work of the command.
	 */
	@Override
	public void execute() {
	    if(startingDriveSpeed < maxDriveSpeed){
	        startingDriveSpeed += 0.02;
        }
        if(startingTurnSpeed < maxTurnSpeed){
            startingTurnSpeed += 0.05;
        }
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
//
//		//Direct distance to target (hypotenuse)
//		double d = Math.sqrt((xOffset * xOffset) + (distance * distance));
//
//		//New distance that the robot should target
//		double newDistance = distance - stoppingDistance;
//
//		//Direct distance to new stopping point
//		double d1 = Math.sqrt((xOffset * xOffset) + ((newDistance) * (newDistance)));
//
//		//Value for calculating other values that is hard to explain in comments
//		double x = d * Math.sin(Math.toRadians(angle));
//
//		//Value for calculating other values that
//		is hard to explain in comments
//		double x1 = stoppingDistance * Math.sin(Math.toRadians(skew));
//
//		//Value for calculating other values that is hard to explain in comments
//		double x2 = x1 + x;
//
//		//Final angle value to new stopping point
//		double a2 = Math.toDegrees(Math.asin(x2 / d1));
//
//		//Set angle to negative if robot is on other side
//		if (xOffset < 0 || angle < 0) {
//			a2 = -a2;
//		}

		//Calculations for drive and turn values
		//double gyroAngle = Gyro.getInstance().getAngle();

		double drive;
		double turn;

        double DRIVE_K = 0.3;
        double STEER_K = 0.2;
        double MAX_SPEED = 0.2;

		//Calculate drive speed based on distance divided by the slow range in inches, if robot is further from slow
		// range it defaults to max speed and if robot is within slow range it starts to slow down based on how
		// far it is from the target
		//drive = Math.min((distance / (12 * SLOW_RANGE)) * startingDriveSpeed * 1.4, startingDriveSpeed);

		//Add skew value to final angle setpoint, this will make the robot curve into the target and eventually be
		// perpendicular to it, otherwise the robot runs straight into the target no matter what rotation the target is
		// relative to the robot

        if(Math.abs(xOffset) < 0.5 && !pipelineSet){
            System.out.println("Switching");
            pipelineSet = true;
            Limelight.getInstance().setPipeline(0);
        }

//		double finalAngleSetpoint = angle; //TODO: Add variable ratio if needed
//
//
//		//Set turn value to output of PID controller
//        //System.out.println(Gyro.getInstance().getAngle() + " gyro");
//        //Correct angle values based on gyro value going from 0-360
//        finalAngleSetpoint = -finalAngleSetpoint;
//        if (gyroAngle + finalAngleSetpoint < 0) {
//            gyroAngle = gyroAngle + finalAngleSetpoint + 360;
//        } else if (gyroAngle + finalAngleSetpoint >= 360) {
//            gyroAngle = gyroAngle + finalAngleSetpoint - 360;
//        } else {
//            gyroAngle = gyroAngle + finalAngleSetpoint;
//        }
//
//        //Set setpoint of PID controller
//        turnController.setSetpoint(gyroAngle);

		//turn = turnController.get(); //Get latest calculated output


        turn =  angle/29.8 * STEER_K;
        if(turn < 0.1 && turn > -0.1){
            if(turn < 0){
                turn = -0.1;
            }
            else{
                turn = 0.1;
            }

        }
        if(angle > 15){
            turn =  angle/29.8 * STEER_K * 1;
        }
        drive = -1 * DRIVE_K;

        if(drive > MAX_SPEED){
            drive = MAX_SPEED;
        }

        //turn = turn - (skew/30 * startingTurnSpeed);
		//System.out.println(finalAngleSetpoint + " angle" + turn +
        //System.out.println(gyroAngle + " gyro");
		//Move left and right wheels based on turn and drive values

		DriveTrain.getInstance().tankDrive(drive + turn,  drive - turn);
		//System.out.println(Gyro.getInstance().getAngle() + " gyro");
		//Check if robot is positioned correctly infront of target, if so start a counter that is used to end the command
		if (Math.abs(angle) <= 1 && distance <= 1) { //TODO: Tune thresholds
		    System.out.println(distance + " " + Limelight.getInstance().isHasTarget());
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
		return false;
		//return !Limelight.getInstance().isHasTarget();
		//return count > 30;
	}
}
package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.autonomous.constants.VisionConstants;
import frc.robot.autonomous.vision.Limelight;
import frc.robot.drive.DriveTrain;
import frc.robot.drive.constants.PID;
import frc.robot.hardware.Gyro;

import javax.swing.*;

public class VisionAlignment extends Command {

	private double MAX_SPEED;

	private int lostTargetCooldown = 10;
	private int lostTargetCount = 0;

	private int endVisionCount = 0;
	private int endVisionCount1 = 0;
	private int endVisionTime = 10;

	private double acceleration = 0;
	double lastDistance = -1000;
	double lastAngle = -1000;

	int side = -100;




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
		endVisionCount1 = 0;
		endVisionCount = 0;
		lostTargetCount = 0;
	}

	/**
	 * This method is called periodically (about every 20ms) and does the work of the command.
	 */
	double distance = 20, xOffset = 0, targetAngle = 0, angle = 0, finalTurn= 0, finalDrive = 0;
	boolean donePipelineSwitch = false;
	@Override

	public void execute() {
//	    while(Limelight.getInstance().getCamX() == 0.0 && side == -100){
//	        System.out.println("WAIT");
//        }
//        if(Limelight.getInstance().getCamX() != 0.0 && !donePipelineSwitch){
//            if(Limelight.getInstance().getCamX() < -2){
//                side = -1;
//                Limelight.getInstance().setPipeline(3);
//                System.out.println("Side -1");
//            }
//            else if(Limelight.getInstance().getCamX() > 2){
//                Limelight.getInstance().setPipeline(1);
//                side = 1;
//                System.out.println("Side 1");
//            }
//            else{
//                Limelight.getInstance().setPipeline(0);
//                side = 0;
//                System.out.println("Side 0");
//            }
//            System.out.println("Waiting for 3D values" +Limelight.getInstance().getCamX()  );
//            donePipelineSwitch = true;
//        }

        if(acceleration < 1){
            acceleration += 0.09;
        }
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
		if(lastAngle != -1000 && distance != 0.0){
		    if(Math.abs(distance - lastDistance) > 80 || Math.abs(angle - lastAngle) > 30){
                endVisionCount += 10;
                System.out.println("Target is too far away from previous target");
            }
        }
        System.out.println(distance + " " + xOffset + " " + targetAngle);
		double drive, turn, skew;

		double DRIVE_K = 0.021;
		double DESIRED_DISTANCE = 14;

		drive = Math.min((distance - DESIRED_DISTANCE) * DRIVE_K, MAX_SPEED);
		drive = Math.max(drive,0);
		double TURN_K = 0.02;
		turn = angle * TURN_K;
		if(turn > 0.2){
			turn = 0.2;
		}
		else if(turn < -0.2){
			turn = -0.2;
		}
		double SKEW_K = 0.03 ;
		if(targetAngle < 6){
            if(targetAngle < 0){
                skew = -SKEW_K;
            }
            else if(targetAngle > 0){
                skew = SKEW_K;
            }
            else{
                skew = 0;
            }
        }
		else{
		    skew = 0;
        }


		if(distance < 36){
		    Limelight.getInstance().setPipeline(0);
        }

        //turn = turn + skew;
        //turn = 0;
       // drive = 0;

        drive = -drive * acceleration;

//
//        if(finalTurn < turn){
//            finalTurn += 0.03;
//        }
//        else{
//            finalTurn -= 0.03;
//        }

		turn = turn - skew;
        if(finalDrive < drive){
            finalDrive += 0.02;
        }
        else{
            finalDrive -= 0.02;
        }
		DriveTrain.getInstance().tankDrive(finalDrive + turn, finalDrive - turn);

		if (Math.abs(angle) <= 4 && Limelight.getInstance().isHasTarget() && distance <= DESIRED_DISTANCE + 1) { //TODO: Tune thresholds
			endVisionCount++;
			System.out.println("Testing for end of vision sequence: " + endVisionCount + "/" + endVisionTime);
		}
		else{
		    endVisionCount = 0;
        }
		if(Math.abs(lastDistance - distance) < 1){
			endVisionCount1++;
			System.out.println("Delta change is low: " + endVisionCount1 + "/" + endVisionTime);
		}
		else{
			endVisionCount1 = 0;
		}
		lastDistance = distance;
		lastAngle = angle;
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
		return endVisionCount > endVisionTime || lostTargetCount > lostTargetCooldown || endVisionCount1 > endVisionTime;
        //return !DriverStation.getInstance().isEnabled();
	}
}
package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.autonomous.enums.VisionEndType;
import frc.robot.autonomous.vision.VisionEnd;
import frc.robot.autonomous.vision.Limelight;
import frc.robot.drive.DriveTrain;

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


	private double DESIRED_DISTANCE = 10;

	public VisionAlignment(){
		super("Vision Alignment");
		requires(DriveTrain.getInstance());
	}

	protected void initialize() {
		VisionEnd.getInstance().reset(new VisionEndType[] {VisionEndType.HIGH_DELTA, VisionEndType.LOST_TARGET, VisionEndType.REACHED_TARGET, VisionEndType.FAILED_CALCULATIONS});
	}

	protected void execute() {

		updateValues();

		double drive = 0, turn = 0, skew = 0;


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

	}


	private void updateValues(){
		VisionEnd.getInstance().update(yaw, distance, targetYaw, DESIRED_DISTANCE);
		if(VisionEnd.getInstance().isSafeToUpdate()){
			distance = Limelight.getInstance().getTargetYFast();
			targetYaw = Limelight.getInstance().getTargetYawFast();
			yaw = Limelight.getInstance().getXAngle();
		}
	}


	protected void end() {
		System.out.println("Vision Alignment ended. Cause: " + VisionEnd.getInstance().getEndCause());
	}

	protected void interrupted() {
		end();
	}

	protected boolean isFinished() {
		return VisionEnd.getInstance().isFinished();
	}
}

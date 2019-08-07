package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.autonomous.enums.StreamMode;
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


	private double DESIRED_DISTANCE =13;

	public VisionAlignment(){
		super("Vision Alignment");
		requires(DriveTrain.getInstance());
	}

	private double initDist = 0;
	protected void initialize() {
		VisionEnd.getInstance().reset(new VisionEndType[] {VisionEndType.HIGH_DELTA, VisionEndType.LOST_TARGET, VisionEndType.REACHED_TARGET, VisionEndType.LOW_DELTA});
		Limelight.getInstance().setStreamMode(StreamMode.Main);
		updateValues();
		initDist = distance;

	}

	private double MAX_SPEED = 35; //in per second
	private double TURN_DRIVE_RATIO = 0.5; //Ratio of MAX_SPEED allowed for turn. Drive ratio is 1-TURN_DRIVE_RATIO
	private double DRIVE_K = 1.2; //Drive gain. Decrease if drive is not slowing down fast enough.
	private double TURN_K = 15; //in per second at max angle (29.8 degrees)

	protected void execute() {

		updateValues();

		double drive = 0, turn = 0, skew = 0;

		if(distance > DESIRED_DISTANCE){
			drive = Math.min(MAX_SPEED * (1-TURN_DRIVE_RATIO), (distance-DESIRED_DISTANCE)*DRIVE_K);
		}
		else{
			drive = 0;
		}

		double mappedYaw = yaw / 29.8;

		turn = Math.min((MAX_SPEED * (TURN_DRIVE_RATIO)), Math.max(-(MAX_SPEED * (TURN_DRIVE_RATIO)), mappedYaw * TURN_K));

		if(Double.isNaN(skew)){
			skew = 0;
		}

		double left = -drive + turn ;
		double right = -drive - turn ;

		System.out.println("turn: " + turn + " yaw " + yaw + " dist: " + distance + " drive: " + drive + " skew " + skew + "left: " + left + " right: " + right);

		DriveTrain.getInstance().tankVelocity(left, right);

	}


	private void updateValues(){
		Limelight.getInstance().updateLimelightValues();
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

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
	private double TURN_K = 0.03;

	protected void execute() {


		updateValues();

		double drive = 0, turn = 0, skew = 0;


		double MAX_SPEED = 1;
		double TURN_DRIVE_RATIO = 0.5;
		double TURN_SKEW_RATIO = 1;
		double DRIVE_K = 0.04;
//		if(initDist < 50){
//			DRIVE_K = 0.04;
//		}
//		if(initDist < 30){
//			DESIRED_DISTANCE = DESIRED_DISTANCE - 2;
//			DRIVE_K = 0.04;
//		}



		double SKEW_K = 0.01;


		if(distance > DESIRED_DISTANCE){
			drive = Math.min(MAX_SPEED * (1-TURN_DRIVE_RATIO), (distance-DESIRED_DISTANCE)*DRIVE_K);
		}
		else{
			drive = 0;
		}



		turn = Math.min((MAX_SPEED * (TURN_DRIVE_RATIO) * TURN_SKEW_RATIO), Math.max(-(MAX_SPEED * (TURN_DRIVE_RATIO) * TURN_SKEW_RATIO), yaw * TURN_K));

		//double sign = Math.signum(turn);

		//turn = turn * sign;

		skew = Math.min((MAX_SPEED * (TURN_DRIVE_RATIO) * (1-TURN_SKEW_RATIO)), Math.max(-(MAX_SPEED * (TURN_DRIVE_RATIO) * (1-TURN_SKEW_RATIO)), targetYaw * SKEW_K));

		if(Double.isNaN(skew)){
			skew = 0;
		}


		TURN_K -= 0.00001;

		double left = -drive + turn ;
		double right = -drive - turn ;

		System.out.println("turn: " + turn + " yaw " + yaw + " dist: " + distance + " drive: " + drive + " skew " + skew + "left: " + left + " right: " + right);



		//double left = -drive +  turn;
		//double right = -drive + -turn;
		DriveTrain.getInstance().tankVelocity(left * 35, right * 35);

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

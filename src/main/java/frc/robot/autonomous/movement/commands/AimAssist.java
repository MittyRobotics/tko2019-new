package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.autonomous.vision.Limelight;
import frc.robot.drive.DriveTrain;
import frc.robot.oi.OI;

public class AimAssist extends Command {

	double yawAngle = 0;
	double lostTargetCount = 0;
	double lostTargetCooldown = 10;

	public AimAssist(){
		super("Aim Assist");
		requires(DriveTrain.getInstance());
	}

	protected void initialize() {
		lostTargetCount = 0;
	}

	protected void execute() {
		Limelight.getInstance().updateLimelightValues();

		updateValues();

		double left = -OI.getInstance().getXboxController().getY(GenericHID.Hand.kLeft);
		double right =  -OI.getInstance().getXboxController().getY(GenericHID.Hand.kRight);
		//Turn gain tuned for turning speed
		double TURN_K = 0.01;

		double turn = yawAngle * TURN_K;

		DriveTrain.getInstance().tankDrive(left+turn,right-turn);
	}

	private void updateValues(){
		if(Limelight.getInstance().isHasTarget()){
			yawAngle = Limelight.getInstance().getXAngle();
			lostTargetCount = 0;
		}
		else{
			lostTargetCount ++;
			System.out.println("Lost target: " + lostTargetCount + "/" + lostTargetCooldown);
		}
	}


	protected void end() {

	}

	protected void interrupted() {
		end();
	}

	protected boolean isFinished() {
		return lostTargetCount >= lostTargetCooldown;
	}
}

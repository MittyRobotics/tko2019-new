package com.amhsrobotics.autonomous.movement.commands;

import com.amhsrobotics.autonomous.vision.Limelight;
import com.amhsrobotics.drive.DriveTrain;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.oi.OI;

public class AimAssist extends Command {

	double yawAngle = 0;
	double distance = 0;
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

		double left = -OI.getInstance().getXboxController().getY(GenericHID.Hand.kLeft) / 1.3;
		double right =  -OI.getInstance().getXboxController().getY(GenericHID.Hand.kRight) / 1.3;
		double turn ;
		if(Limelight.getInstance().isHasTarget() && distance < 70){
			//Turn gain tuned for turning speed
			double TURN_K = 0.005;
			turn = yawAngle * TURN_K;

		}
		else{
			turn = 0;
		}


		DriveTrain.getInstance().tankDrive(left+turn,right-turn );
	}

	private void updateValues(){
		if(Limelight.getInstance().isHasTarget()){
			yawAngle = Limelight.getInstance().getXAngle();
			distance = Limelight.getInstance().getTargetYFast();
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
		return false;
	}
}

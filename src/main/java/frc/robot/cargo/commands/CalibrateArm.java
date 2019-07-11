package frc.robot.cargo.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargo.Arm;
import frc.robot.cargo.constants.ArmPosition;
import frc.robot.oi.OI;
import team1351.motionprofile.TrapezoidalMotionProfile;

public class CalibrateArm extends Command {
	private boolean finished;
	public CalibrateArm() {
		super("CalibrateArm");
		requires(Arm.getInstance());
		setTimeout(10);
		this.finished = false;
	}

	@Override
	protected void initialize() {
		Arm.getInstance().zeroEncoder(this);
	}

	@Override
	protected void execute() {
		double t = timeSinceInitialized();
		if(t%1 == 0){
			System.out.println("Calibrating arm. Time since started: " + t + "/" + 10);
		}
	}

	@Override
	protected void end() {
		if(isTimedOut()){
			System.out.println("Calibrate arm timed out! Please check the limit switches.");
		}
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return finished || isTimedOut();
	}

	public void setFinished(){
		this.finished = true;
	}

}

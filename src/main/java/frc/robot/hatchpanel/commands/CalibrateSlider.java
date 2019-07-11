package frc.robot.hatchpanel.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargo.Arm;
import frc.robot.cargo.constants.ArmPosition;
import frc.robot.hatchpanel.Slider;
import frc.robot.oi.OI;
import team1351.motionprofile.TrapezoidalMotionProfile;

public class CalibrateSlider extends Command {
	private boolean finished;
	public CalibrateSlider() {
		super("CalibrateArm");
		requires(Slider.getInstance());
		setTimeout(10);
		this.finished = false;
	}

	@Override
	protected void initialize() {
		Slider.getInstance().zeroEncoder(this);
	}

	@Override
	protected void execute() {
		double t = timeSinceInitialized();
		if(t%1 == 0){
			System.out.println("Calibrating slider. Time since started: " + t + "/" + 10);
		}
	}

	@Override
	protected void end() {
		if(isTimedOut()){
			System.out.println("Calibrate slider timed out! Please check the limit switches.");
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

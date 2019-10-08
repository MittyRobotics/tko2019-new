package com.amhsrobotics.hatchpanel.commands;

import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.hatchpanel.Slider;
//TODO: Rewrite
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

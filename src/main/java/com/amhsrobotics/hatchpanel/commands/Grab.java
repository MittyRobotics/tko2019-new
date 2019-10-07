package com.amhsrobotics.hatchpanel.commands;

import com.amhsrobotics.hatchpanel.Grabber;
import edu.wpi.first.wpilibj.command.Command;

public class Grab extends Command {
	private double timer;
	private boolean  finish;
	public Grab() {
		new Grab(0);
	}

	public Grab(double timer) {
		super("Grab");
		requires(Grabber.getInstance());
		this.timer = timer;
		finish = false;
	}

	@Override
	protected void initialize() {
		if(timer == 0){
			Grabber.getInstance().grab();
			finish = true;
		}

		System.out.println("Grab");
	}

	@Override
	protected void execute() {
		double t = timeSinceInitialized();
		if(timer != 0 && t > timer){
			Grabber.getInstance().grab();
			finish = true;
		}
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}

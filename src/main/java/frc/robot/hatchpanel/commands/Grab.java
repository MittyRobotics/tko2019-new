package frc.robot.hatchpanel.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.hatchpanel.Grabber;

public class Grab extends Command {
	private double timer;
	private boolean  finish;
	public Grab() {
		super("Grab");
		requires(Grabber.getInstance());
		timer = 0;
		finish = false;
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
		return finish;
	}
}

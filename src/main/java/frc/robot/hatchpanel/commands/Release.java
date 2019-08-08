package frc.robot.hatchpanel.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.hatchpanel.Grabber;

public class Release extends Command {
	private double timer;
	private boolean  finish;
	public Release() {
		new Release(0);
	}

	public Release(double timer) {
		super("Release");
		requires(Grabber.getInstance());
		this.timer = timer;
		this.finish = false;
	}

	@Override
	protected void initialize() {
		if(timer == 0){
			Grabber.getInstance().release();
			finish = true;
		}

		System.out.println("Release");
	}

	@Override
	protected void execute() {
		double t = timeSinceInitialized();
		if(timer != 0 && t > timer){
			Grabber.getInstance().release();
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

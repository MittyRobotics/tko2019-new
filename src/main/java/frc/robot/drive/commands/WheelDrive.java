package frc.robot.drive.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drive.DriveTrain;

public class WheelDrive extends Command {
	private double drive, turn;
	private boolean inPlace;

	public WheelDrive(final double drive, final double turn, final boolean inPlace){
		super("Wheel Drive");
		requires(DriveTrain.getInstance());
		this.drive = drive;
		this.turn = turn;
		this.inPlace = inPlace;
	}

	@Override
	protected void initialize(){

	}

	@Override
	protected void execute(){
		DriveTrain.getInstance().wheelDrive(drive, turn, inPlace);
	}

	@Override
	protected void end(){
		DriveTrain.getInstance().tankDrive(0, 0);
	}

	@Override
	protected void interrupted(){
		end();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}

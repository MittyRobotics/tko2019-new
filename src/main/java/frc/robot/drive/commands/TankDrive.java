package frc.robot.drive.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drive.DriveTrain;

public class TankDrive extends Command {
	private double left, right;
	public TankDrive(final double left, final double right){
		requires(DriveTrain.getInstance());
		this.left = left;
		this.right = right;
	}

	@Override
	protected void initialize(){

	}

	@Override
	protected void execute(){
		DriveTrain.getInstance().tankDrive(left, right);
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

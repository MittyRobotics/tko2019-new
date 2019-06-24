package frc.robot.drive.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drive.DriveTrain;
import frc.robot.oi.OI;

public class TankDrive extends Command {
	public TankDrive() {
		super("Tank Drive");
		requires(DriveTrain.getInstance());
	}
	
	@Override
	protected void initialize() {

	}
	
	@Override
	protected void execute() {
		DriveTrain.getInstance().tankDrive(-OI.getInstance().getXboxController().getY(GenericHID.Hand.kLeft), -OI.getInstance().getXboxController().getY(GenericHID.Hand.kRight));
	}
	
	@Override
	protected void end() {
		DriveTrain.getInstance().tankDrive(0, 0);
	}
	
	@Override
	protected void interrupted() {
		end();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}

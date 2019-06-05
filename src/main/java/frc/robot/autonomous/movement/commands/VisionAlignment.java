package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.autonomous.constants.VisionConstants;
import frc.robot.autonomous.vision.Limelight;
import frc.robot.drive.DriveTrain;

public class VisionAlignment extends Command {
	
	double maxSpeed; //percent output
	int count = 0;
	
	/**
	 * Initializes command with a name "VisionAlignment" and the required subsystem class that will be used, {@link DriveTrain}
	 *
	 * @param maxSpeed the maximum speed at which the robot will be rotating at
	 */
	public VisionAlignment(double maxSpeed) {
		super("VisionAlignment");
		requires(DriveTrain.getInstance());
		this.maxSpeed = maxSpeed;
	}
	
	/**
	 * The initialize function is called at the initialization stage of the command.
	 */
	@Override
	public void initialize() {
		//"Everybody needs a friend" -Bob Ross
	}
	
	/**
	 * This method is called periodically (about every 20ms) and does the work of the command.
	 */
	@Override
	public void execute() {
		//"Everybody needs a friend" -Bob Ross
	}
	
	/**
	 * This method is called at the end of the command.
	 */
	@Override
	public void end() {
		DriveTrain.getInstance().tankDrive(0, 0);
	}
	
	/**
	 * This method is called when the command is interrupted, therefore ending the command.
	 */
	@Override
	public void interrupted() {
		end();
	}
	
	/**
	 * This returns whether or not the command has finished.
	 *
	 * @return
	 */
	@Override
	protected boolean isFinished() {
		return false;
	}
}
package frc.robot.drive.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drive.Shifter;
import frc.robot.drive.constants.GearState;

public class GearShift extends Command {
	private GearState gearState;
	public GearShift(GearState gearState){
		requires(Shifter.getInstance());
		this.gearState = gearState;
	}

	@Override
	protected void initialize(){
		Shifter.getInstance().shiftGear(gearState);
	}

	@Override
	protected void execute(){

	}

	@Override
	protected void end(){

	}

	@Override
	protected void interrupted(){
		end();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}

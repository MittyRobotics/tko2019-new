package com.amhsrobotics.drive.commands;

import com.amhsrobotics.drive.constants.GearState;
import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.drive.Shifter;

public class GearShift extends Command {
	private GearState gearState;

	public GearShift(GearState gearState) {
		super("Gear Shift");
		requires(Shifter.getInstance());
		this.gearState = gearState;
	}

	@Override
	protected void initialize() {
		Shifter.getInstance().shiftGear(gearState);
		System.out.println("HERE");

	}

	@Override
	protected void execute() {

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

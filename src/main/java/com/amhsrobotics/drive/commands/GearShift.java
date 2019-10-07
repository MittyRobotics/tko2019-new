package com.amhsrobotics.drive.commands;

import com.amhsrobotics.drive.constants.GearState;
import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.drive.Shifter;

/**
 * Gear Shifting Subsystem
 */
public class GearShift extends Command {
	private GearState gearState;

	/**
	 * Constructor for GearShift Command that runs
	 * Sets the command to the Shifter subsystem
	 * @param gearState the state the gear should be set to
	 */
	public GearShift(GearState gearState) {
		super("Gear Shift");
		requires(Shifter.getInstance());
		this.gearState = gearState;
	}

	/**
	 * Gear shifts the Shifter based on the gearState
	 */
	@Override
	protected void initialize() {
		Shifter.getInstance().shiftGear(gearState);
	}

	/**
	 * Return true because nothing needs to be run continuously
	 * @return true
	 */
	@Override
	protected boolean isFinished() {
		return true;
	}
}
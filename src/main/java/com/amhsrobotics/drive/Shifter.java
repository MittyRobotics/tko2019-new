package com.amhsrobotics.drive;

import com.amhsrobotics.drive.constants.GearState;
import com.amhsrobotics.drive.constants.SolenoidIds;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Shifter Subsystem code
 */
public class Shifter extends Subsystem {
	private DoubleSolenoid gearShift = new DoubleSolenoid(SolenoidIds.GEAR_SHIFT[0], SolenoidIds.GEAR_SHIFT[1]);
	private static Shifter ourInstance = new Shifter();

	/**
	 * Static function to make this class a singleton
	 * @return Shifter subsystem
	 */
	public static Shifter getInstance() {
		return ourInstance;
	}

	/**
	 * Constructor for Roller subsystem
	 */
	private Shifter() {
		super("Shifter");
	}

	/**
	 * Init default command function
	 * No command is set as the default
	 */
	@Override
	protected void initDefaultCommand() {

	}

	/**
	 * Shifts gears based on the GearState parameter
	 * @param gearState is the state that the gear should be shifted to
	 */
	public void shiftGear(GearState gearState) {
		switch (gearState) {
			case High:
				if(gearShift.get() != DoubleSolenoid.Value.kForward){
					gearShift.set(DoubleSolenoid.Value.kForward);
				}
			case Low:
				if(gearShift.get() != DoubleSolenoid.Value.kReverse){
					gearShift.set(DoubleSolenoid.Value.kReverse);
				}
			case Off:
				if(gearShift.get() != DoubleSolenoid.Value.kOff){
					gearShift.set(DoubleSolenoid.Value.kOff);
				}
		}
	}
}
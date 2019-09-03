package com.amhsrobotics.drive;

import com.amhsrobotics.drive.constants.GearState;
import com.amhsrobotics.drive.constants.SolenoidIds;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shifter extends Subsystem {
	private DoubleSolenoid gearShift = new DoubleSolenoid(SolenoidIds.GEAR_SHIFT[0], SolenoidIds.GEAR_SHIFT[1]);
	private static Shifter ourInstance = new Shifter();

	public static Shifter getInstance() {
		return ourInstance;
	}

	private Shifter() {
		super("Shifter");
	}

	@Override
	protected void initDefaultCommand() {

	}

	public void shiftGear(GearState gearState) {
		switch (gearState) {
			case High:
				gearShift.set(DoubleSolenoid.Value.kForward);
			case Low:
				gearShift.set(DoubleSolenoid.Value.kReverse);
			case Off:
				gearShift.set(DoubleSolenoid.Value.kOff);
		}
	}
}
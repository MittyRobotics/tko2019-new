package com.amhsrobotics.hatchpanel;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.amhsrobotics.hatchpanel.constants.SolenoidIds;

/**
 * Grabber Subsystem to grab a Hatch Panel
 */
public class Grabber extends Subsystem {
	private static Grabber ourInstance = new Grabber();
	private DoubleSolenoid grabber = new DoubleSolenoid(SolenoidIds.GRABBER[0], SolenoidIds.GRABBER[1]);

	/**
	 * The only instance of the Grabber Class, making it a singleton
	 * @return a static Grabber object
	 */
	public static Grabber getInstance() {
		return ourInstance;
	}

	/**
	 * Constructor for the Grabber Subsystem
	 */
	private Grabber() {
		super("Grabber");
	}

	/**
	 * Sets no default comamnd
	 */
	@Override
	protected void initDefaultCommand() {

	}

	/**
	 * Function to grab the hatch panel
	 */
	public void grab(){
			grabber.set(DoubleSolenoid.Value.kForward);
	}

	/**
	 * Function to release the hatch panel
	 */
	public void release(){
			grabber.set(DoubleSolenoid.Value.kReverse);
	}
}
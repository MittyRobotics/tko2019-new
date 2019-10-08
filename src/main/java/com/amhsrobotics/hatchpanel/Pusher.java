package com.amhsrobotics.hatchpanel;

import com.amhsrobotics.hatchpanel.constants.SolenoidIds;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Pusher Subsystem to move the slider forward or back
 */
public class Pusher extends Subsystem {
	private static Pusher ourInstance = new Pusher();
	private DoubleSolenoid pusher = new DoubleSolenoid(SolenoidIds.PUSHER[0], SolenoidIds.PUSHER[1]);

	/**
	 * The only instance of the Pusher Class, making it a singleton
	 * @return a static Pusher object
	 */
	public static Pusher getInstance() {
		return ourInstance;
	}

	/**
	 * Constructor for the Pusher Subsystem
	 */
	private Pusher() {
		super("Pusher");
	}

	/**
	 * Sets no default command
	 */
	@Override
	protected void initDefaultCommand() {

	}

	/**
	 * Function to push the slider forward
	 */
	public void pushForward(){
		pusher.set(DoubleSolenoid.Value.kReverse);
	}

	/**
	 * Function to release the hatch panel
	 */
	public void pushBackward(){
		pusher.set(DoubleSolenoid.Value.kForward);
	}
}

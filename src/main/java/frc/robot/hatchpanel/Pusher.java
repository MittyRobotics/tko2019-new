package frc.robot.hatchpanel;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.hatchpanel.constants.SolenoidIds;

public class Pusher extends Subsystem {
	private static Pusher ourInstance = new Pusher();
	private DoubleSolenoid pusher = new DoubleSolenoid(SolenoidIds.PUSHER[0], SolenoidIds.PUSHER[1]);
	public static Pusher getInstance() {
		return ourInstance;
	}

	private Pusher() {
		super("Pusher");
	}

	@Override
	protected void initDefaultCommand() {

	}
	public void pushForward(){
		pusher.set(DoubleSolenoid.Value.kReverse);
	}

	public void pushBackward(){
		pusher.set(DoubleSolenoid.Value.kForward);
	}
}

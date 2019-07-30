package frc.robot.hatchpanel;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.hatchpanel.constants.SolenoidIds;
import frc.robot.hatchpanel.constants.SwitchIds;

public class Grabber extends Subsystem {
	private static Grabber ourInstance = new Grabber();
	private DoubleSolenoid grabber = new DoubleSolenoid(SolenoidIds.GRABBER[0], SolenoidIds.GRABBER[1]);
	private DigitalInput hatchSensor = new DigitalInput(SwitchIds.HATCH_SENSOR); //is inverted
	public static Grabber getInstance() {
		return ourInstance;
	}

	private Grabber() {
		super("Grabber");
	}

	@Override
	protected void initDefaultCommand() {

	}
	public void grab(){
		//if(!hatchSensor.get()){
			grabber.set(DoubleSolenoid.Value.kForward);
		//}
	}
	public void release(){
		//if(!hatchSensor.get()){
			grabber.set(DoubleSolenoid.Value.kReverse);
		//}
	}
}

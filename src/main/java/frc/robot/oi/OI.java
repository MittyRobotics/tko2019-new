package frc.robot.oi;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.drive.commands.GearShift;
import frc.robot.drive.constants.GearState;

public class OI {
	private XboxController controller;
	private Joystick joystick1;
	private Joystick joystick2;
	private SteeringWheel wheel;
	private static OI ourInstance = new OI();

	public static OI getInstance() {
		return ourInstance;
	}

	private OI() {
		Button shiftHigh = new Button(getXboxController().getBumper(GenericHID.Hand.kLeft));
		shiftHigh.whenPressed(new GearShift(GearState.High));
		Button shiftLow = new Button(getXboxController().getBumper(GenericHID.Hand.kRight));
		shiftLow.whenPressed(new GearShift(GearState.Low));
	}

	public XboxController getXboxController(){
		if(controller == null){
			controller = new XboxController(0);
		}
		return controller;
	}

	public Joystick getJoystick1(){
		if(joystick1 == null){
			joystick1 = new Joystick(1);
		}
		return joystick1;
	}

	public Joystick getJoystick2(){
		if(joystick2 == null){
			joystick2 = new Joystick(2);
		}
		return joystick2;
	}

	public SteeringWheel getSteeringWheel(){
		if(wheel == null){
			wheel = new SteeringWheel(3);
		}
		return wheel;
	}
}
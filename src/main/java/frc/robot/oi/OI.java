package frc.robot.oi;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.autonomous.movement.commands.VisionAlignment;
import frc.robot.autonomous.movement.commands.VisionAlignmentTest;
import frc.robot.drive.commands.GearShift;
import frc.robot.drive.commands.TankDrive;
import frc.robot.drive.constants.GearState;
import org.opencv.core.Mat;

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
//		Button drive = new Button() {
//			@Override
//			public boolean get() {
//				return Math.abs(getXboxController().getY(GenericHID.Hand.kLeft)) > 0.05 || Math.abs(getXboxController().getY(GenericHID.Hand.kRight)) > 0.05;
//			}
//		};
//		drive.whileHeld(new TankDrive());
		Button vision = new Button() {
			@Override
			public boolean get() {

				return getXboxController().getAButtonPressed();
			}

		};
		vision.whenPressed(new VisionAlignment(0.4));

		Button shiftLow = new Button() {
			@Override
			public boolean get() {
				return getXboxController().getBumper(GenericHID.Hand.kLeft);
			}
		};
		shiftLow.whenPressed(new GearShift(GearState.Low));
		Button shiftHigh = new Button() {
			@Override
			public boolean get() {
				return getXboxController().getBumper(GenericHID.Hand.kRight);
			}
		};
		shiftHigh.whenPressed(new GearShift(GearState.High));
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
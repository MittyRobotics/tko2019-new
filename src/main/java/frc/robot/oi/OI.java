package frc.robot.oi;

import edu.wpi.first.wpilibj.Controller;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.autonomous.constants.VisionConstants;
import frc.robot.autonomous.movement.commands.MotionProfileTranslate;
import frc.robot.autonomous.movement.commands.VisionAlignment;
import frc.robot.autonomous.movement.commands.VisionAlignmentTest;
//import frc.robot.drive.commands.GearShift;
import frc.robot.drive.commands.TankDrive;
import frc.robot.drive.constants.GearState;
import frc.robot.hatchpanel.commands.Grab;
import frc.robot.hatchpanel.commands.ManualSlide;
import frc.robot.hatchpanel.commands.PushBackward;
import frc.robot.hatchpanel.commands.PushForward;
import frc.robot.hatchpanel.commands.Release;
import frc.robot.hatchpanel.commands.Slide;
import frc.robot.hatchpanel.constants.SliderPosition;
import frc.robot.cargo.commands.ArmDown;
import frc.robot.cargo.commands.ArmUp;
import frc.robot.cargo.commands.Intake;
import frc.robot.cargo.commands.Outtake;
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

				return getXboxController().getTriggerAxis(GenericHID.Hand.kLeft) > 0.5 ;
			}

		};
		Button testProfile = new Button() {
			@Override
			public boolean get() {

				return getXboxController().getAButton() ;
			}

		};
		vision.whenPressed(new VisionAlignment());
		testProfile.whenPressed(new MotionProfileTranslate(10, 0.2));
		Button shiftLow = new Button() {
			@Override
			public boolean get() {
				return getXboxController().getBumper(GenericHID.Hand.kLeft);
			}
		};
		//shiftLow.whenPressed(new GearShift(GearState.Low));
		Button shiftHigh = new Button() {
			@Override
			public boolean get() {
				return getXboxController().getBumper(GenericHID.Hand.kRight);
			}
		};
//		shiftHigh.whenPressed(new GearShift(GearState.High));
		Button slideLeft = new Button() {
			@Override
			public boolean get() {
				return joystick1.getRawButton(4);
			}
		};
		slideLeft.whenPressed(new Slide(SliderPosition.Left));
		Button slideMiddle = new Button() {
			@Override
			public boolean get() {
				return joystick1.getRawButton(3);
			}
		};
		slideMiddle.whenPressed(new Slide(SliderPosition.Middle));
		Button slideRight = new Button() {
			@Override
			public boolean get() {
				return joystick1.getRawButton(5);
			}
		};
		slideRight.whenPressed(new Slide(SliderPosition.Right));
		Button manualSlide = new Button() {
			@Override
			public boolean get() {
				return Math.abs(joystick1.getX()) > 0.2;
			}
		};
		manualSlide.whenPressed(new ManualSlide());
		Button grab = new Button() {
			@Override
			public boolean get() {
				return joystick1.getTrigger();
			}
		};
		grab.whenPressed(new Grab());
		Button release = new Button() {
			@Override
			public boolean get() {
				return joystick1.getRawButton(2);
			}
		};
		release.whenPressed(new Release());
		Button pushForward = new Button() {
			@Override
			public boolean get() {
				return joystick1.getY() > 0.5;
			}
		};
		pushForward.whenPressed(new PushForward());
		Button pushBackward = new Button() {
			@Override
			public boolean get() {
				return joystick1.getY() < - 0.5;
			}
		};
		pushBackward.whenPressed(new PushBackward());
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
package frc.robot.oi;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.autonomous.movement.commands.LegacyVisionAlignment;
//import frc.robot.drive.commands.GearShift;
import frc.robot.autonomous.movement.commands.VisionAlignment;
import frc.robot.autonomous.vision.VisionScore;
import frc.robot.cargo.commands.Angle;
import frc.robot.cargo.commands.Intake;
import frc.robot.cargo.commands.Outtake;
import frc.robot.cargo.commands.StopRollers;
import frc.robot.cargo.constants.ArmPosition;
import frc.robot.drive.commands.GearShift;
import frc.robot.drive.constants.GearState;
import frc.robot.hatchpanel.commands.Grab;
import frc.robot.hatchpanel.commands.PushBackward;
import frc.robot.hatchpanel.commands.PushForward;
import frc.robot.hatchpanel.commands.Release;
import frc.robot.hatchpanel.commands.Slide;
import frc.robot.hatchpanel.constants.SliderPosition;
import team1351.commoncontrols.XboxWheel;


public class OI {
	private XboxController controller;
	private Joystick joystick1;
	private Joystick joystick2;
	private XboxWheel wheel;
	private static OI ourInstance = new OI();

	public static OI getInstance() {
		return ourInstance;
	}

	private OI() {

		Button vision = new Button() {
			@Override
			public boolean get() {

				return getXboxController().getStickButton(GenericHID.Hand.kRight) ;
			}

		};
		Button testProfile = new Button() {
			@Override
			public boolean get() {

				return getXboxController().getAButton() ;
			}

		};
		vision.whenPressed(new VisionAlignment());
		//testProfile.whenPressed(new MotionProfileTranslate(10, 0.2));
		driveControls();
		hatchControls();
		hatchControlsXbox();
		cargoControls();
		cargoControlsXbox();

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

	public XboxWheel getSteeringWheel(){
		if(wheel == null){
			wheel = new XboxWheel(3);
		}
		return wheel;
	}

	private void driveControls(){
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

	private void hatchControls(){
		Button slideLeft = new Button() {
			@Override
			public boolean get() {
				return getJoystick1().getRawButton(4);
			}
		};
		slideLeft.whenPressed(new Slide(SliderPosition.Left));
		Button slideMiddle = new Button() {
			@Override
			public boolean get() {
				return getJoystick1().getRawButton(3);
			}
		};
		slideMiddle.whenPressed(new Slide(SliderPosition.Middle));
		Button slideRight = new Button() {
			@Override
			public boolean get() {
				return getJoystick1().getRawButton(5);
			}
		};
		slideRight.whenPressed(new Slide(SliderPosition.Right));
		Button grab = new Button() {
			@Override
			public boolean get() {
				return getJoystick1().getTrigger();
			}
		};
		grab.whenPressed(new Grab());
		Button release = new Button() {
			@Override
			public boolean get() {
				return getJoystick1().getRawButton(2);
			}
		};
		release.whenPressed(new Release());
		Button pushForward = new Button() {
			@Override
			public boolean get() {
				return getJoystick1().getY() > 0.5;
			}
		};
		pushForward.whenPressed(new PushForward());
		Button pushBackward = new Button() {
			@Override
			public boolean get() {
				return getJoystick1().getY() < - 0.5;
			}
		};
		pushBackward.whenPressed(new PushBackward());
	}

	private void cargoControls(){
		Button intake = new Button() {
			@Override
			public boolean get() {
				return getJoystick2().getTrigger();
			}
		};
		intake.whenPressed(new Intake());
		intake.whenReleased(new StopRollers());
		Button outtake = new Button() {
			@Override
			public boolean get() {
				return getJoystick2().getRawButton(2);
			}
		};
		outtake.whenPressed(new Outtake());
		outtake.whenReleased(new StopRollers());
		Button angleCargo = new Button() {
			@Override
			public boolean get() {
				return getJoystick2().getRawButton(5);
			}
		};
		angleCargo.whenPressed(new Angle(ArmPosition.Cargo));
		Button angleRocket = new Button() {
			@Override
			public boolean get() {
				return getJoystick2().getRawButton(4);
			}
		};
		angleRocket.whenPressed(new Angle(ArmPosition.Rocket));
		Button angleGround = new Button() {
			@Override
			public boolean get() {
				return getJoystick2().getRawButton(3);
			}
		};
		angleGround.whenPressed(new Angle(ArmPosition.Ground));
	}

	private void hatchControlsXbox(){
		Button slideLeft = new Button() {
			@Override
			public boolean get() {
				return getXboxController().getBackButton();
			}
		};
		slideLeft.whenPressed(new Slide(SliderPosition.Left));
		Button slideMiddle = new Button() {
			@Override
			public boolean get() {
				return getXboxController().getStartButton();
			}
		};
		slideMiddle.whenPressed(new Slide(SliderPosition.Middle));
		Button slideRight = new Button() {
			@Override
			public boolean get() {
				return getXboxController().getXButton();
			}
		};
		slideRight.whenPressed(new Slide(SliderPosition.Right));
		Button grab = new Button() {
			@Override
			public boolean get() {

				return getXboxController().getTriggerAxis(GenericHID.Hand.kLeft) > 0.5;
			}
		};
		grab.whenPressed(new Grab());
		Button release = new Button() {
			@Override
			public boolean get() {
				return getXboxController().getBumper(GenericHID.Hand.kLeft);
			}
		};
		release.whenPressed(new Release());

		Button pushForward = new Button() {
			@Override
			public boolean get() {
				return getXboxController().getTriggerAxis(GenericHID.Hand.kLeft) > 0.5;
			}
		};
		//pushForward.whenPressed(new PushForward());
		Button pushBackward = new Button() {
			@Override
			public boolean get() {
				return getXboxController().getBumper(GenericHID.Hand.kLeft);			}
		};
		//pushBackward.whenPressed(new PushBackward());

	}
	private void cargoControlsXbox(){
		Button intake = new Button() {
			@Override
			public boolean get() {
				return getXboxController().getBumper(GenericHID.Hand.kRight);
			}
		};
		intake.whenPressed(new Intake());
		intake.whenReleased(new StopRollers());
		Button outtake = new Button() {
			@Override
			public boolean get() {
				return getXboxController().getTriggerAxis(GenericHID.Hand.kRight) < 0.3;
			}
		};
		outtake.whenPressed(new Outtake());
		outtake.whenReleased(new StopRollers());
		Button angleCargo = new Button() {
			@Override
			public boolean get() {
				return getXboxController().getYButton();
			}
		};
		angleCargo.whenPressed(new Angle(ArmPosition.Cargo));
		Button angleRocket = new Button() {
			@Override
			public boolean get() {
				return getXboxController().getBButton();
			}
		};
		angleRocket.whenPressed(new Angle(ArmPosition.Rocket));
		Button angleGround = new Button() {
			@Override
			public boolean get() {
				return getXboxController().getAButton();
			}
		};
		angleGround.whenPressed(new Angle(ArmPosition.Ground));
	}
}
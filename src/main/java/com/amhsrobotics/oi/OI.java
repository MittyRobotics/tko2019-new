package com.amhsrobotics.oi;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

//import GearShift;


public class OI {
	private XboxController controller;
	private Joystick joystick1;
	private Joystick joystick2;
	private static OI ourInstance = new OI();

	public static OI getInstance() {
		return ourInstance;
	}

	private OI() {

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

}
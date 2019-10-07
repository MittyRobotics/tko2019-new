package com.amhsrobotics.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.amhsrobotics.autonomous.enums.AutoMode;
import com.amhsrobotics.autonomous.modes.BL_CargoShipFrontHatchAuto;
import com.amhsrobotics.autonomous.modes.ExitHabAuto;


/**
 * Autonomous mode selector class.
 * <p>
 * Reads "AUTO_MODE" value from {@link SmartDashboard} and calls the respective autonomous mode based on the value.
 */
public class AutoSelector {
	private static AutoSelector ourInstance = new AutoSelector();

	public static AutoSelector getInstance() {
		return ourInstance;
	}

	private AutoSelector() {

	}

	public void selectAutoMode() {
		int modeID = (int) SmartDashboard.getNumber("AUTO_MODE", 0);
		AutoMode mode = AutoMode.getMode(modeID);
		switch (mode) {
			case EXIT_HAB_AUTO:
				new ExitHabAuto().start();
				break;
			case CARGO_SHIP_FRONT_HATCH_AUTO:
				new BL_CargoShipFrontHatchAuto().start();
				break;
			default:
				//Invalid autonomous mode
				System.out.println("You selected an invalid autonomous mode!");
				break;
		}
	}
}

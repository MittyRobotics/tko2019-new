package frc.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.autonomous.enums.AutoMode;
import frc.robot.autonomous.modes.CargoShipFrontHatchAuto;
import frc.robot.autonomous.modes.ExitHabAuto;


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
		int autoMode = (int) SmartDashboard.getNumber("AUTO_MODE", 0);
		selectAutoMode(AutoMode.getMode(autoMode));
	}

	private void selectAutoMode(AutoMode mode) {
		switch (mode) {
			case EXIT_HAB_AUTO:
				ExitHabAuto.getInstance().start();
				break;
			case CARGO_SHIP_FRONT_HATCH_AUTO:
				CargoShipFrontHatchAuto.getInstance().start();
				break;
			default:
				//Invalid autonomous mode
				System.out.println("You selected an invalid autonomous mode!");
				break;

		}
	}
}

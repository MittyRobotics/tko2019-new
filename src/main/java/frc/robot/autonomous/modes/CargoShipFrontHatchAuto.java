package frc.robot.autonomous.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.autonomous.vision.VisionScore;
import frc.robot.autonomous.enums.CargoTarget;
import frc.robot.autonomous.enums.GamePiece;
import frc.robot.autonomous.movement.commands.MotionProfileTranslate;

public class CargoShipFrontHatchAuto extends CommandGroup {
	private static CargoShipFrontHatchAuto ourInstance = new CargoShipFrontHatchAuto();

	public static CargoShipFrontHatchAuto getInstance() {
		return ourInstance;
	}

	private CargoShipFrontHatchAuto() {
		//Drive forward to within range of vision
		addSequential(new MotionProfileTranslate(48, 0.6));
		//Start vision score sequence: score hatch panel on cargo ship
		VisionScore.getInstance().selectSequence();
	}
}

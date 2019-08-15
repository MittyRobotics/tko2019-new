package com.amhsrobotics.autonomous.modes;

import com.amhsrobotics.autonomous.movement.commands.MotionProfileTranslate;
import com.amhsrobotics.autonomous.vision.VisionScore;
import edu.wpi.first.wpilibj.command.CommandGroup;

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

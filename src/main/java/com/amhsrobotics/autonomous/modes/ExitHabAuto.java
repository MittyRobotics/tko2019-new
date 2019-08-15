package com.amhsrobotics.autonomous.modes;

import com.amhsrobotics.autonomous.movement.commands.MotionProfileTranslate;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ExitHabAuto extends CommandGroup {
	private static ExitHabAuto ourInstance = new ExitHabAuto();

	public static ExitHabAuto getInstance() {
		return ourInstance;
	}

	private ExitHabAuto() {
		addSequential(new MotionProfileTranslate(48, 0.6));
	}
}

package frc.robot.autonomous.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.autonomous.movement.commands.MotionProfileTranslate;

public class ExitHabAuto extends CommandGroup {
	private static ExitHabAuto ourInstance = new ExitHabAuto();

	public static ExitHabAuto getInstance() {
		return ourInstance;
	}

	private ExitHabAuto() {
		addSequential(new MotionProfileTranslate(48, 0.6));
	}
}

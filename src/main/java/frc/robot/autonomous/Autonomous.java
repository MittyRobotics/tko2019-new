package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.autonomous.enums.CargoTarget;
import frc.robot.autonomous.enums.GamePiece;
import frc.robot.autonomous.movement.commands.PIDTranslation;

/**
 * The autonomous command group that contains the master autonomous sequence during the 15 second autonomous/sandstorm period.
 */
public class Autonomous extends CommandGroup {
	private static Autonomous ourInstance = new Autonomous();
	
	public static Autonomous getInstance() {
		return ourInstance;
	}
	
	/**
	 * Main autonomous sequence command group. This is called to start the autonomous sequence.
	 */
	private Autonomous() {
		//Drive forward to within range of vision
		addSequential(new PIDTranslation(48, 0.6));
		//Start vision score sequence: score hatch panel on cargo ship
		VisionScore.getInstance().selectSequence(GamePiece.Hatch, CargoTarget.Ship);
	}
}

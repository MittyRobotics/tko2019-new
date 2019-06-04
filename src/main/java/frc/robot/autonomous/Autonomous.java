package frc.robot.autonomous;

		import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup {
	private static Autonomous ourInstance = new Autonomous();

	public static Autonomous getInstance() {
		return ourInstance;
	}

	private Autonomous() {
		VisionScore.getInstance().selectSequence(GamePiece.Hatch, CargoTarget.Rocket);
	}
}

package frc.robot.autonomous.vision;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.autonomous.enums.CargoTarget;
import frc.robot.autonomous.enums.GamePiece;
import frc.robot.autonomous.movement.commands.PIDTranslation;
import frc.robot.autonomous.movement.commands.VisionAlignment;

public class VisionScore extends CommandGroup {
	private static VisionScore ourInstance = new VisionScore();
	
	public static VisionScore getInstance() {
		return ourInstance;
	}
	
	private VisionScore() {
		//private VisionScore(){} is sad because there is nothing here :(
		//"Everybody needs a friend" -Bob Ross
	}
	
	/**
	 * Command sequence for automatically scoring hatch panels.
	 * <p>
	 * Works for either cargo ship or rocket ship.
	 */
	private void hatchSequence() {
		//addParallel(new MoveHatch(HatchPosition.Center));
		addParallel(new VisionAlignment());
		//addSequential(new PushForward());
		//addSequential(new Release());
		addSequential(new PIDTranslation(-4, 0.4));
	}
	
	/**
	 * Command sequence for automatically scoring cargo balls.
	 *
	 * @param cargoTarget where the cargo ball is being scored, cargo ship or rocket ship.
	 */
	private void cargoSequence(CargoTarget cargoTarget) {
		addSequential(new VisionAlignment());
		switch (cargoTarget) {
			case Rocket:
				//addSequential(new Angle(CargoAngle.RocketShip));
			case Ship:
				//addSequential(new Angle(CargoAngle.CargoShip));
		}
		//addSequential(new Outtake());
	}
	
	/**
	 * Selecting a sequence to automatically score a game piece.
	 * <p>
	 * Call VisionScore.getInstance().selectSequence(gamePiece, cargoTarget) to
	 * initiate the automatic scoring sequence.
	 *
	 * @param gamePiece   which game piece is being scored, hatch or cargo
	 * @param cargoTarget where the game piece is being scored, cargo ship or rocket ship
	 */
	public void selectSequence(GamePiece gamePiece, CargoTarget cargoTarget) {
		switch (gamePiece) {
			case Hatch:
				hatchSequence();
			case Cargo:
				cargoSequence(cargoTarget);
		}
	}
}

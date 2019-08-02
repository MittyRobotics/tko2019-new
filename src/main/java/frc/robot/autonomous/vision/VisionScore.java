package frc.robot.autonomous.vision;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.autonomous.enums.CargoTarget;
import frc.robot.autonomous.enums.GamePiece;
import frc.robot.autonomous.movement.commands.LegacyVisionAlignment;
import frc.robot.autonomous.movement.commands.MotionProfileTranslate;
import frc.robot.cargo.commands.Angle;
import frc.robot.cargo.commands.Outtake;
import frc.robot.cargo.constants.ArmPosition;
import frc.robot.hatchpanel.commands.PushForward;
import frc.robot.hatchpanel.commands.Release;
import frc.robot.hatchpanel.commands.Slide;
import frc.robot.hatchpanel.constants.SliderPosition;

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
		addParallel(new Slide(SliderPosition.Middle));
		addParallel(new LegacyVisionAlignment());
		addSequential(new PushForward());
		addSequential(new Release());
		addSequential(new MotionProfileTranslate(-4, 0.4));
	}
	
	/**
	 * Command sequence for automatically scoring cargo balls.
	 *
	 * @param cargoTarget where the cargo ball is being scored, cargo ship or rocket ship.
	 */
	private void cargoSequence(CargoTarget cargoTarget) {
		addSequential(new LegacyVisionAlignment());
		switch (cargoTarget) {
			case Rocket:
				//addSequential(new Angle(CargoAngle.RocketShip));
			case Ship:
				addSequential(new Angle(ArmPosition.Cargo));
		}
		addSequential(new Outtake());
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

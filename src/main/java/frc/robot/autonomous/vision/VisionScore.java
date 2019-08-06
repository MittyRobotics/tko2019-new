package frc.robot.autonomous.vision;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.autonomous.enums.CargoTarget;
import frc.robot.autonomous.enums.GamePiece;
import frc.robot.autonomous.movement.commands.LegacyVisionAlignment;
import frc.robot.autonomous.movement.commands.MotionProfileTranslate;
import frc.robot.autonomous.movement.commands.VisionAlignment;
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
		addParallel(new PushForward());
		addParallel(new VisionAlignment());
		addSequential(new Release());
	}
	
	/**
	 * Selecting a sequence to automatically score a game piece.
	 * <p>
	 * Call VisionScore.getInstance().selectSequence() to
	 * initiate the automatic scoring sequence.
	 */
	public void selectSequence() {
		hatchSequence();
	}
}

package com.amhsrobotics.autonomous.vision;

import com.amhsrobotics.autonomous.movement.commands.VisionAlignment;
import edu.wpi.first.wpilibj.command.CommandGroup;
import com.amhsrobotics.hatchpanel.commands.PushBackward;
import com.amhsrobotics.hatchpanel.commands.Release;

public class VisionScore extends CommandGroup {
	private static VisionScore ourInstance = new VisionScore();
	
	public static VisionScore getInstance() {
		return ourInstance;
	}
	
	public VisionScore() {
		//private VisionScore(){} is sad because there is nothing here :(
		//"Everybody needs a friend" -Bob Ross
		selectSequence();
	}
	
	/**
	 * Command sequence for automatically scoring hatch panels.
	 * <p>
	 * Works for either cargo ship or rocket ship.
	 */
	private void hatchSequence() {
		addSequential(new PushBackward());
		addSequential(new VisionAlignment());
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

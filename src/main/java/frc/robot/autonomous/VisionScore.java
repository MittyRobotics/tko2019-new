package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class VisionScore extends CommandGroup {
	private static VisionScore ourInstance = new VisionScore();

	public static VisionScore getInstance() {
		return ourInstance;
	}

	private VisionScore() {
	}

	private void hatchSequence(){

	}
	
	private void cargoSequence(CargoTarget cargoTarget){

	}

	public void selectSequence(GamePiece gamePiece, CargoTarget cargoTarget){
		switch (gamePiece){
			case Hatch:
				hatchSequence();
			case Cargo:
				cargoSequence(cargoTarget);
		}
	}
}

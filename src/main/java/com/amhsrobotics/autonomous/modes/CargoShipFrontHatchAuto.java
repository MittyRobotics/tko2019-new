package com.amhsrobotics.autonomous.modes;

import com.amhsrobotics.autonomous.constants.AutoWaypoints;
import com.amhsrobotics.autonomous.movement.commands.MotionProfileTranslate;
import com.amhsrobotics.autonomous.movement.commands.Translate2dTrajectory;
import com.amhsrobotics.autonomous.movement.commands.Translate2dTrajectoryVision;
import com.amhsrobotics.autonomous.movement.commands.VisionAlignment;
import com.amhsrobotics.autonomous.vision.VisionScore;
import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.hatchpanel.commands.Grab;
import com.amhsrobotics.hatchpanel.commands.PushForward;
import com.amhsrobotics.hatchpanel.commands.Release;
import com.amhsrobotics.purepursuit.enums.PathType;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CargoShipFrontHatchAuto extends CommandGroup {

	public CargoShipFrontHatchAuto(int stage){
		if(stage == 0){
			approachCargoShip();
		}
		else if(stage == 1){
			visionPlace1();
		}
		else if(stage == 2){
			driveToHelperPoint1();
		}
		else if(stage == 3){
			approachLoader();
		}
		else if(stage == 4){
			visionPickup();
		}
		else if(stage == 5){
			driveToHelperPoint2();
		}
		else if(stage == 6){
			approachRocket();
		}
		else if(stage == 7){
			visionPlace2();
		}
	}

	public CargoShipFrontHatchAuto() {
		requires(DriveTrain.getInstance());
		approachCargoShip();
		visionPlace1();
		driveToHelperPoint1();
		approachLoader();
		visionPickup();
		driveToHelperPoint2();
		approachRocket();
		visionPlace2();
	}

	public void approachCargoShip(){
		System.out.println("Started approach cargo ship stage");
		addSequential(new PushForward());
		addSequential( new Translate2dTrajectoryVision(AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[0], 150,150,500,  PathType.CUBIC_HERMITE_PATH,100,true));
		System.out.println("Ended approach cargo ship stage");
	}
	public void visionPlace1(){
		System.out.println("Started vision place 1 stage");
		addSequential(new VisionAlignment());
		addSequential(new Release());
		System.out.println("Ended vision place 1 stage");
	}
	public void driveToHelperPoint1(){
		System.out.println("Started drive to helper point 1 stage");
		addSequential(new Translate2dTrajectory(AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[1], 150,150,500, PathType.CUBIC_HERMITE_PATH, 0, false));
		System.out.println("Ended drive to helper point 1 stage");
	}
	public void approachLoader(){
		System.out.println("Started approach loader stage");
		addSequential(new Translate2dTrajectory(AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[2], 150,150,500, PathType.CUBIC_HERMITE_PATH, 0, true));
		System.out.println("Ended approach loader stage");
	}
	public void visionPickup(){
		System.out.println("Started vision pickup stage");
		addSequential(new VisionAlignment());
		addSequential(new Grab());
		System.out.println("Ended vision pickup stage");
	}
	public void driveToHelperPoint2(){
		System.out.println("Started drive to helper point 2 stage");
		addSequential(new Translate2dTrajectory(AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[3], 150,150,500, PathType.CUBIC_HERMITE_PATH, 0, false));
		System.out.println("Ended drive to helper point 2 stage");
	}
	public void approachRocket(){
		System.out.println("Started approach rocket stage");
		addSequential(new Translate2dTrajectory(AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[4], 150,150,500, PathType.CUBIC_HERMITE_PATH, 0, true));
		System.out.println("Ended approach rocket stage");
	}
	public void visionPlace2(){
		System.out.println("Started vision place 2 stage");
		addSequential(new VisionAlignment());
		addSequential(new Release());
		System.out.println("Ended vision place 2 stage");
	}
}

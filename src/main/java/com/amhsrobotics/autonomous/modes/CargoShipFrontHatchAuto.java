package com.amhsrobotics.autonomous.modes;

import com.amhsrobotics.autonomous.constants.AutoConstants;
import com.amhsrobotics.autonomous.constants.AutoPaths;
import com.amhsrobotics.autonomous.movement.commands.*;
import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.hardware.Gyro;
import com.amhsrobotics.hatchpanel.commands.Grab;
import com.amhsrobotics.hatchpanel.commands.PushBackward;
import com.amhsrobotics.hatchpanel.commands.PushForward;
import com.amhsrobotics.hatchpanel.commands.Release;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CargoShipFrontHatchAuto extends CommandGroup {

	public CargoShipFrontHatchAuto(int stage){
		addSequential(new Grab());
		if(stage == 0){
			approachCargoShip();
			visionPlace1();
			driveToHelperPoint1();
			turn1();
			visionPickup();
		}
		else if(stage == 1){
			visionPlace1();
		}
		else if(stage == 2){
			addSequential(new Release());
			driveToHelperPoint1();
			turn1();
		}
		else if(stage == 3){
			approachLoader();
		}
		else if(stage == 4){
			turn1();
		}
		else if(stage == 5){
			visionPickup();
		}
		else if(stage == 6){
			driveToHelperPoint2();
		}
		else if(stage == 7){
			approachRocket();
		}
		else if(stage == 8){
			visionPlace2();
		}
	}

	public CargoShipFrontHatchAuto() {
		requires(DriveTrain.getInstance());
		addSequential(new Grab());
		addSequential(new PushForward());
		approachCargoShip();
		visionPlace1();
		driveToHelperPoint1();
		turn1();
		approachLoader();
		visionPickup();
	}

	public void approachCargoShip(){
		System.out.println("Started approach cargo ship stage");
		addSequential(new PushBackward());
		//addSequential( new Translate2dTrajectory(AutoWaypoints.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET[0], 150,50,500,  PathType.CUBIC_HERMITE_PATH,10,true, true));
		System.out.println("reached end hab");
		addSequential( new Translate2dTrajectory(AutoPaths.B_RIGHT_START_TO_FRONT_CS,true));
		System.out.println("Ended approach cargo ship stage");
	}
	public void visionPlace1(){
		System.out.println("Started vision place 1 stage");
		addSequential(new VisionAlignmentNew());
		addSequential(new Release());
		System.out.println("Ended vision place 1 stage");
	}
	public void driveToHelperPoint1(){
		System.out.println("Started drive to helper point 1 stage");
		addSequential( new Translate2dTrajectory(AutoPaths.B_RIGHT_FRONT_CS_TO_RIGHT_HELPER,true));
		System.out.println("Ended drive to helper point 1 stage");
	}
	public void turn1(){
		System.out.println("Started turn 1 stage");
		//TODO: Figure out if 90 is positive or negative
		addSequential(new PIDRotation(180));
		System.out.println("Ended turn 1 stage");
	}
	public void approachLoader(){
		System.out.println("Started approach loader stage");
		//addSequential( new MotionProfileTranslate(AutoConstants.BLUE_RIGHT_HELPER_POINT.getWaypoint().getX() - AutoConstants.BLUE_RIGHT_LOADER_STATION.getWaypoint().getX()));
		addSequential( new Translate2dTrajectory(AutoPaths.B_RIGHT_HELPER_TO_RIGHT_LOADER,true));
		System.out.println("Ended approach loader stage");
	}
	public void visionPickup(){
		System.out.println("Started vision pickup stage");
		addSequential(new VisionAlignmentNew());
		addSequential(new Grab());
		System.out.println("Ended vision pickup stage");
	}
	public void driveToHelperPoint2(){
		System.out.println("Started drive to helper point 2 stage");

		System.out.println("Ended drive to helper point 2 stage");
	}
	public void approachRocket(){
		System.out.println("Started approach rocket stage");

		System.out.println("Ended approach rocket stage");
	}
	public void visionPlace2(){
		System.out.println("Started vision place 2 stage");
		addSequential(new VisionAlignment());
		addSequential(new Release());
		System.out.println("Ended vision place 2 stage");
	}
}

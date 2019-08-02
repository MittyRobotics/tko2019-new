package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.autonomous.constants.VisionConstants;
import frc.robot.autonomous.vision.Limelight;
import frc.robot.drive.DriveTrain;
import frc.robot.hatchpanel.Slider;
import frc.robot.hatchpanel.commands.Slide;
import frc.robot.hatchpanel.constants.SliderPositions;
import frc.robot.hatchpanel.constants.TicksPerInch;
import frc.robot.oi.OI;

import java.awt.geom.Point2D;

public class AutoSlider extends Command {

	Point2D.Double leftCorner;
	Point2D.Double rightCorner;
	private double yaw;
	double lostTargetCount = 0;
	double lostTargetCooldown = 10;

	public AutoSlider(){
		super("Auto Slider");
		requires(Slider.getInstance());
	}

	protected void initialize() {
		lostTargetCount = 0;
	}

	protected void execute() {
		try {
			Limelight.getInstance().updateLimelightValues();

			updateValues();

			if(Limelight.getInstance().isHasTarget()){


				double distanceBetweenCorners = leftCorner.distance(rightCorner);

				//TODO: Calibrate distance between target inner corners
				double distance = ((VisionConstants.DISTANCE_BETWEEN_TARGET_INNER_CORNERS * VisionConstants.FOCAL_PIXELS_CALIB) / distanceBetweenCorners);


				double xOffset = Math.tan(Math.toRadians(-yaw)) * distance;

				double sliderOffset = -8 + xOffset;

				sliderOffset = Math.max(Math.min(sliderOffset, SliderPositions.RIGHT), SliderPositions.LEFT);

				//System.out.println(sliderOffset + " " + xOffset + " " + yaw + " " + distance);
				if(distance < 50){
					Slider.getInstance().setSliderPosition(sliderOffset * TicksPerInch.SLIDER);
				}
			}
		}
		catch(Exception e){
			System.out.println("Auto slider error");
		}

	}


	private void updateValues(){
		if(Limelight.getInstance().isHasTarget()){
			leftCorner = Limelight.getInstance().getLeftCorner();
			rightCorner = Limelight.getInstance().getRightCorner();
			yaw = Limelight.getInstance().getXAngle();

			if(leftCorner == null || rightCorner == null){
				leftCorner = new Point2D.Double(0,0);
				rightCorner = new Point2D.Double(0,0);
				System.out.println("Corners not detected");
			}
			lostTargetCount = 0;
		}
	}


	protected void end() {

	}

	protected void interrupted() {
		end();
	}

	protected boolean isFinished() {
		return false;
	}
}

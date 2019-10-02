package com.amhsrobotics.autonomous.movement.commands;

import com.amhsrobotics.autonomous.constants.VisionConstants;
import com.amhsrobotics.autonomous.vision.Limelight;
import com.amhsrobotics.hatchpanel.constants.SliderPositions;
import com.amhsrobotics.hatchpanel.constants.TicksPerInch;
import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.hatchpanel.Slider;

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
				double distance = ((VisionConstants.DISTANCE_BETWEEN_TARGET_INNER_CORNERS * VisionConstants.FOCAL_PIXELS_CALIB_HIGH) / distanceBetweenCorners);


				double xOffset = Math.tan(Math.toRadians(-yaw)) * distance;

				double sliderOffset = -8 + xOffset + 2;

				sliderOffset = Math.max(Math.min(sliderOffset, SliderPositions.RIGHT), SliderPositions.LEFT);

				//System.out.println(sliderOffset + " " + xOffset + " " + yaw + " " + distance);
				if(distance < 100){
//					if(Math.abs((Slider.getInstance().getSliderPosition()/TicksPerInch.SLIDER)-sliderOffset) > 0.1){
						Slider.getInstance().setSliderPosition(sliderOffset * TicksPerInch.SLIDER);
//					}

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

package frc.robot.autonomous.movement.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.autonomous.constants.VisionConstants;
import frc.robot.autonomous.vision.Limelight;
import frc.robot.drive.DriveTrain;
import frc.robot.hatchpanel.Slider;
import frc.robot.hatchpanel.commands.Slide;
import frc.robot.hatchpanel.constants.SliderPositions;
import frc.robot.oi.OI;

import java.awt.geom.Point2D;

public class AutoSlider extends Command {

	Point2D.Double leftCorner;
	Point2D.Double rightCorner;
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
		Limelight.getInstance().updateLimelightValues();

		updateValues();

		Point2D.Double mdpt = midpoint(leftCorner, rightCorner);

		double distanceBetweenCorners = leftCorner.distance(rightCorner);

		double distance = ((VisionConstants.DISTANCE_BETWEEN_TARGET_INNER_CORNERS * VisionConstants.FOCAL_PIXELS_CALIB) / distanceBetweenCorners);

		double sign = (mdpt.x > (VisionConstants.RESOLUTION_WIDTH/2)) ? -1 : 1;

		double yaw = Math.toDegrees(Math.atan((mdpt.x-(VisionConstants.RESOLUTION_WIDTH/2))/VisionConstants.FOCAL_PIXELS_CALIB) * sign);

		double xOffset = Math.tan(Math.toRadians(yaw))/distance;

		double sliderOffset = -8 + xOffset;

		sliderOffset = Math.max(Math.min(sliderOffset, SliderPositions.RIGHT), SliderPositions.LEFT);

		System.out.println(sliderOffset + " " + xOffset + " " + yaw + " " + sign);

		//Slider.getInstance().setSliderPosition(sliderOffset);

	}

	private Point2D.Double midpoint(Point2D.Double p0, Point2D.Double p1){
		return new Point2D.Double((p0.x + p1.x) /2, (p0.y + p1.y) /2);
	}

	private void updateValues(){
		if(Limelight.getInstance().isHasTarget()){
			leftCorner = Limelight.getInstance().getLeftCorner();
			rightCorner = Limelight.getInstance().getRightCorner();

			if(leftCorner == null || rightCorner == null){
				leftCorner = new Point2D.Double(0,0);
				rightCorner = new Point2D.Double(0,0);
				System.out.println("Corners not detected");
			}
			lostTargetCount = 0;
		}
		else{
			lostTargetCount ++;
			System.out.println("Lost target: " + lostTargetCount + "/" + lostTargetCooldown);
		}
	}


	protected void end() {

	}

	protected void interrupted() {
		end();
	}

	protected boolean isFinished() {
		return lostTargetCount >= lostTargetCooldown;
	}
}

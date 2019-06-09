package frc.robot.autonomous.constants;

/**
 * Constants that are used in the vision system.
 */
public class VisionConstants {
	public final double FOCAL_LENGTH = 3.3385168390258093; //mm
	public static final double FOCAL_PIXELS = 281.6509; //calculated based on field of view and resolution width in pixels
	public static final double TARGET_HEIGHT_INCHES = 5;
	public final double H_FOV = 59.6; //degrees
	public final double V_FOV = 49.7; //degrees
	public final double RESOLUTION_WIDTH = 320; //pixels
	public final double RESOLUTION_HEIGHT = 240; //pixels
	
	public static final int AREA_TO_STOP = 15; //% of screen TODO: CALIBRATE
}

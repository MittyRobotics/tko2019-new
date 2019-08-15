package com.amhsrobotics.autonomous.constants;

/**
 * Constants that are used in the vision system.
 */
public class VisionConstants {
    public final double FOCAL_LENGTH = 3.3385168390258093; //mm
	public static final double FOCAL_PIXELS_CALIB = 247.04664613918573 ; //calculated based on field of view and resolution width in pixels
	public static final double FOCAL_PIXELS_CALIB_HIGH = 247.04664613918573 * 3 ; //calculated based on field of view and resolution width in pixels
	public static final double TARGET_DISTANCE_0_2 = 5.75;
	public static final double TARGET_DISTANCE_1_3 = 5.75;
	public static final double DISTANCE_BETWEEN_TARGET_INNER_CORNERS = 8;
	public static final double DISTANCE_BETWEEN_TARGET_SIDES = 12;
	public static final double H_FOV = 59.6; //degrees
	public static final double V_FOV = 49.7; //degrees
//	public static final double RESOLUTION_WIDTH = 320; //pixels
//	public static final double RESOLUTION_HEIGHT = 240; //pixels
	public static final double RESOLUTION_WIDTH = 960; //pixels
	public static final double RESOLUTION_HEIGHT = 720; //pixels
	
	public static final int AREA_TO_STOP = 15; //% of screen TODO: CALIBRATE
}

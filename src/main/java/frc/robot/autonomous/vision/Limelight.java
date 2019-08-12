package frc.robot.autonomous.vision;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.vision.VisionPipeline;
import frc.robot.autonomous.constants.*;
import frc.robot.autonomous.enums.CameraMode;
import frc.robot.autonomous.enums.LedMode;
import frc.robot.autonomous.enums.SnapshotMode;
import frc.robot.autonomous.enums.StreamMode;

import java.awt.*;
import java.awt.geom.Point2D;
/**
 * Singleton that handles all Limelight related tasks and functions.
 */
public class Limelight {
	private static Limelight ourInstance = new Limelight();

	public static Limelight getInstance() {
		return ourInstance;
	}

	private int pipelineID = 0;

	//Values read from limelight networktables
	private double tv; //Whether the limelight has any valid targets (0 or 1)
	private double tx; //Horizontal Offset From Crosshair To Target (-29.8 to 29.8 degrees)
	private double ty; //Vertical Offset From Crosshair To Target (-24.85 to 24.85 degrees)
	private double ta; //Target Area (0% of image to 100% of image
	private double[] camtran;
	private double[] tcornx;
	private double[] tcorny;
	private double[] targetPositionFast; //Distance to the target (inches)

	private Point2D.Double leftCorner;
	private Point2D.Double rightCorner;


	private double[] defaultCamtranVal = {-1000,-1000,-1000,-1000,-1000,-1000};
	private double[] defaultTCornVal = {-1000,-1000,-1000,-1000,-1000,-1000,-1000,-1000};
	/**
	 * Initializes the Limelight's properties
	 * <p>
	 * The Limelight should default in driver mode with no LEDs on. We will enable the LEDs and put the Limelight into
	 * vision mode whenever vision is active, but in order to not distract others on the field we want to keep the
	 * lights off when vision is not active. Putting the Limelight into driver mode allows the drivers to use the
	 * camera while vision is not being used.
	 */
	private Limelight() {

	}

	public void initDefaultLimelightSettings(){
		setPipeline(0);
		setLedMode(LedMode.On);
		setCameraMode(CameraMode.Vision);
		setStreamMode(StreamMode.Secondary);
		setSnapshotMode(SnapshotMode.Off);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	//                                       Limelight Network Tables API:                                //
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	//tv	Whether the limelight has any valid targets (0 or 1)                                          //
	//tx	Horizontal Offset From Crosshair To Target (LL2: -29.8 to 29.8 degrees)                       //
	//ty	Vertical Offset From Crosshair To Target (LL2: -24.85 to 24.85 degrees)                       //
	//ta	Target Area (0% of image to 100% of image)                                                    //
	//ts	Skew or rotation (-90 degrees to 0 degrees)                                                   //
	//tl	The pipeline’s latency contribution (ms) Add at least 11ms for image capture latency.         //
	//tshort	Sidelength of shortest side of the fitted bounding box (pixels)                           //
	//tlong	Sidelength of longest side of the fitted bounding box (pixels)                                //
	//thor	Horizontal sidelength of the rough bounding box (0 - 320 pixels)                              //
	//tvert	Vertical sidelength of the rough bounding box (0 - 320 pixels)                                //
	//getpipe	True active pipeline index of the camera (0 .. 9)                                         //
	//camtran	Results of a 3D position solution, 6 numbers: PIDTranslation (x,y,y) PIDRotation(pitch,yaw,roll)//
	////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Reads the Limelight's values from NetworkTables.
	 * <p>
	 * This should be periodically updated every loop time to ensure the most acurate vision movement
	 */
	public void updateLimelightValues() {
		if(pipelineID == 5){
			double leftX = NetworkTableInstance.getDefault().getTable("GRIP/leftLine").getEntry("x1").getDoubleArray(new double[] {0,0})[0];
			double leftY = NetworkTableInstance.getDefault().getTable("GRIP/leftLine").getEntry("y1").getDoubleArray(new double[] {0,0})[0];
			leftCorner = new Point2D.Double(leftX, leftY);
			double rightX = NetworkTableInstance.getDefault().getTable("GRIP/rightLine").getEntry("x1").getDoubleArray(new double[] {0,0})[0];
			double rightY = NetworkTableInstance.getDefault().getTable("GRIP/rightLine").getEntry("y1").getDoubleArray(new double[] {0,0})[0];
			rightCorner = new Point2D.Double(rightX, rightY);
		}
		else{
			tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0); //Whether the limelight has any valid targets (0 or 1)
			tx = (double) Math.round((float) NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0) * 10) / 10; //Horizontal Offset From Crosshair To Target (LL1: -27 degrees to 27 degrees | LL2: -29.8 to 29.8 degrees)
			ty = (double) Math.round((float) NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0) * 10) / 10; //Vertical Offset From Crosshair To Target (LL1: -20.5 degrees to 20.5 degrees | LL2: -24.85 to 24.85 degrees)
			ta = (double) Math.round((float) NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0) * 10) / 10; //Target Area (0% of image to 100% of image)
			camtran = NetworkTableInstance.getDefault().getTable("limelight").getEntry("camtran").getDoubleArray(defaultCamtranVal);
			tcornx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tcornx").getDoubleArray(defaultTCornVal);
			tcorny = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tcorny").getDoubleArray(defaultTCornVal);
			targetPositionFast = calculateTargetPositionFast();
			leftCorner = new Point2D.Double(tcornx[3], tcorny[3]);
            rightCorner = new Point2D.Double(tcornx[4], tcorny[4]);
		}

	}

	public VisionTarget getCurrentTarget(){
		return new VisionTarget();
	}

	/**
	 * Returns if the Limelight is currently tracking a target or not.
	 *
	 * @return true if target is detected, false if target is not detected
	 */
	public boolean isHasTarget() {
		return tv != 0;
	}

	/**
	 * Returns the horizontal (X) angle value to the detected target on the Limelight.
	 * <p>
	 * This is the angle from the calibrated crosshair on the Limelight to the detected target.
	 *
	 * @return X angle in degrees
	 */
	public double getXAngle() {
		return tx;
	}


	/**
	 * Returns the vertical (Y) angle value to the detected target on the Limelight.
	 * <p>
	 * This is the angle from the calibrated crosshair on the Limelight to the detected target.
	 *
	 * @return Y angle in degrees
	 */
	public double getYAngle() {
		return ty;
	}


	/**
	 * Returns the area of the bounding box of the detected target on the Limelight.
	 * <p>
	 * This value is in percent of screen space that the bounding box takes up, from 0% to 100%
	 *
	 * @return area percentage
	 */
	public double getArea() {
		return ta;
	}

	public double getCamX(){
		return camtran[0];
	}
	public double getCamY(){
		return camtran[1];
	}

	public double getCamZ(){
		return camtran[2];
	}
	public double getCamPitch(){
		return camtran[3];
	}

	public double getCamYaw(){
		return camtran[4];
	}

	public double getCamRoll(){
		return camtran[5];
	}

	public double getTargetXFast() {
		return targetPositionFast[0];
	}

	public double getTargetYFast(){
		return targetPositionFast[1];
	}
	public double getTargetZFast(){
		return targetPositionFast[2];
	}
	public double getTargetYawFast(){
		return targetPositionFast[4];
	}
	public Point2D.Double getLeftCorner(){
		return leftCorner;
	}
	public Point2D.Double getRightCorner(){
		return rightCorner;
	}
	/**
	 * Returns the rough distance to the target in inches.
	 *
	 * @return distance to target in inches
	 */
	public double[] getTargetPositionFast() {
		return targetPositionFast;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//                                       Camera Properties                                                      //
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//                            ledMode	Sets limelight’s LED state                                              //
	//0	use the LED Mode set in the current pipeline                                                                //
	//1	force off                                                                                                   //
	//2	force blink                                                                                                 //
	//3	force on                                                                                                    //
	//                            camMode	Sets limelight’s operation mode                                         //
	//0	Vision processor                                                                                            //
	//1	Driver Camera (Increases exposure, disables vision processing)                                              //
	//                            pipeline	Sets limelight’s current pipeline                                       //
	//0 .. 9	Select pipeline 0..9                                                                                //
	//                            stream	Sets limelight’s streaming mode                                         //
	//0	Standard - Side-by-side streams if a webcam is attached to Limelight                                        //
	//1	PiP Main - The secondary camera stream is placed in the lower-right corner of the primary camera stream     //
	//2	PiP Secondary - The primary camera stream is placed in the lower-right corner of the secondary camera stream//
	//                            snapshot	Allows users to take snapshots during a match                           //
	//0	Stop taking snapshots                                                                                       //
	//1	Take two snapshots per second                                                                               //
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Sets the LED mode on the Limelight camera.
	 * <p>
	 * 0	use the LED Mode set in the current pipeline
	 * 1	force off
	 * 2	force blink
	 * 3	force on
	 *
	 * @param ledMode enum containing the different modes for leds.
	 */
	public void setLedMode(LedMode ledMode) {
		NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(ledMode.value); //	Sets limelight’s LED state
	}

	/**
	 * Sets the camera mode on the Limelight camera.
	 * <p>
	 * 0	Vision processor
	 * 1	Driver Camera (Increases exposure, disables vision processing) pipeline	Sets limelight’s current pipeline
	 *
	 * @param cameraMode enum containing the different modes of camera
	 */
	public void setCameraMode(CameraMode cameraMode) {
		NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(cameraMode.value); //	Sets limelight’s operation mode
	}

	/**
	 * Sets the active pipeline on the Limelight camera. These pipelines are
	 * configured in the limelight configuration tool and range from 0-9.
	 *
	 * @param pipelineID ID of new active pipeline
	 */
	public void setPipeline(int pipelineID) {
		this.pipelineID = pipelineID;
		if (pipelineID > 9 || pipelineID < 0) {
			pipelineID = 0;
		}
		NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(pipelineID); //	Sets limelight’s operation mode
	}

	/**
	 * Sets the stream configuration on the Limelight camera.
	 * <p>
	 * 0	Standard - Side-by-side streams if a webcam is attached to Limelight
	 * 1	PiP Main - The secondary camera stream is placed in the lower-right corner of the primary camera stream
	 * 2	PiP Secondary - The primary camera stream is placed in the lower-right corner of the secondary camera stream
	 *
	 * @param streamMode enum containing the different streaming configurations
	 */
	public void setStreamMode(StreamMode streamMode) {
		NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(streamMode.value); //Sets limelight’s streaming mode
	}

	/**
	 * Sets the snapshot mode on the Limelight camera.
	 * <p>
	 * 0	Stop taking snapshots
	 * 1	Take two snapshots per second
	 *
	 * @param snapshotMode enum containing the different snapshot modes
	 */
	public void setSnapshotMode(SnapshotMode snapshotMode) {
		NetworkTableInstance.getDefault().getTable("limelight").getEntry("snapshot").setNumber(snapshotMode.value); //Allows users to take snapshots during a match
	}

	/**
	 * Puts the Limelight into vision mode, with the camera settings turned to vision mode (low exposure)
	 * and the LEDs on.
	 * <p>
	 * Vision mode sets the camera properties to allow for optimum vision tracking and turns the LEDs on so the
	 * target can be tracked.
	 */
	public void enableVisionMode() {
		setCameraMode(CameraMode.Vision);
		setLedMode(LedMode.On);
	}

	/**
	 * Puts the Limelight into driver mode, with the camera settings turned to driver mode (regular exposure)
	 * and the LEDs off.
	 * <p>
	 * Driver mode allows the driver to see through the Limelight camera and turns off the LEDs so it does not
	 * distract others on the field.
	 */
	public void enableDriverMode() {
		setCameraMode(CameraMode.Driver);
		setLedMode(LedMode.Off);
	}

	/**
	 * Returns the rough calculated distance
	 * <p>
	 * This distance value is calculated based on the height of the target in pixels in relation to the height of the
	 * target in real life
	 *
	 * @return distance in inches
	 */
	private double[] calculateTargetPositionFast() {
		try {
			if(tcornx[0] == -1000){
				return defaultCamtranVal;
			}
			else {
				double x =0,y =0, z =0,pitch =0,yaw =0,roll = 0;

				Point2D.Double[] points = new Point2D.Double[tcornx.length];
				for(int i = 0; i < points.length; i++){
					points[i] = new Point2D.Double(tcornx[i], tcorny[i]);
				}

				double leftDist1 = calculateDistanceToPoints(points[0],points[2], VisionConstants.TARGET_DISTANCE_0_2);
				double leftDist2 = calculateDistanceToPoints(points[1],points[3], VisionConstants.TARGET_DISTANCE_1_3);
				double rightDist1 = calculateDistanceToPoints(points[7],points[5], VisionConstants.TARGET_DISTANCE_0_2);
				double rightDist2 = calculateDistanceToPoints(points[4],points[6], VisionConstants.TARGET_DISTANCE_1_3);

				double leftDist = (leftDist1 + leftDist2) / 2;
				double rightDist = (rightDist1 + rightDist2) / 2;

				Point2D.Double leftCenter = midpoint(points[0], points[2]);
				Point2D.Double rightCenter = midpoint(points[4], points[6]);

				double lYaw = getYaw(leftCenter);
				double rYaw = getYaw(rightCenter);

				//Offset of target position from robot
				double lOffset = getOffset(leftDist, lYaw);
				double rOffset = getOffset(leftDist, rYaw);

				//Points for left and right target (top down) relative to the robot
				Point2D.Double lPoint = new Point2D.Double(lOffset, leftDist);
				Point2D.Double rPoint = new Point2D.Double(rOffset, rightDist);

				int sign = (int)Math.signum(lPoint.y - rPoint.y);

				//Angle of target relative to robot
				double legO = Math.abs(lPoint.y - rPoint.y);
				double legA = Math.abs(lPoint.x - rPoint.x);
				double angle = Math.toDegrees(Math.atan(legO / legA)) * sign;

				double distance = (leftDist + rightDist) /2;

				double sideDistanceOffset = rightDist - leftDist;


				y = distance;

				x = Math.tan(Math.toRadians(getXAngle())) * distance;

				z = -100;

				pitch = -100;

				//yaw = Math.toDegrees(Math.asin(sideDistanceOffset/VisionConstants.DISTANCE_BETWEEN_TARGET_SIDES));
				yaw = angle;

				roll = -100;

				//System.out.println("distance: " + y + "target angle: " + yaw + " lDist: " + leftDist + " rDist: " + rightDist);

				double[] position = {
						x, y, z, pitch, yaw, roll
				};
				return position;
			}
		}catch (Exception e){
			return defaultCamtranVal;
		}
	}

	private double calculateDistanceToPoints(Point2D.Double p1, Point2D.Double p2, double distanceBetweenPoints){
		double pixelDistance = p1.distance(p2);
		return ((distanceBetweenPoints * VisionConstants.FOCAL_PIXELS_CALIB_HIGH) / pixelDistance);
	}

	private double getYaw(Point2D.Double p) {
		double xPosOffset = Math.abs(VisionConstants.RESOLUTION_WIDTH / 2 - p.x);
		double yaw = Math.toDegrees(Math.atan(xPosOffset /VisionConstants.FOCAL_PIXELS_CALIB_HIGH));
		if (p.x < VisionConstants.RESOLUTION_WIDTH / 2) {
			yaw = -yaw;
		}
		return (yaw);
	}

	private double getOffset(double dist, double yaw) {
		double offset = dist * Math.tan(Math.toRadians(yaw));

		return (offset);
	}

	private Point2D.Double midpoint(Point2D.Double p1, Point2D.Double p2){
		return new Point2D.Double((p1.x + p2.x )/2,(p1.y + p2.y )/2);
	}

	/**
	 * Prints the received limelight values in an organized line.
	 */
	public void printValues() {
		//x angle, y angle, area, and has target strings
		String x;
		String y;
		String a;
		String h;

		//Set strings to network table valuesf
		h = Limelight.getInstance().isHasTarget() + "";
		x = Limelight.getInstance().getXAngle() + "";
		y = Limelight.getInstance().getYAngle() + "";
		a = Limelight.getInstance().getArea() + "";


		//Add spaces neccessary to keep elements aligned in print
		if (h.length() == 4) {
			h = h + " ";
		}
		if (x.length() == 3) {
			x = "  " + x;
		}
		if (x.length() == 4) {
			x = " " + x;
		}
		if (x.length() == 5) {
			x = "" + x;
		}
		if (y.length() == 3) {
			y = "  " + y;
		}
		if (y.length() == 4) {
			y = " " + y;
		}
		if (y.length() == 5) {
			y = "" + y;
		}
		if (a.length() == 3) {
			a = "  " + a;
		}
		if (a.length() == 4) {
			a = " " + a;
		}
		if (a.length() == 5) {
			a = "" + a;
		}
		//double camX = getCamX();
		//double camY = getCamY();
		//double camZ = getCamZ();
		//double camPitch = getCamPitch();
		//double camYaw = getCamYaw();
		//double camRoll = getCamRoll();
		//Print values
		System.out.println("Has Target:" + h + " \t X Angle: " + x + " \t  Y Angle: " + y + " \t Area: " + a );
	}

}

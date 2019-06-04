package frc.robot.autonomous.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
    private static Limelight ourInstance = new Limelight();

    public static Limelight getInstance() {
        return ourInstance;
    }

    //Values read from limelight networktables
    private double tv; //Whether the limelight has any valid targets (0 or 1)
    private double tx; //Horizontal Offset From Crosshair To Target (LL1: -27 degrees to 27 degrees | LL2: -29.8 to 29.8 degrees)
    private double ty; //Vertical Offset From Crosshair To Target (LL1: -20.5 degrees to 20.5 degrees | LL2: -24.85 to 24.85 degrees)
    private double ta; //Target Area (0% of image to 100% of image)
    private double ts; //Skew or rotation (-90 degrees to 0 degrees)

    private Limelight() {
        //Lonliness
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       Limelight Network Tables API:                                              //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //tv	Whether the limelight has any valid targets (0 or 1)                                                        //
    //tx	Horizontal Offset From Crosshair To Target (LL1: -27 degrees to 27 degrees | LL2: -29.8 to 29.8 degrees)    //
    //ty	Vertical Offset From Crosshair To Target (LL1: -20.5 degrees to 20.5 degrees | LL2: -24.85 to 24.85 degrees)//
    //ta	Target Area (0% of image to 100% of image)                                                                  //
    //ts	Skew or rotation (-90 degrees to 0 degrees)                                                                 //
    //tl	The pipeline’s latency contribution (ms) Add at least 11ms for image capture latency.                       //
    //tshort	Sidelength of shortest side of the fitted bounding box (pixels)                                         //
    //tlong	Sidelength of longest side of the fitted bounding box (pixels)                                              //
    //thor	Horizontal sidelength of the rough bounding box (0 - 320 pixels)                                            //
    //tvert	Vertical sidelength of the rough bounding box (0 - 320 pixels)                                              //
    //getpipe	True active pipeline index of the camera (0 .. 9)                                                       //
    //camtran	Results of a 3D position solution, 6 numbers: Translation (x,y,y) Rotation(pitch,yaw,roll)              //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Update the limelight values from network tables
    void updateLimelightValues(){
        tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0); //Whether the limelight has any valid targets (0 or 1)
        tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0); //Horizontal Offset From Crosshair To Target (LL1: -27 degrees to 27 degrees | LL2: -29.8 to 29.8 degrees)
        ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0); //Vertical Offset From Crosshair To Target (LL1: -20.5 degrees to 20.5 degrees | LL2: -24.85 to 24.85 degrees)
        ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0); //Target Area (0% of image to 100% of image)
        ts = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ts").getDouble(0); //Skew or rotation (-90 degrees to 0 degrees)
    }

    public boolean isHasTarget(){
        return tv != 0;
    }

    public double getXAngle(){
        return tx;
    }

    public double getYAngle(){
        return ty;
    }

    public double getArea(){
        return ta;
    }

    public double getSkew(){
        return ts;
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
    //pipeline	Sets limelight’s current pipeline                                                                   //
    //0 .. 9	Select pipeline 0..9                                                                                //
    //                            stream	Sets limelight’s streaming mode                                         //
    //0	Standard - Side-by-side streams if a webcam is attached to Limelight                                        //
    //1	PiP Main - The secondary camera stream is placed in the lower-right corner of the primary camera stream     //
    //2	PiP Secondary - The primary camera stream is placed in the lower-right corner of the secondary camera stream//
    //                            snapshot	Allows users to take snapshots during a match                           //
    //0	Stop taking snapshots                                                                                       //
    //1	Take two snapshots per second                                                                               //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setLedMode(int ledMode){
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(ledMode); //	Sets limelight’s LED state
    }

    public void setCameraMode(int camMode){
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(camMode); //	Sets limelight’s operation mode
    }

    public void setStreamMode(int streamMode){
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(streamMode); //Sets limelight’s streaming mode
    }

    public void setSnapshotMode(int snapshotMode){
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("snapshot").setNumber(snapshotMode); //Allows users to take snapshots during a match
    }
}

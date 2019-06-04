package frc.robot.autonomous.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
    private static Limelight ourInstance = new Limelight();

    public static Limelight getInstance() {
        return ourInstance;
    }

    private double tv;
    private double tx;
    private double ty;
    private double ta;
    private double ts;

    private Limelight() {

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

    double getX(){
        return tx;
    }

    double getY(){
        return ty;
    }

    double getArea(){
        return ta;
    }

    boolean isHasTarget(){
        if(tv == 0){
            return false;
        }
        else{
            return true;
        }
    }


}

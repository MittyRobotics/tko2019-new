package com.amhsrobotics.autonomous.movement.commands;

import edu.wpi.first.wpilibj.command.Command;
import com.amhsrobotics.autonomous.enums.StreamMode;
import com.amhsrobotics.autonomous.enums.VisionEndType;
import com.amhsrobotics.autonomous.vision.VisionEnd;
import com.amhsrobotics.autonomous.vision.Limelight;
import com.amhsrobotics.drive.DriveTrain;

public class VisionAlignmentNew extends Command {

    private double distance;
    private double targetYaw;
    private double yaw;

    private double deltaHighTimer;
    private double lostTargetTimer;
    private double reachedTargetTimer;

    private double deltaHighCooldown = 10;
    private double lostTargetCooldown = 10;
    private double reachedTargetCooldown = 10;


    private double DESIRED_DISTANCE =15;

    public VisionAlignmentNew(){
        super("Vision Alignment Cargo");
        requires(DriveTrain.getInstance());
    }

    private double initDist = 0;
    protected void initialize() {
        VisionEnd.getInstance().reset(new VisionEndType[] {VisionEndType.HIGH_DELTA, VisionEndType.LOST_TARGET, VisionEndType.REACHED_TARGET, VisionEndType.LOW_DELTA});
        Limelight.getInstance().setStreamMode(StreamMode.Main);
        updateValues();
        initDist = distance;

    }

    private double MAX_TURN_SPEED = 100; //Max speed cap applied to drive and turn values (in/sec)
    private double MAX_DRIVE_SPEED = 100;
    private double DRIVE_K = 6; //Drive gain. Decrease if drive is not slowing down fast enough.
    private double TURN_K = 150; //in per second at max angle (29.8 degrees)

    protected void execute() {

        updateValues();

        double drive = 0, turn = 0;

//      drive = Math.min(MAX_SPEED * (1-TURN_DRIVE_RATIO), (distance-DESIRED_DISTANCE)*DRIVE_K);
        drive = mapRange((distance-DESIRED_DISTANCE)*DRIVE_K,0,MAX_DRIVE_SPEED);


        double mappedYaw = yaw / 29.8;
//        turn = Math.min((MAX_SPEED * (TURN_DRIVE_RATIO)), Math.max(-(MAX_SPEED * (TURN_DRIVE_RATIO)), mappedYaw * TURN_K));
        turn = mapRange(mappedYaw * TURN_K,-MAX_TURN_SPEED,MAX_TURN_SPEED);

        double left = -drive + turn ;
        double right = -drive - turn ;


        DriveTrain.getInstance().tankVelocity(left, right);

    }


    private void updateValues(){
        Limelight.getInstance().updateLimelightValues();
        VisionEnd.getInstance().update(yaw, distance, targetYaw, DESIRED_DISTANCE);
        if(VisionEnd.getInstance().isSafeToUpdate()){
            distance = Limelight.getInstance().getTargetYFast();
            targetYaw = Limelight.getInstance().getTargetYawFast();
            yaw = Limelight.getInstance().getXAngle();
        }
    }


    protected void end() {
        System.out.println("Vision Alignment Cargo ended. Cause: " + VisionEnd.getInstance().getEndCause());
    }

    protected void interrupted() {
        end();
    }

    protected boolean isFinished() {
        return VisionEnd.getInstance().isFinished();
    }

    double mapRange(double val, double min, double max){
        return Math.min(Math.max(val,min),max);
    }
}

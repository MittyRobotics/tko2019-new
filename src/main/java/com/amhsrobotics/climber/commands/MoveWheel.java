package com.amhsrobotics.climber.commands;

import com.amhsrobotics.climber.ClimberWheel;
import com.amhsrobotics.climber.constants.TicksPerInch;
import com.amhsrobotics.climber.constants.WheelPosition;
import com.amhsrobotics.drive.DriveTrain;
import com.amhsrobotics.motionprofile.MotionFrame;
import com.amhsrobotics.motionprofile.TrapezoidalMotionProfile;
import edu.wpi.first.wpilibj.command.Command;

public class MoveWheel extends Command {
    private TrapezoidalMotionProfile wheelLeft, wheelRight;
    private double t, position;
    public MoveWheel(double position) {
        super("ClimberWheel");
        this.position = position;
        requires(ClimberWheel.getInstance());
        requires(DriveTrain.getInstance());
        System.out.println("construct");
    }

    @Override
    protected void initialize() {
        System.out.println("init");
        wheelLeft = ClimberWheel.getInstance().wheelLeft(WheelPosition.WHEEL_POS, false);
        wheelRight = ClimberWheel.getInstance().wheelRight(WheelPosition.WHEEL_POS, false);
        t = 0;
    }

    @Override
    protected void execute() {
        t = timeSinceInitialized();
        MotionFrame frameRight = wheelRight.getFrameAtTime(t);
        MotionFrame frameLeft = wheelLeft.getFrameAtTime(t);
        ClimberWheel.getInstance().setRightWheel(frameRight.getPosition());
        System.out.println(frameRight.getPosition());
        ClimberWheel.getInstance().setLeftWheel(frameLeft.getPosition());
        System.out.println(frameLeft.getPosition());
        DriveTrain.getInstance().tankDrive(wheelLeft.getFrameAtTime(t).getVelocity(), wheelRight.getFrameAtTime(t).getVelocity());
    }

    @Override
    protected void end(){
        System.out.println("end");
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return (wheelLeft.isFinished() && wheelRight.isFinished());
    }
}

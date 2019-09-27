package com.amhsrobotics.climber.commands;

import com.amhsrobotics.climber.ClimbSubsystem;
import com.amhsrobotics.climber.constants.ClimberPosition;
import com.amhsrobotics.climber.constants.TicksPerInch;
import com.amhsrobotics.motionprofile.MotionFrame;
import com.amhsrobotics.motionprofile.TrapezoidalMotionProfile;
import edu.wpi.first.wpilibj.command.Command;

import static com.amhsrobotics.climber.constants.TicksPerInch.CLIMBER_TPI;

public class MoveClimber extends Command {
    private TrapezoidalMotionProfile climbLeft, climbRight;
    private double t, position;
    public MoveClimber(double position) {
        super("ClimberWheel");
        this.position = position;
        requires(ClimbSubsystem.getInstance());
        System.out.println("construct_mc");
    }

    @Override
    protected void initialize() {
        System.out.println("init");
        climbLeft = ClimbSubsystem.getInstance().climberLeft(ClimberPosition.CLIMB_POS, false);
//        climbRight = ClimbSubsystem.getInstance().climberRight(ClimberPosition.CLIMB_POS, false);
        t = 0;
    }

    @Override
    protected void execute() {
        t = timeSinceInitialized();
//        MotionFrame frameRight = climbRight.getFrameAtTime(t);
        MotionFrame frameLeft = climbLeft.getFrameAtTime(t);
//        ClimbSubsystem.getInstance().setClimberPositionRight(frameRight.getPosition());
//        System.out.println(frameRight.getPosition());
        ClimbSubsystem.getInstance().setClimberPositionLeft(frameLeft.getPosition()*CLIMBER_TPI);
        System.out.println(frameLeft.getPosition());
//        System.out.println("move climber did the thing for once");
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
        return climbLeft.isFinished();
    }
}

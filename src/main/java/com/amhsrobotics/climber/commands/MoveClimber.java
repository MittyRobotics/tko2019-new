package com.amhsrobotics.climber.commands;

import com.amhsrobotics.climber.ClimbSubsystem;
import com.amhsrobotics.motionprofile.MotionFrame;
import com.amhsrobotics.motionprofile.TrapezoidalMotionProfile;
import edu.wpi.first.wpilibj.command.Command;

public class MoveClimber extends Command {
    private ClimbSubsystem.ClimberPosition climberPosition; // TODO: make ClimberPosition file in constants
    private TrapezoidalMotionProfile climb; // Owen's thing
    private double t;
    public MoveClimber(ClimbSubsystem.ClimberPosition sliderPosition) {
        super("Slide");
        requires(ClimbSubsystem.getInstance());
        this.climberPosition = climberPosition;
        System.out.println("construct");
    }

    @Override
    protected void initialize() {
        System.out.println("init");
        climb = ClimbSubsystem.getInstance().climb(climberPosition); // TODO: what
        t = 0;
    }

    @Override
    protected void execute() {
        t = timeSinceInitialized();
        MotionFrame frame = climb.getFrameAtTime(t);
        ClimbSubsystem.getInstance().setClimberPosition(frame.getPosition() * TicksPerInch.CLIMBER); // TODO: make TicksPerInch class in constants
        System.out.println(frame.getPosition());
    }

    @Override
    protected void end() {
        System.out.println("end");
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return climb.isFinished();
    }

}

package com.amhsrobotics.climber.commands;

import com.amhsrobotics.climber.ClimbSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class MoveClimber2 extends Command {
    private double position;

    public MoveClimber2(double position){
        this.position = position;
        requires(ClimbSubsystem.getInstance());
    }
    @Override
    protected void initialize(){

    }

    @Override
    protected void execute(){
        final double RAMP_RATE = 3;
        if ((position - ClimbSubsystem.getInstance().getClimberPositionLeft()) > RAMP_RATE) {
            ClimbSubsystem.getInstance().setClimberPositionLeft(ClimbSubsystem.getInstance().getClimberPositionLeft()+ RAMP_RATE);
        } else {
            ClimbSubsystem.getInstance().setClimberPositionLeft(position);
        }
        if ((position - ClimbSubsystem.getInstance().getClimberPositionRight()) > RAMP_RATE) {
            ClimbSubsystem.getInstance().setClimberPositionRight(ClimbSubsystem.getInstance().getClimberPositionRight()+ RAMP_RATE);
        } else {
            ClimbSubsystem.getInstance().setClimberPositionRight(position);
        }
    }
    @Override
    protected boolean isFinished() {
        return (Math.abs(ClimbSubsystem.getInstance().getClimberPositionLeft() - position) < 0.5) &&
                (Math.abs(ClimbSubsystem.getInstance().getClimberPositionRight() - position) < 0.5);
    }
}

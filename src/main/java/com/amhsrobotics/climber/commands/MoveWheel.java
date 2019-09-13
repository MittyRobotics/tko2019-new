package com.amhsrobotics.climber.commands;

import com.amhsrobotics.climber.ClimbSubsystem;
import com.amhsrobotics.climber.ClimberWheel;
import edu.wpi.first.wpilibj.command.Command;

public class MoveWheel extends Command {
    double position;
    public void MoveWheel(double position) {
        this.position = position;
        requires(ClimbSubsystem.getInstance());
    }
    protected void execute(){
        final double RAMP_RATE = 3;
        if ((position - ClimberWheel.getInstance()) > RAMP_RATE) {
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
    protected boolean isFinished(){
        return false;
    }
}

package com.amhsrobotics.climber.Commands;

import com.amhsrobotics.climber.ClimbSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class MoveClimberDown extends Command {
    @Override
    protected boolean isFinished() {
        return false;
    }
    public MoveClimberDown(){
        requires(ClimbSubsystem.getInstance());
    }
    protected void execute(){
        ClimbSubsystem.getInstance().moveClimber(17);
    }
}

package com.amhsrobotics.climber.commands;

import com.amhsrobotics.climber.ClimbSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class ServoMoveBack extends Command {
    @Override
    protected boolean isFinished() {
        return false;
    }

    public ServoMoveBack(){
        requires(ClimbSubsystem.getInstance());
    }

    protected void execute(){
        ClimbSubsystem.getInstance().moveServo( 0);
    }
}
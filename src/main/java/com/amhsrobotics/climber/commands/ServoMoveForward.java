package com.amhsrobotics.climber.commands;

import com.amhsrobotics.climber.ClimbSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class ServoMoveForward extends Command {
    @Override
    protected boolean isFinished() {
        return false;
    }

    public ServoMoveForward(){
        requires(ClimbSubsystem.getInstance());
    }

    protected void execute(){
        ClimbSubsystem.getInstance().moveServo( 180);
    }
}

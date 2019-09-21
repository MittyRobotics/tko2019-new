package com.amhsrobotics.climber.commands;

import com.amhsrobotics.climber.ClimbSubsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

public class ResetEncoder extends Command {
    public void ResetEncoder(){
        requires(ClimbSubsystem.getInstance());
    }
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}

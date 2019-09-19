package com.amhsrobotics.climber.commands;

import com.amhsrobotics.climber.ClimbSubsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

public class ResetEncoder extends Command {
    public void ResetEncoder(){
        requires(ClimbSubsystem.getInstance());
    }
    protected void execute() {
        while (!ClimbSubsystem.getInstance().leftTalon.getSensorCollection().isFwdLimitSwitchClosed()) {
            ClimbSubsystem.getInstance().leftTalon.set(ControlMode.PercentOutput, .1);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (!ClimbSubsystem.getInstance().rightTalon.getSensorCollection().isFwdLimitSwitchClosed()) {
            ClimbSubsystem.getInstance().rightTalon.set(ControlMode.PercentOutput, .1);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ClimbSubsystem.getInstance().rightTalon.set(ControlMode.PercentOutput, 0);
        ClimbSubsystem.getInstance().leftTalon.set(ControlMode.PercentOutput, 0);
        ClimbSubsystem.getInstance().rightTalon.setSelectedSensorPosition(0);
        ClimbSubsystem.getInstance().leftTalon.setSelectedSensorPosition(0);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}

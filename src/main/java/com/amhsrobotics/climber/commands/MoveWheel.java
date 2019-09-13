package com.amhsrobotics.climber.commands;

import com.amhsrobotics.climber.ClimberWheel;
import edu.wpi.first.wpilibj.command.Command;

public class MoveWheel extends Command {
    double position;
    public void MoveWheel(double position) {
        this.position = position;
        requires(ClimberWheel.getInstance());
    }
    protected void execute(){
        final double RAMP_RATE = 3;
        if ((position - ClimberWheel.getInstance().getLeftWheel()) > RAMP_RATE) {
            ClimberWheel.getInstance().setLeftWheel(ClimberWheel.getInstance().getLeftWheel()+ RAMP_RATE);
        } else {
            ClimberWheel.getInstance().setLeftWheel(position);
        }
        if ((position - ClimberWheel.getInstance().getRightWheel()) > RAMP_RATE) {
            ClimberWheel.getInstance().setRightWheel(ClimberWheel.getInstance().getRightWheel()+ RAMP_RATE);
        } else {
            ClimberWheel.getInstance().setRightWheel(position);
        }
    }
    @Override
    protected boolean isFinished(){
        return (Math.abs(ClimberWheel.getInstance().getLeftWheel() - position) < 0.5) &&
                (Math.abs(ClimberWheel.getInstance().getRightWheel() - position) < 0.5);
    }
}

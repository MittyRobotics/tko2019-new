package com.amhsrobotics.climber.commands;

import com.amhsrobotics.climber.ClimbSubsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Command;

public class MoveClimberDown extends Command {
    @Override
    protected boolean isFinished() {
        return false;
    }
    WPI_TalonSRX rightTalon, leftTalon;
    double tpi = 0;
    double increment = 0;
    public MoveClimberDown(){
        requires(ClimbSubsystem.getInstance());
        WPI_TalonSRX leftTalon = new WPI_TalonSRX(0);
        WPI_TalonSRX rightTalon = new WPI_TalonSRX(1);
        leftTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        rightTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        rightTalon.config_kP(0, 0);
        rightTalon.config_kI(0, 0);
        rightTalon.config_kD(0, 0);
        leftTalon.config_kP(0, 0);
        leftTalon.config_kI(0, 0);
        leftTalon.config_kD(0, 0);
    }
    protected void execute(double position){
        double setpoint = position*tpi;
        double threshold = 0;
        //  leftTalon.set(ControlMode.Position, setpoint);
        while ((Math.abs(rightTalon.getSelectedSensorPosition() - setpoint) > threshold) && (Math.abs(leftTalon.getSelectedSensorPosition() - setpoint) > threshold)){
            if (setpoint - rightTalon.getSelectedSensorPosition() >= increment) {
                rightTalon.set(ControlMode.Position, rightTalon.getSelectedSensorPosition() + increment);
            } else {
                rightTalon.set(ControlMode.Position, setpoint);
            }

            if (setpoint - leftTalon.getSelectedSensorPosition() >= increment) {
                leftTalon.set(ControlMode.Position, leftTalon.getSelectedSensorPosition() + increment);
            } else {
                leftTalon.set(ControlMode.Position, setpoint);
            }
        }
    }
}
